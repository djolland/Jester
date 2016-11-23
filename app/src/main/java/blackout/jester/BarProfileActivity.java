package blackout.jester;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BarProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_profile);
        View rootView = findViewById(R.id.bar_profile_container);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            Bundle arguments = new Bundle();

            BarProfileFragment fragment = new BarProfileFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bar_profile_container, fragment)
                    .commit();
        }

        Toolbar mToolbar =
                (Toolbar) rootView.findViewById(R.id.profile_toolbar);

        setSupportActionBar(mToolbar);
        //getActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);

        // Get a support ActionBar corresponding to this toolbar
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDefaultDisplayHomeAsUpEnabled(true);// setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
