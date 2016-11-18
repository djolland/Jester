package blackout.jester;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        //Generating deals list
        //Intent dealsIntent = new Intent(getApplicationContext(), DealsFragment.class);
        ArrayList<DealListItem> dealListItems;

        Bundle dealsBundle = new Bundle();
        DealListItem[] deals = {
                new DealListItem("Bar Image","Bar Name","Deal Description"),
                new DealListItem("Other Bar Image", "Other Bar Name", "Deal Description")
        };

        dealListItems= new ArrayList<>(Arrays.asList(deals));
        dealsBundle.putSerializable("deals",dealListItems);

        // Instantiating Fragments
        final Fragment dealsFragment = new DealsFragment();
        dealsFragment.setArguments(dealsBundle);
        final Fragment eventsFragment = new EventsFragment();
        final Fragment mapFragment = new MapFragment();

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setMaxFixedTabs(4);
        mBottomBar.setItems(R.menu.bottombar_menu);
        // Setting NavBar Style preferences
        mBottomBar.setActiveTabColor("#a40b07");//Color that all nav icons will adopt when selected

        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_deals) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, dealsFragment)
                            .commit();
                }
                else if (menuItemId == R.id.bb_menu_events) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, eventsFragment)
                            .commit();
                }
                else if (menuItemId == R.id.bb_menu_map) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, mapFragment)
                            .commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_deals) {
                    // Do Nothing I guess
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

}
