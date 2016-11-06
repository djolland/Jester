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
        mBottomBar.setItems(R.menu.bottombar_menu);
        // Setting NavBar Style preferences
        mBottomBar.setActiveTabColor("#a40b07");//Color that all nav icons will adopt when selected

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
                    "Craft Beers - $4",
                    "Vodka Red Bulls - $2",
                    "Bottomless Momosas - $12",
                    "BlueRibbon Beer - $1",
                    "Whiskey Shots - $3",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
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
                    "DJ Sumptin - 8:00PM - No Cover",
                    "Lady Gaga - 7:00PM - $100 Cover",
                    "PERFORMER - TIME - PRICE",
                    "PERFORMER - TIME - PRICE",
                    "PERFORMER - TIME - PRICE",
                    "PERFORMER - TIME - PRICE",
                    "PERFORMER - TIME - PRICE",
                    "PERFORMER - TIME - PRICE",
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
                    "Social House - 5 Miles",
                    "The Library - 5 Miles",
                    "Single Speed - 7 Miles",
                    "BAR - DISTANCE",
                    "BAR - DISTANCE",
                    "BAR - DISTANCE",
                    "BAR - DISTANCE",
                    "BAR - DISTANCE",
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
