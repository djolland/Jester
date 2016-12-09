package blackout.jester.DealsTab;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import blackout.jester.R;

/**
 * Created by djolland on 11/16/2016.
 */

public class DealArrayAdapter extends ArrayAdapter<DealListItem> {

    private Context context;
    private ArrayList<DealListItem> dealList;

    public DealArrayAdapter(Context context, int resource, ArrayList<DealListItem> objects){
        super(context, resource, objects);

        this.context = context;
        this.dealList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //get the deal item we are displaying
        DealListItem dealListItem = dealList.get(position);

        //inflate the layout for each item
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.deal_item_layout, null);

        TextView dealDescriptionText = (TextView) view.findViewById(R.id.deal_description_text);
        TextView dealPriceText = (TextView) view.findViewById(R.id.deal_price_text);
        TextView dealDateText = (TextView) view.findViewById(R.id.deal_date_text);
        ImageView barImage = (ImageView) view.findViewById(R.id.bar_image);

        //display deal text... may need to trim this up.
        dealDescriptionText.setText(dealListItem.getDealTabDescriptionText());
        dealPriceText.setText(dealListItem.getDealTabPriceText());
        dealDateText.setText(dealListItem.getDealTabDateText());

        //Getting image resource
        int imageID = context.getResources()
                .getIdentifier(dealListItem.getBarImage(), "drawable", context.getPackageName());
        barImage.setImageResource(imageID);

        return view;
    }

}
