package blackout.jester;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by djoll on 11/18/2016.
 */

public class DealsFragment extends Fragment {

    //private ArrayAdapter<DealListItem> mDealsAdapter; //need to replace with custom adapter
    //private ArrayList<DealListItem> dealListItems = new ArrayList<>();
    private ArrayAdapter<String> mDealsAdapter;
    private ArrayList<String> dealListItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.deals_layout, container, false);

        //Getting Deal List Data
        //dealListItems = getArguments().("deals");

        dealListItems.add("DEAL TEST TEXT - TEST");
        dealListItems.add("DEAL TEST TEXT - TEST");
        dealListItems.add("DEAL TEST TEXT - TEST");
        dealListItems.add("DEAL TEST TEXT - TEST");

        //mDealsAdapter = new DealArrayAdapter(this.getContext(), 0, dealListItems);
        mDealsAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.deal_textview,
                R.id.list_item_deal,
                dealListItems
        );

        ListView dealListView = (ListView) rootView.findViewById(
                R.id.listview_deals);
        dealListView.setAdapter(mDealsAdapter);

        return rootView;
    }
}

