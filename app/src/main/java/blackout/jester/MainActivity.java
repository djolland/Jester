package blackout.jester;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiating Fragments
        final Fragment dealsFragment = new DealsFragment();
        final Fragment eventsFragment = new EventsFragment();
        final Fragment mapFragment = new MapFragment();

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setMaxFixedTabs(4);
        mBottomBar.setItems(R.menu.bottombar_menu);
        // Setting NavBar Style preferences
        //mBottomBar.setActiveTabColor(R.color.colorPrimaryDark);//Color that all nav icons will adopt when selected

        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_deals) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, dealsFragment)
                            .commit();
                }
                else if (menuItemId == R.id.bb_menu_events) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, eventsFragment)
                            .commit();
                }
                else if (menuItemId == R.id.bb_menu_map) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, mapFragment)
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    public static class DealsFragment extends Fragment {

        private ArrayAdapter<String> mDealsAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.deals_layout, container, false);

            String[] dealData = {
                    "Craft Beers $4 - 7:00PM to Close - Gingers",
                    "Vodka Red Bulls $2 - 8:00PM to Close - Sharky's",
                    "Bottomless Momosas $12 - 7:00AM to 9:00AM - Cafe Due",
                    "BlueRibbon Beer $1 - All Day - The Octopus",
                    "Whiskey Shots $3 - 9:00PM to Close - Social House",
                    "2 for 1 Wells $4 - 8:00PM to Close, Firday - Mugs",
                    "$3 Domestics - 4:00PM to 6:00PM Monday - Peppers",
                    "DRINK DEAL & PRICE - TIME/DATE - BAR NAME",
                    "DRINK DEAL & PRICE - TIME/DATE - BAR NAME",
                    "DRINK DEAL & PRICE - TIME/DATE - BAR NAME",
                    "DRINK DEAL & PRICE - TIME/DATE - BAR NAME",
            };

            ArrayList<String> dealsList = new ArrayList<>(Arrays.asList(dealData));

            mDealsAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.deal_textview,
                    R.id.list_item_deal,
                    dealsList
            );

            ListView dealListView = (ListView) rootView.findViewById(
                    R.id.listview_deals);
            dealListView.setAdapter(mDealsAdapter);

            return rootView;
        }
    }

    public static class EventsFragment extends Fragment {

        private ArrayAdapter<String> mEventsAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.events_layout, container, false);

            String[] eventData = {
                    "DJ Sumptin - 8:00PM - No Cover - Social House",
                    "Lady GooGa - 7:00PM - $10 Cover - The Octopus",
                    "The Glass Eyed Peas - 9:00PM - $5 Cover - The Library",
                    "The Rocking Eagles - 8:00PM - No Cover - The Pump Haus",
                    "Old Joe's Band - 7:30PM - No Cover - Toads",
                    "The Dish Water Blondes - 8:15PM - $5 - The Screaming Eagle",
                    "Karaoke - 8:00PM Saturday - No Cover - Social House",
                    "Trivia - 7:00PM Sunday - No Cover - The Octopus",
                    "Prize Raffle - 6:00PM - The Library",
            };

            ArrayList<String> eventsList = new ArrayList<>(Arrays.asList(eventData));

            mEventsAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.events_textview,
                    R.id.list_item_event,
                    eventsList
            );

            ListView eventListView = (ListView) rootView.findViewById(
                    R.id.listview_events);
            eventListView.setAdapter(mEventsAdapter);

            return rootView;
        }

        @Override
        public void onPause(){
            super.onPause();
        }
    }

    public static class MapFragment extends Fragment {

        private ArrayAdapter<String> mapListAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.map_layout, container, false);


            String[] barData = {
                    "Social House - 1 Mile",
                    "The Octopus - 1 Mile",
                    "Sharky's Fun House - 1 Mile",
                    "Suds Upstairs - 1 Mile",
                    "The Library - 1 Mile",
                    "Gingers - 1 Mile",
                    "Single Speed - 3 Miles",
                    "Whiskey Road - 3 Miles",
                    "The Pump Haus - 3 Miles",
                    "Peppers Grill - 5 Miles"
            };

            ArrayList<String> barList = new ArrayList<>(Arrays.asList(barData));

            mapListAdapter = new ArrayAdapter<String>(
                    this.getActivity(),
                    R.layout.map_textview,
                    R.id.list_item_map,
                    barList
            );

            ListView mapListView = (ListView) rootView.findViewById(
                    R.id.listview_map);
            mapListView.setAdapter(mapListAdapter);

            return rootView;
        }
    }
}
