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
import blackout.jester.DealsTab.DealListItem;
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

        //get the deal item we are dispalying
        BarData favBar = favList.get(position);

        //inflate the layout for each item
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.deal_item_layout, null);

        TextView dealText = (TextView) view.findViewById(R.id.deal_text);
        ImageView barImage = (ImageView) view.findViewById(R.id.bar_image);

        //display deal text... may need to trim this up.
        dealText.setText(favBar.getBarName());

        //Getting image resource
        int imageID = context.getResources()
                .getIdentifier(favBar.getBarListImage(), "drawable", context.getPackageName());
        barImage.setImageResource(imageID);

        return view;

    }

}
