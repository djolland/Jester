package blackout.jester.Filter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import blackout.jester.BarData.DealType;
import blackout.jester.BarData.EventType;
import blackout.jester.R;

/**
 * Created by djolland on 12/6/2016.
 */

public class FilterEventArrayAdapter extends ArrayAdapter<FilterListItem> {

    private Context context;
    private ArrayList<FilterListItem> filterList;

    public FilterEventArrayAdapter(Context context, int resource, ArrayList<FilterListItem> objects) {
        super(context, resource, objects);

        this.context = context;
        this.filterList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the event item we are displaying
        FilterListItem dealListItem = filterList.get(position);

        //inflate the layout for each item
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.filter_item_layout, null);

        ImageView filterCheckBox = (ImageView) view.findViewById(R.id.filter_item_checkbox);
        TextView filterText = (TextView) view.findViewById(R.id.filter_item_text);

        //display filter text
        switch ((EventType) dealListItem.getFilterType()){
            case LIVEMUSIC:
                filterText.setText("Event Type - Live Music");
                break;
            case KARAOKE:
                filterText.setText("Event Type - Karaoke");
                break;
            case COMEDY:
                filterText.setText("Event Type - Comedy");
                break;
            case OTHER:
                filterText.setText("Event Type - Other");
                break;
            default:
                filterText.setText("Invalid Filter Found");
        }

        // Setting checkbox image
        int imageID;

        //Getting image resource
        if (dealListItem.getIsChecked()){
            imageID = context.getResources()
                    .getIdentifier("ic_filter_check_box_selected", "drawable", context.getPackageName());
        }
        else{
            imageID = context.getResources()
                    .getIdentifier("ic_filter_check_box_deselected", "drawable", context.getPackageName());
        }
        filterCheckBox.setImageResource(imageID);

        return view;
    }
}
