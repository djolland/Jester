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

import blackout.jester.DealsTab.DealListItem;
import blackout.jester.R;

/**
 * Created by djolland on 11/26/2016.
 */

public class BarProfileDealArrayAdapter extends ArrayAdapter<DealListItem> {

    private Context context;
    private List<DealListItem> dealList;

    public BarProfileDealArrayAdapter(Context context, int resource, ArrayList<DealListItem> objects){
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
        View view = inflater.inflate(R.layout.profile_deal_list_item, null);

        //display deal text... may need to trim this up.
        TextView dealText = (TextView) view.findViewById(R.id.deal_description_text);
        dealText.setText(dealListItem.getProfileDealText());

        return view;

    }


}
