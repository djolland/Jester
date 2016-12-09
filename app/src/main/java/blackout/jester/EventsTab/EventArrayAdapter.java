package blackout.jester.EventsTab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import blackout.jester.R;

/**
 * Adapts EventListitems to Main Event tab List
 */

public class EventArrayAdapter extends ArrayAdapter<EventListItem> {

    private Context context;
    private List<EventListItem> eventList;

    public EventArrayAdapter(Context context, int resource, ArrayList<EventListItem> objects){
        super(context, resource, objects);

        this.context = context;
        this.eventList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //get the event item we are dispalying
        EventListItem eventListItem = eventList.get(position);

        //inflate the layout for each item
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.event_item_layout, null);

        TextView eventDescriptionText = (TextView) view.findViewById(R.id.event_description_text);
        TextView eventTimeText = (TextView) view.findViewById(R.id.event_date_text);
        TextView eventCoverText = (TextView) view.findViewById(R.id.event_cover_text);
        ImageView barImage = (ImageView) view.findViewById(R.id.bar_image);

        //display event text... may need to trim this up.
        eventDescriptionText.setText(eventListItem.getEventTabDescriptionText());
        eventTimeText.setText(eventListItem.getEventTabTimeText());
        eventCoverText.setText(eventListItem.getEventTabCoverText());

        //Getting image resource
        int imageID = context.getResources()
                .getIdentifier(eventListItem.getBarImage(), "drawable", context.getPackageName());
        barImage.setImageResource(imageID);

        return view;

    }

}
