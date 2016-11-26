package blackout.jester.DealsTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import blackout.jester.BarProfileView.BarProfileActivity;
import blackout.jester.R;

/**
 * Created by djoll on 11/18/2016.
 */

public class DealsFragment extends Fragment {

    private ArrayAdapter<DealListItem> mDealsAdapter; //need to replace with custom adapter
    private ListView mListView;
    private ArrayList<DealListItem> dealListItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.deals_layout, container, false);


        //Getting Deal List Data
        dealListItems = getArguments().getParcelableArrayList("deals");

        mDealsAdapter = new DealArrayAdapter(this.getContext(), 0, dealListItems);

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_deals);
        mListView.setAdapter(mDealsAdapter);
        // We'll call our MainActivity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DealListItem dealListItem = (DealListItem) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), BarProfileActivity.class)
                        .putExtra("thisBar", dealListItem.getBarData());
                startActivity(intent);
            }
        });

        return rootView;
    }
}

