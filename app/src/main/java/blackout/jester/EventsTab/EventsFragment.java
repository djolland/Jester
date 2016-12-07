package blackout.jester.EventsTab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import blackout.jester.BarData.BarData;
import blackout.jester.BarProfileView.BarProfileActivity;
import blackout.jester.MainActivity;
import blackout.jester.R;

/**
 * Created by djolland on 11/18/2016.
 */

public class EventsFragment extends Fragment {

    private static final String EVENTS = "events";
    public static final int REQUEST_CODE = 1;

    private EventArrayAdapter mEventsAdapter;
    private ListView mListView;
    private ArrayList<EventListItem> eventListItems;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.events_layout, container, false);
        mainActivity = (MainActivity) getActivity();

        //Getting Event List Data
        eventListItems = getArguments().getParcelableArrayList(EVENTS);
        // Setting up custom ArrayAdapter
        mEventsAdapter = new EventArrayAdapter(this.getContext(), 0, eventListItems);

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_events);
        mListView.setAdapter(mEventsAdapter);
        // Calling the Bar Profile Activity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EventListItem eventListItem = (EventListItem) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), BarProfileActivity.class)
                        .putExtra("thisBar", eventListItem.getBarData())
                        .putExtra("focusTab", "events");
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

