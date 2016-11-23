package blackout.jester;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by djoll on 11/18/2016.
 */

public class DealsFragment extends Fragment {

    private ArrayAdapter<DealListItem> mDealsAdapter; //need to replace with custom adapter
    private ArrayList<DealListItem> dealListItems = new ArrayList<>();
    private ListView mListView;

//    /**
//     * A callback interface that all activities containing this fragment must
//     * implement. This mechanism allows activities to be notified of item
//     * selections.
//     */
//    public interface Callback {
//        /**
//         * DetailFragmentCallback for when an item has been selected.
//         */
//        public void onDealItemSelected(Uri barName);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.deals_layout, container, false);


        //Getting Deal List Data
        //dealListItems = getArguments().("deals");
        ArrayList<DealListItem> dealListItems;
        DealListItem[] deals = {
                new DealListItem("social_house_logo","Social House", "2 for 1 Mixed Drinks"),
                new DealListItem("social_house_logo", "Social House", "$3 Domestic Beers")
        };

        dealListItems = new ArrayList<>(Arrays.asList(deals));
        mDealsAdapter = new DealArrayAdapter(this.getContext(), 0, dealListItems);

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_deals);
        mListView.setAdapter(mDealsAdapter);
        // We'll call our MainActivity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getActivity(), BarProfileActivity.class);
                       // .setData(barName);
                startActivity(intent);            }
        });

        return rootView;
    }
}

