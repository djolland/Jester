package blackout.jester;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private ArrayList<DealListItem> dealListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Generating deals list
        Bundle dealsBundle = new Bundle();
        DealListItem[] deals = {
                new DealListItem("Bar Image","Bar Name","Deal Description"),
                new DealListItem("Other Bar Image", "Other Bar Name", "Deal Description")
        };
        dealListItems= new ArrayList<>(Arrays.asList(deals));
        dealsBundle.putSerializable("deals",dealListItems);

        // Instantiating Fragments
        final Fragment dealsFragment = new DealsFragment();
        dealsFragment.setArguments(dealsBundle);
        final Fragment eventsFragment = new EventsFragment();
        final Fragment mapFragment = new MapFragment();

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setMaxFixedTabs(4);
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

    public class DealListItem{

        private String barImage;
        private String barName;
        private String dealText;

        public DealListItem(String image, String name, String deal){
            barImage = image;
            barName = name;
            dealText = deal;
        }

        // getters
        public String getBarImage(){return barImage;}
        public String getBarName(){return barName;}
        public String getDealText(){return dealText;}

    }

    class DealArrayAdapter extends ArrayAdapter<DealListItem>{

        private Context context;
        private List<DealListItem> dealList;

        public DealArrayAdapter(Context context, int resource, ArrayList<DealListItem> objects){
            super(context, resource, objects);

            this.context = context;
            this.dealList = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent){

            //get the deal item we are dispalying
            DealListItem dealListItem = dealList.get(position);

            //inflate the layout for each item
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.deal_item_layout, null);

            TextView dealText = (TextView) view.findViewById(R.id.deal_text);
            ImageView barImage = (ImageView) view.findViewById(R.id.bar_image);

            //display deal text... may need to trim this up.
            dealText.setText(dealListItem.getDealText());

            //Getting image resource
            int imageID = context.getResources()
                    .getIdentifier(dealListItem.getBarImage(), "drawable", context.getPackageName());
            barImage.setImageResource(imageID);

            return view;

        }

    }

    public static class DealsFragment extends Fragment {

        private ArrayAdapter<DealListItem> mDealsAdapter;
        private ArrayList<DealListItem> dealListItems;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.deals_layout, container, false);

            //Getting Deal List Data
            dealListItems= getArguments().getSerializable("deals");

            mDealsAdapter = new DealArrayAdapter(this.getContext(), 0, dealListItems);

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
