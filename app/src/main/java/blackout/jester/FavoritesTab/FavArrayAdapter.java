package blackout.jester.FavoritesTab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import blackout.jester.BarData.BarData;
import blackout.jester.R;

/**
 * Created by djoll on 12/3/2016.
 */

public class FavArrayAdapter extends ArrayAdapter<BarData> {

    private Context context;
    private ArrayList<BarData> favList;

    public FavArrayAdapter(Context context, int resource, ArrayList<BarData> objects) {
        super(context, resource, objects);

        this.context = context;
        this.favList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //get the bar we are displaying
        BarData favBar = favList.get(position);

        //inflate the layout for each item
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.favorite_item_layout, null);

        ImageView barImage = (ImageView) view.findViewById(R.id.bar_image);
        TextView barNameText = (TextView) view.findViewById(R.id.bar_name);
        TextView dealsText = (TextView) view.findViewById(R.id.deal_number);
        TextView eventText = (TextView) view.findViewById(R.id.event_number);
        TextView hoursText = (TextView) view.findViewById(R.id.bar_hours);

        //Set text fields
        barNameText.setText(favBar.getBarName());
        dealsText.setText("Deals: " + favBar.getDeals().size());
        eventText.setText("Events: " + favBar.getEvents().size());
        hoursText.setText(favBar.getHours());

        //Getting image resource
        int imageID = context.getResources()
                .getIdentifier(favBar.getBarListImage(), "drawable", context.getPackageName());
        barImage.setImageResource(imageID);

        return view;

    }

}
