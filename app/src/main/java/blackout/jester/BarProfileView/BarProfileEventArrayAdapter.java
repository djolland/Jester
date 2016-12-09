package blackout.jester.BarProfileView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import blackout.jester.EventsTab.EventListItem;
import blackout.jester.R;

/**
 * Adapts EventListItems for display in the Events List in the Bar Profile view.
 */

public class BarProfileEventArrayAdapter extends ArrayAdapter<EventListItem> {

    private Context context;
    private List<EventListItem> eventList;

    public BarProfileEventArrayAdapter(Context context, int resource, ArrayList<EventListItem> objects){
        super(context, resource, objects);

        this.context = context;
        this.eventList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //get the deal item we are dispalying
        EventListItem eventListItem = eventList.get(position);

        //inflate the layout for each item
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.profile_event_list_item, null);

        //display event text... may need to trim this up.
        TextView dealText = (TextView) view.findViewById(R.id.event_description_text);
        dealText.setText(eventListItem.getProfileEventText());

        return view;

    }


}
