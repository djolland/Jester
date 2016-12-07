package blackout.jester.DealsTab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import blackout.jester.BarData.BarData;
import blackout.jester.BarProfileView.BarProfileActivity;
import blackout.jester.MainActivity;
import blackout.jester.R;

/**
 * Created by djolland on 11/18/2016.
 */

public class DealsFragment extends Fragment {
    // the fragments incoming arg key
    private static final String DEALS = "deals";
    public static final int REQUEST_CODE = 1;

    private DealArrayAdapter mDealsAdapter;
    private ListView mListView;
    private ArrayList<DealListItem> dealListItems;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.deals_layout, container, false);
        mainActivity = (MainActivity) getActivity();

        // Getting Deal List Data
        dealListItems = getArguments().getParcelableArrayList(DEALS);
        // Setting up custom ArrayAdapter
        mDealsAdapter = new DealArrayAdapter(this.getContext(), 0, dealListItems);

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_deals);
        mListView.setAdapter(mDealsAdapter);
        // Calling the Bar Profile Activity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DealListItem dealListItem = (DealListItem) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), BarProfileActivity.class)
                        .putExtra("thisBar", dealListItem.getBarData())
                        .putExtra("focusTab", "deals");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                BarData updatedBar = data.getParcelableExtra("result");
                mainActivity.updateBarData(updatedBar);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Do nothing I guess...
            }
        }
    }
}

