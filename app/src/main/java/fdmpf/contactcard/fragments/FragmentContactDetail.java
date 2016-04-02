package fdmpf.contactcard.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fdmpf.contactcard.R;
import fdmpf.contactcard.contact.Contact;
import fdmpf.contactcard.contact.ContactDatabaseHelper;

/**
 * Created by Hans on 27-3-2016.
 */
public class FragmentContactDetail extends Fragment {

    private static final String ID_CONTACT = null;

    private int mIdContact = 0;

    ContactDatabaseHelper dbh;

    Contact contact;

    //view

    TextView textName;
    TextView email;

    TextView address;

    public static FragmentContactDetail newInstance(int idContact) {

        Bundle args = new Bundle();
        args.putInt(ID_CONTACT, idContact);
        FragmentContactDetail fragment = new FragmentContactDetail();
        fragment.setArguments(args);
        return fragment;
    }


    public FragmentContactDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mIdContact = getArguments().getInt(ID_CONTACT);

        }

        dbh = new ContactDatabaseHelper(getActivity().getApplicationContext());
        contact = dbh.getById(mIdContact);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);

        address = (TextView) view.findViewById(R.id.textAddress);
        address.setText(contact.getFirstName());


        return view;
    }
}
