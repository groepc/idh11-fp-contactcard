package fdmpf.contactcard;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import fdmpf.contactcard.contact.Contact;
import fdmpf.contactcard.contact.ContactDatabaseHelper;
import fdmpf.contactcard.fragments.FragmentContactDetail;
import fdmpf.contactcard.fragments.FragmentContactList;

public class MainActivity extends AppCompatActivity implements
        FragmentContactList.OnFragmentInteractionListener {


    static ContactDatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction().replace(R.id.fragment_a,new FragmentContactList()).addToBackStack(null).commit();


        dbh = new ContactDatabaseHelper(getApplicationContext());

        Contact contact1 = new Contact();
        contact1.setFirstName("Perry");
        contact1.setLastName("Faro");
        contact1.setEmail("email@email.com");
        dbh.addContact(contact1);

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
    public void onFragmentInteraction(int id) {
       // Log.i("onFragmentInteraction", id);
        // Doorsturen naar andere Fragments ...
        FragmentContactDetail info = (FragmentContactDetail)
                getFragmentManager().findFragmentById(R.id.fragment_b);
        // In Landscape, info != null
        if (info != null) {

        } else {

            Fragment newFragment = new FragmentContactDetail();
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
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
