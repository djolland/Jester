package blackout.jester.BarProfileView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import blackout.jester.BarData.BarData;
import blackout.jester.R;

public class BarProfileActivity extends AppCompatActivity {

    private BarData thisBar;
    private Menu profileMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_profile);
        View rootView = findViewById(R.id.bar_profile_container);

        Bundle args = getIntent().getExtras();

        thisBar = args.getParcelable("thisBar");

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            BarProfileFragment fragment = new BarProfileFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.bar_profile_container, fragment)
                    .commit();
        }

        Toolbar mToolbar =
                (Toolbar) rootView.findViewById(R.id.profile_toolbar);
        setSupportActionBar(mToolbar);

        // Get a support ActionBar corresponding to this toolbar
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(thisBar.getBarName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.profileMenu = menu;
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        if (thisBar.isFavorite()) {
            menu.findItem(R.id.action_favorite).setIcon(R.drawable.ic_fav_full_tinted);
        }
        else{
            menu.findItem(R.id.action_favorite).setIcon(R.drawable.ic_fav_empty_tinted);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  android.R.id.home:
                this.finish();
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" icon
                if (thisBar.isFavorite()) {
                    thisBar.removeFromFavorites();
                    profileMenu.findItem(R.id.action_favorite)
                            .setIcon(R.drawable.ic_fav_empty_tinted);
                }
                else{
                    thisBar.addToFavorites();
                    profileMenu.findItem(R.id.action_favorite)
                            .setIcon(R.drawable.ic_fav_full_tinted);
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
