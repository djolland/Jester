package blackout.jester.FavoritesTab;

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
 * Created by djoll on 12/2/2016.
 */

public class FavoritesFragment extends Fragment {

    private static final String FAVS = "favorites";
    public static final int REQUEST_CODE = 1;

    private FavArrayAdapter mFavAdpater;
    private ListView mListView;
    private ArrayList<BarData> barFavList;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.favorites_layout, container, false);
        mainActivity = (MainActivity) getActivity();

        // Getting Fav Bar List Data
        barFavList = getArguments().getParcelableArrayList(FAVS);
        // Setting up custom ArrayAdapter
        mFavAdpater = new FavArrayAdapter(this.getContext(), 0, barFavList);

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_favorites);
        mListView.setAdapter(mFavAdpater);
        // Calling the Bar Profile Activity
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BarData favBar = (BarData) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), BarProfileActivity.class)
                        .putExtra("thisBar", favBar)
                        .putExtra("focusTab", "deals");
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
