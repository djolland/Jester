package blackout.jester;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        dealListItems= new ArrayList<>(Arrays.asList(deals));

        mDealsAdapter = new DealArrayAdapter(this.getContext(), 0, dealListItems);


        ListView dealListView = (ListView) rootView.findViewById(
                R.id.listview_deals);
        dealListView.setAdapter(mDealsAdapter);

        return rootView;
    }
}

