package blackout.jester.BarProfileView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;

import blackout.jester.BarData.BarData;
import blackout.jester.DealsTab.DealArrayAdapter;
import blackout.jester.DealsTab.DealListItem;
import blackout.jester.R;


public class BarProfileFragment extends Fragment {
    // the fragment incoming arg key
    private static final String BAR_DATA = "thisBar";
    private static final String FOCUS_TAB = "focusTab";

    private BarData barData;
    private String focusTab;

    private BarProfileDealArrayAdapter mDealsAdapter;
    private ListView mDealListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            barData = getArguments().getParcelable(BAR_DATA);
            focusTab = getArguments().getString(FOCUS_TAB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bar_profile_layout, container, false);
        Context context = this.getContext();

        TabHost tabHost = (TabHost) view.findViewById(R.id.profile_tab_host);
        tabHost.setup();

        /** Setting Bar Profile Image **/
        ImageView profileImage = (ImageView) view.findViewById(R.id.bar_profile_image);

        // * Getting image resource
        int imageID = context.getResources()
                .getIdentifier(barData.getBarProfileImage(), "drawable", context.getPackageName());
        profileImage.setImageResource(imageID);

        /** Setting up Tab Navigation**/
        // * Info Tab
        TabHost.TabSpec infoSpec = tabHost.newTabSpec("Info");
        infoSpec.setContent(R.id.profile_info_tab);
        infoSpec.setIndicator("Info");
        tabHost.addTab(infoSpec);

        // * Deals Tab
        TabHost.TabSpec dealSpec = tabHost.newTabSpec("Deals");
        dealSpec.setContent(R.id.profile_deals_tab);
        dealSpec.setIndicator("Deals");
        tabHost.addTab(dealSpec);

        // * Event Tab
        TabHost.TabSpec eventSpec = tabHost.newTabSpec("Events");
        eventSpec.setContent(R.id.profile_events_tab);
        eventSpec.setIndicator("Events");
        tabHost.addTab(eventSpec);

        // Switch to a tab based on where the user is accessing the profile from.
        if (focusTab.equalsIgnoreCase("deals") || focusTab.equalsIgnoreCase("deal")){
            tabHost.setCurrentTabByTag("Deals");
        }
        else if (focusTab.equalsIgnoreCase("events") || focusTab.equalsIgnoreCase("event")) {
            tabHost.setCurrentTabByTag("Events");
        }
        else{
            tabHost.setCurrentTabByTag("Info");
        }

        //** Setting up Deals Tab view**//
        //Setting up adapter
        mDealsAdapter = new BarProfileDealArrayAdapter(
                this.getContext(), 0, barData.generateDealList());

        // Get a reference to the ListView, and attach this adapter to it.
        mDealListView = (ListView) view.findViewById(R.id.profile_deals_list);
        mDealListView.setAdapter(mDealsAdapter);
        // Set what happens when an item is selected
        mDealListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //TODO: Add Share Pop-up

            }
        });


        return view;
    }

}
