package blackout.jester.BarProfileView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import blackout.jester.BarData.BarData;
import blackout.jester.R;


public class BarProfileFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String BAR_DATA = "barData";

    private BarData barData;

    //private OnFragmentInteractionListener mListener;

    public BarProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            barData = getArguments().getParcelable(BAR_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = this.getContext();

        // inflating the layout for each item
        View view = inflater.inflate(R.layout.bar_profile_layout, container, false);

        ImageView profileImage = (ImageView) view.findViewById(R.id.bar_profile_image);

        //Getting image resource
        int imageID = context.getResources()
                .getIdentifier(barData.getBarProfileImage(), "drawable", context.getPackageName());
        profileImage.setImageResource(imageID);

        return view;
    }

}
