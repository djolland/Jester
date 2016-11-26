package blackout.jester.DealsTab;

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
 * Created by djoll on 11/16/2016.
 */

public class DealArrayAdapter extends ArrayAdapter<DealListItem> {

    private Context context;
    private List<DealListItem> dealList;

    public DealArrayAdapter(Context context, int resource, ArrayList<DealListItem> objects){
        super(context, resource, objects);

        this.context = context;
        this.dealList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //get the deal item we are dispalying
        DealListItem dealListItem = dealList.get(position);

        //inflate the layout for each item
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.deal_item_layout, null);

        TextView dealText = (TextView) view.findViewById(R.id.deal_text);
        ImageView barImage = (ImageView) view.findViewById(R.id.bar_image);

        //display deal text... may need to trim this up.
        dealText.setText(dealListItem.getDealText());

        //Getting image resource
        int imageID = context.getResources()
                .getIdentifier(dealListItem.getBarImage(), "drawable", context.getPackageName());
        barImage.setImageResource(imageID);

        return view;

    }

}
