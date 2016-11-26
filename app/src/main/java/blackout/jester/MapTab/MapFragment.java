package blackout.jester.MapTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import blackout.jester.R;

/**
 * Created by djoll on 11/18/2016.
 */

public class MapFragment extends Fragment {

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
