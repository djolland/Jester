package blackout.jester;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DealsFragment())
                    .commit();
        }

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_deals) {

                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_events) {
                    // The user reselected the "Recents" tab. React accordingly.
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    public static class DealsFragment extends Fragment {

        private ArrayAdapter<String> mDealsAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.deals_layout, container, false);

            String[] dealData = {
                    "Craft Beers - $4",
                    "Vodka Red Bulls - $2",
                    "Bottomless Momosas - $12",
                    "BlueRibbon Beer - $1",
                    "Whiskey Shots - $3",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
                    "DRINK - PRICE - DATE",
            };

            ArrayList<String> dealsList = new ArrayList<>(Arrays.asList(dealData));

            mDealsAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.deal_textview,
                    R.id.list_item_deal,
                    dealsList
            );

            ListView dealListView = (ListView) rootView.findViewById(
                    R.id.listview_deals);
            dealListView.setAdapter(mDealsAdapter);

            return rootView;
        }

    }
}
