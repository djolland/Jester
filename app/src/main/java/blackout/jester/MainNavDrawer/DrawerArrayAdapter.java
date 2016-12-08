package blackout.jester.MainNavDrawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import blackout.jester.R;

/**
 * Created by djolland on 12/8/2016.
 */

public class DrawerArrayAdapter  extends ArrayAdapter<DrawerListItem> {

    private Context context;
    private ArrayList<DrawerListItem> itemList;

    public DrawerArrayAdapter(Context context, int resource, ArrayList<DrawerListItem> objects) {
        super(context, resource, objects);

        this.context = context;
        this.itemList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the deal item we are displaying
        DrawerListItem drawerListItem = itemList.get(position);

        //inflate the layout for each item
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.drawer_item_layout, null);

        TextView dealText = (TextView) view.findViewById(R.id.drawer_item_text);
        ImageView barImage = (ImageView) view.findViewById(R.id.drawer_icon);

        //display drawer item text
        dealText.setText(drawerListItem.getText());

        //Getting image resource
        int imageID = context.getResources()
                .getIdentifier(drawerListItem.getIcon(), "drawable", context.getPackageName());
        barImage.setImageResource(imageID);

        return view;
    }
}