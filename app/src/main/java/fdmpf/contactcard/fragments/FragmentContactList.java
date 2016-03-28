package fdmpf.contactcard.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import fdmpf.contactcard.R;
import fdmpf.contactcard.contact.Contact;
import fdmpf.contactcard.contact.ContactAdapter;
import fdmpf.contactcard.contact.ContactDatabaseHelper;

/**
 * Created by Hans on 27-3-2016.
 */
public class FragmentContactList extends Fragment implements AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match

    private ArrayList<Contact> contactList = new ArrayList<>();
    private ListView contactListView;
    private ContactAdapter arrayAdapter;

    private OnFragmentInteractionListener mListener;


    public FragmentContactList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContactDatabaseHelper dbh = new ContactDatabaseHelper(getActivity().getApplicationContext());
        contactList = dbh.getAllContacts();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("onCreateView()", "");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        arrayAdapter = new ContactAdapter(getActivity().getApplicationContext(), inflater, contactList);

        contactListView = (ListView) view.findViewById(R.id.listView);
        contactListView.setOnItemClickListener(this);
        contactListView.setAdapter(arrayAdapter);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener ...");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = arrayAdapter.getItem(position);
        mListener.onFragmentInteraction(contact.getId());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int msg);
    }
}
