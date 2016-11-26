package blackout.jester.EventsTab;

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

public class EventsFragment extends Fragment {

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

