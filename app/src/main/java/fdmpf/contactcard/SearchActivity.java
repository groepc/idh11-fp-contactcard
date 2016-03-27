package fdmpf.contactcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import fdmpf.contactcard.fragments.FragmentSearchDetail;
import fdmpf.contactcard.fragments.FragmentSearchList;

/**
 * Created by Hans on 27-3-2016.
 */
public class SearchActivity extends AppCompatActivity implements
        FragmentSearchList.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_new_contact) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String msg) {
        Log.i("onFragmentInteraction", msg);
        // Doorsturen naar andere Fragments ...
        FragmentSearchDetail info = (FragmentSearchDetail)
                getFragmentManager().findFragmentById(R.id.fragment_b);
        // In Landscape, info != null
        if (info != null ) {
            info.infoText(msg);
        }
    }
}
