package blackout.jester;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;

import blackout.jester.BarData.BarData;
import blackout.jester.BarData.DealType;
import blackout.jester.BarData.EventType;
import blackout.jester.DealsTab.DealListItem;
import blackout.jester.DealsTab.DealsFragment;
import blackout.jester.EventsTab.EventListItem;
import blackout.jester.EventsTab.EventsFragment;
import blackout.jester.FavoritesTab.FavoritesFragment;
import blackout.jester.Filter.FilterClass;
import blackout.jester.Filter.FilterDealArrayAdapter;
import blackout.jester.Filter.FilterEventArrayAdapter;
import blackout.jester.Filter.FilterListItem;
import blackout.jester.MapTab.MapFragment;

public class MainActivity extends AppCompatActivity {

    // Main UI variables
    private BottomBar mBottomBar;
    private Menu mainActionBarMenu;
    private View rootView;
    // Filter UI variables
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

        /** Add New Bars in this section.
         *      - Be sure to add new bars to the barList at the end of this section.
         *      - To add an image asset copy it in windows file explorer, then right click on
         *        the res/drawable directory in the project view and paste. (yes it's that easy).
         *          * Make sure the image is in the drawable directory!
         *          * When instantiating a new bar you will reference the image by its file name
         *            WITHOUT its file extension.
         *          * Keep in mind that each bar will have 2 images, one that will appear on the
         *            deal and events lists (this one is small and square) and another that will
         *            appear in the bar profile view (this one can be larger and more detailed).
         **/

        // Social House //
        BarData barSocialHouse =
                new BarData("Social House",         // Bar Name
                            "social_house_list",    // Bar Image to appear in lists (small)
                            "social_house_profile"  // Bar Image to appear on profile (big)
        );
        // * Adding Info
        barSocialHouse.setDisatnceMiles(5);
        barSocialHouse.setAddress("2208 College St., Cedar Falls, IA");
        barSocialHouse.setHours("Monday - Saturday: 4PM - 2AM, Sunday: Closed");
        barSocialHouse.setContactInfo("(319) 266-3662");
        // * Adding Deals
        barSocialHouse.addDeal("2 for 1 Mixed Drinks", new BigDecimal(4.00), DealType.MIXEDDRINK, "Today");
        barSocialHouse.addDeal("Domestic Beers", new BigDecimal(3.00), DealType.BEER, "Today");
        // * Adding Events
        barSocialHouse.addEvent("DJ Sumptin", "8:00PM", "Today", EventType.LIVEMUSIC, new BigDecimal(0.00));
        barSocialHouse.addEvent("Lady Googa", "7:00PM", "Tomorrow", EventType.LIVEMUSIC, new BigDecimal(10.00));

        barSocialHouse.setAsFavorite(); // Testing favorites.

        // Blank Bar //
        BarData barBlankBar = new BarData("Blank Bar");
        // * Adding Deals
        barBlankBar.addDeal("Free Beer!", new BigDecimal(0.00), DealType.BEER, "Today");

        barBlankBar.addEvent("Karaoke", "12:00am-1:00pm", "25th Dec.", EventType.KARAOKE, new BigDecimal(10.00));


        // !!! Add your bars to the barList !!! //
        barList.add(barSocialHouse);
        barList.add(barBlankBar);

        /** End of Adding New Bars Section */


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

        // Setting up Bottom Bar navigation
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setMaxFixedTabs(4); //Default is 3, when this number is exceeded the bottombar
                                       //changes styles. we don't want that happening.
        mBottomBar.setItems(R.menu.bottombar_menu);

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
                    // Do Nothing I guess
                }
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