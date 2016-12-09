package blackout.jester;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import blackout.jester.BarData.BarData;
import blackout.jester.BarData.DealType;
import blackout.jester.BarData.EventType;
import blackout.jester.CalendarTab.CalendarFragment;
import blackout.jester.DealsTab.DealListItem;
import blackout.jester.DealsTab.DealsFragment;
import blackout.jester.EventsTab.EventListItem;
import blackout.jester.EventsTab.EventsFragment;
import blackout.jester.FavoritesTab.FavoritesFragment;
import blackout.jester.Filter.FilterClass;
import blackout.jester.Filter.FilterDealArrayAdapter;
import blackout.jester.Filter.FilterEventArrayAdapter;
import blackout.jester.Filter.FilterListItem;
import blackout.jester.MainNavDrawer.DrawerArrayAdapter;
import blackout.jester.MainNavDrawer.DrawerListItem;
import blackout.jester.MapTab.MapFragment;

public class MainActivity extends AppCompatActivity {

    // Main UI variables
    private BottomBar mBottomBar;
    private Menu mainActionBarMenu;
    private View rootView;
    // Filter UI variables
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private DrawerArrayAdapter mDrawerAdapter;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private PopupWindow filterPopUp;
    private ListView filterListView;
    private Button filterApplyButton;
    private FilterDealArrayAdapter filterDealAdapter;
    private FilterEventArrayAdapter filterEventAdapter;
    // Data variables
    private ArrayList<BarData> barList;
    private ArrayList<FilterListItem> filterDealItems; //Deal Filter options for filter Popup list
    private ArrayList<FilterListItem> tempFilterDealItems;
    private ArrayList<FilterListItem> filterEventItems; //Event Filter options for filter Popup list
    private ArrayList<FilterListItem> tempFilterEventItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.main_container);
        barList = new ArrayList<>();
        filterDealItems = new ArrayList<>();
        tempFilterDealItems = new ArrayList<>();
        filterEventItems = new ArrayList<>();
        tempFilterEventItems = new ArrayList<>();

        // Generate Bars
        barList = BarGenerator.generate();

        /** Setting up Bottom Bar navigation **/
        mBottomBar = BottomBar.attach(rootView, savedInstanceState);
        mBottomBar.setMaxFixedTabs(4); //Default is 3, when this number is exceeded the bottombar
        //changes styles. we don't want that happening.
        mBottomBar.setItems(R.menu.bottombar_menu);

        /** Setting up Drawer Menu**/
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mTitle = mDrawerTitle = getTitle();
        // Set the adapter for the list view
        final ArrayList<DrawerListItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerListItem("ic_patron_drawer","Bar Patron"));
        drawerItems.add(new DrawerListItem("ic_owner_drawer","Bar Owner"));
        mDrawerAdapter = new DrawerArrayAdapter(this, 0, drawerItems);

        final CalendarFragment calendarFragment = new CalendarFragment();
        mDrawerList.setAdapter(mDrawerAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DrawerListItem drawerListItem =
                        (DrawerListItem) adapterView.getItemAtPosition(position);
                // this is a bad way of handling this
                if (drawerListItem.getText().equalsIgnoreCase("bar owner")){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, calendarFragment)
                            .commit();
                    mBottomBar.hide();
                }
                else{
                    mBottomBar.show();
                    mBottomBar.selectTabAtPosition(0, false);
                }
                mDrawerLayout.closeDrawers();
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0){

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getSupportActionBar().setTitle(mDrawerTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Set the list's click listener
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_toggle);


        /** Setting up filter menu **/
        // Initializing Filter Lists:
        for (DealType dealType: DealType.values()){
            filterDealItems.add(new FilterListItem(dealType, true));
        }
        for (EventType eventType: EventType.values()){
            filterEventItems.add(new FilterListItem(eventType, true));
        }

        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.filter_popup_window);
        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View filterLayout = layoutInflater.inflate(R.layout.filter_popup_layout, viewGroup);

        // Setting up references to Filter Window elements
        filterListView = (ListView) filterLayout.findViewById(R.id.filter_window_listview);
        filterApplyButton = (Button) filterLayout.findViewById(R.id.filter_apply_button);

        // Setting up ArrayList adapters for the different filter views
        filterDealAdapter =
                new FilterDealArrayAdapter (filterLayout.getContext(), 0, filterDealItems);
        filterEventAdapter =
                new FilterEventArrayAdapter(filterLayout.getContext(), 0, filterEventItems);

        // Setting up filter popup window
        filterPopUp = new PopupWindow(this);
        filterPopUp.setContentView(filterLayout);
        filterPopUp.setFocusable(true);
        filterPopUp.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT); //1000
        filterPopUp.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT); //900

        // Drawing screen with declared bar data.
        updateMainScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_ab_menu, menu);
        this.mainActionBarMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.filter_top:
                tempFilterDealItems.clear();
                tempFilterDealItems.addAll(filterDealItems);
                tempFilterEventItems.clear();
                tempFilterEventItems.addAll(filterEventItems);
                // Calling the filter PopupWindow
                filterPopUp.showAtLocation(rootView, Gravity.CENTER, 0, 0);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    /**
     * This method is called from updateBarData and onCreate.
     */
    private void updateMainScreen(){

        // Generating the Deals and Events List for the main Tabs //
        ArrayList<DealListItem> dealListItems = new ArrayList<>();
        ArrayList<EventListItem> eventListItems = new ArrayList<>();
        ArrayList<BarData> favBarList = new ArrayList<>();
        for (BarData bar : barList){
            dealListItems.addAll(bar.generateDealList());
            eventListItems.addAll(bar.generateEventList());
            if (bar.isFavorite()){
                favBarList.add(bar);
            }
        }
        // Applying filters to lists
        dealListItems = FilterClass.filterByDeal(dealListItems, filterDealItems);
        eventListItems = FilterClass.filterByEvent(eventListItems, filterEventItems);

        // Sorting Lists
        Collections.sort(dealListItems);
        Collections.sort(eventListItems);

        // Bundling Deal and Event lists to pass to fragments
        Bundle dealsBundle = new Bundle();
        dealsBundle.putParcelableArrayList("deals",dealListItems);
        Bundle eventBundle = new Bundle();
        eventBundle.putParcelableArrayList("events",eventListItems);
        Bundle favBundle = new Bundle();
        favBundle.putParcelableArrayList("favorites", favBarList);

        // Instantiating Fragments
        final Fragment dealsFragment = new DealsFragment();
        dealsFragment.setArguments(dealsBundle);
        final Fragment eventsFragment = new EventsFragment();
        eventsFragment.setArguments(eventBundle);
        final Fragment mapFragment = new MapFragment();
        // Set map args
        final Fragment favFragment = new FavoritesFragment();
        favFragment.setArguments(favBundle);

        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_deals) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, dealsFragment)
                            .commit();
                    // Update filter popup window to deals list
                    setFilterWindowToDeals();
                }
                else if (menuItemId == R.id.bb_menu_events) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, eventsFragment)
                            .commit();
                    // Update filter popup window to events list
                    setFilterWindowToEvents();
                }
                else if (menuItemId == R.id.bb_menu_map) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, mapFragment)
                            .commit();
                }
                else if (menuItemId == R.id.bb_menu_favorites) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, favFragment)
                            .commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_deals) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, dealsFragment)
                            .commit();
                    // Update filter popup window to deals list
                    setFilterWindowToDeals();                }
            }
        });
    }

    /**
     * This method is to be called from all child fragments if a bars data needs to be updated.
     *
     * @param updatedBar Updated bar data
     */
    public void updateBarData(BarData updatedBar){
        for (BarData bar : barList){
            // This is not a good way to do this... what id they change the bar name!
            if (bar.getBarName().equalsIgnoreCase(updatedBar.getBarName())){
                barList.set(barList.indexOf(bar), updatedBar);
            }
        }
        updateMainScreen();
    }

    public void setFilterWindowToDeals(){
        filterPopUp.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                filterDealItems.clear();
                filterDealItems.addAll(tempFilterDealItems);
                filterDealAdapter.notifyDataSetChanged();
                updateMainScreen();
            }
        });
        filterListView.setAdapter(filterDealAdapter);
        filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                FilterListItem selectedFilterItem= (FilterListItem) adapterView.getItemAtPosition(position);
                for (FilterListItem item : filterDealItems){
                    if (item.getFilterType().equals(selectedFilterItem.getFilterType())){
                        if (item.getIsChecked()){
                            filterDealItems.get(filterDealItems.indexOf(selectedFilterItem)).setIsChecked(false);
                            filterDealAdapter.notifyDataSetChanged();
                        }
                        else {
                            filterDealItems.get(filterDealItems.indexOf(selectedFilterItem)).setIsChecked(true);
                            filterDealAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });

        filterApplyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tempFilterDealItems.clear();
                tempFilterDealItems.addAll(filterDealItems);
                filterPopUp.dismiss();
            }

        });
    }

    public void setFilterWindowToEvents(){
        filterPopUp.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                filterEventItems.clear();
                filterEventItems.addAll(tempFilterEventItems);
                filterEventAdapter.notifyDataSetChanged();
                updateMainScreen();
            }
        });
        filterListView.setAdapter(filterEventAdapter);
        filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                FilterListItem selectedFilterItem= (FilterListItem) adapterView.getItemAtPosition(position);
                for (FilterListItem item : filterEventItems){
                    if (item.getFilterType().equals(selectedFilterItem.getFilterType())){
                        if (item.getIsChecked()){
                            filterEventItems.get(filterEventItems.indexOf(selectedFilterItem)).setIsChecked(false);
                            filterEventAdapter.notifyDataSetChanged();
                        }
                        else {
                            filterEventItems.get(filterEventItems.indexOf(selectedFilterItem)).setIsChecked(true);
                            filterEventAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });

        filterApplyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tempFilterEventItems.clear();
                tempFilterEventItems.addAll(filterEventItems);
                filterPopUp.dismiss();
            }

        });
    }

}