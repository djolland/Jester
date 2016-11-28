package blackout.jester.EventsTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import blackout.jester.BarProfileView.BarProfileActivity;
import blackout.jester.DealsTab.DealListItem;
import blackout.jester.R;

/**
 * Created by djoll on 11/18/2016.
 */

public class EventsFragment extends Fragment {

    private static final String EVENTS = "events";

    private EventArrayAdapter mEventsAdapter;
    private ListView mListView;
    private ArrayList<EventListItem> eventListItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.events_layout, container, false);

        //Getting Deal List Data
        eventListItems = getArguments().getParcelableArrayList(EVENTS);

        mEventsAdapter = new EventArrayAdapter(this.getContext(), 0, eventListItems);

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_events);
        mListView.setAdapter(mEventsAdapter);
        // We'll call our MainActivity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EventListItem eventListItem = (EventListItem) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), BarProfileActivity.class)
                        .putExtra("thisBar", eventListItem.getBarData())
                        .putExtra("focusTab", "events");
                startActivity(intent);
            }
        });


        return rootView;
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}

