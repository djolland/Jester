package blackout.jester;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
import blackout.jester.MapTab.MapFragment;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private Menu filterMenu;
    private ArrayList<BarData> barList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View rootView = findViewById(R.id.main_container);
        barList = new ArrayList<>();

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
        barSocialHouse.setAddress("2208 College St., Cedar Falls, IA");
        barSocialHouse.setHours("Monday - Saturday: 4PM - 2AM, Sunday: Closed");
        barSocialHouse.setContactInfo("(319) 266-3662");
        // * Adding Deals
        barSocialHouse.addDeal("2 for 1 Mixed Drinks", new BigDecimal(4.00), DealType.MIXEDDRINK, "Today");
        barSocialHouse.addDeal("Domestic Beers", new BigDecimal(3.00), DealType.BEER, "Today");
        // * Adding Events
        barSocialHouse.addEvent("DJ Sumptin", "8:00PM", "Today",EventType.LIVEMUSIC, new BigDecimal(0.00));
        barSocialHouse.addEvent("Lady Googa", "7:00PM", "Tomorrow", EventType.LIVEMUSIC, new BigDecimal(10.00));

        barSocialHouse.setAsFavorite(); // Testing favorites.

        // Blank Bar //
        BarData barBlankBar = new BarData("Blank Bar");
        // * Adding Deals
        barBlankBar.addDeal("Free Beer!", new BigDecimal(0.00), DealType.BEER, "Today");
        barBlankBar.addEvent("Kareoke", "12:00am-1:00pm", "25th Dec.", EventType.KARAOKE, new BigDecimal(10.00));



        // !!! Add your bars to the barList !!! //
        barList.add(barSocialHouse);
        barList.add(barBlankBar);

        /** End of Adding New Bars Section */

        // Setting up Bottom Bar navigation
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setMaxFixedTabs(4); //Default is 3, when this number is exceeded the bottombar
        //changes styles. we don't want that happening.
        mBottomBar.setItems(R.menu.bottombar_menu);

        // Drawing screen with declared bar data.
        updateScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        this.filterMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    /**
     * This method is called from updateBarData and onCreate.
     */
    private void updateScreen(){

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
                }
                else if (menuItemId == R.id.bb_menu_events) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, eventsFragment)
                            .commit();
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
            if (bar.getBarName().equalsIgnoreCase(updatedBar.getBarName())){
                barList.set(barList.indexOf(bar), updatedBar);
            }
        }
        updateScreen();
    }

}
