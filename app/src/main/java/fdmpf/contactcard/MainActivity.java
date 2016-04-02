package fdmpf.contactcard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import fdmpf.contactcard.contact.ContactDatabaseHelper;
import fdmpf.contactcard.contact.ContactFinder;
import fdmpf.contactcard.fragments.FragmentContactDetail;
import fdmpf.contactcard.fragments.FragmentContactList;

public class MainActivity extends AppCompatActivity implements
        FragmentContactList.OnFragmentInteractionListener {


    static ContactDatabaseHelper dbh;
    static SearchView searchViewAndroidActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction().replace(R.id.fragment_a, new FragmentContactList()).addToBackStack(null).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);
        final MenuItem searchViewItem = menu.findItem(R.id.action_search);

        searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnCloseListener(new SearchView.OnCloseListener() {

            @Override
            public boolean onClose() {
                searchViewAndroidActionBar.clearFocus();
                onBackPressed();
                return false;
            }
        });
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                searchViewHandler(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchViewHandler(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onFragmentInteraction(int id) {
        // Log.i("onFragmentInteraction", id);
        // Doorsturen naar andere Fragments ...
        FragmentContactDetail info = (FragmentContactDetail)
                getFragmentManager().findFragmentById(R.id.fragment_b);
        // In Landscape, info != null
        if (info != null) {

        } else {
            System.out.println("Hieronder het id:");
            System.out.print(id);
            Fragment newFragment = FragmentContactDetail.newInstance(id);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_a, newFragment);
            transaction.addToBackStack(null);

// Commit the transaction
            transaction.commit();

            /*
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_a, new FragmentContactDetail(), "NewFragmentTag");
            ft.commit();


            Intent intent = new Intent(this, FragmentContactDetail.class);
            intent.putExtra("id", id);
            startActivity(intent);
            // overridePendingTransition(R.anim.animation_right_to_center, R.anim.animation_center_to_left);
            */
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

    public void searchViewHandler(String searchString) {
        Fragment newFragment = FragmentContactList.newInstance(searchString);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_a, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //add 5 new random contacts

                    ContactFinder.dbh = new ContactDatabaseHelper(getApplicationContext());

                    ContactFinder.randomApiRequests(5);
                    System.out.print("666666664444");
                }
            });
            thread.start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
