package fdmpf.contactcard.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import fdmpf.contactcard.R;
import fdmpf.contactcard.contact.Contact;
import fdmpf.contactcard.contact.ContactDatabaseHelper;
import fdmpf.contactcard.http.Http;

/**
 * Created by Hans on 27-3-2016.
 */
public class FragmentContactDetail extends Fragment {

    private static final String ID_CONTACT = null;

    private int mIdContact = 0;

    ContactDatabaseHelper dbh;

    Contact contact;

    //view
    ImageView image;
    TextView email;
    TextView name;
    TextView postalCode;
    TextView city;
    TextView phone;
    TextView cell;
    TextView address;
    FloatingActionButton delete;
    Http http;
    FrameLayout f = null;

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
        http = Http.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);

        if (container != null) {
            f = (FrameLayout) container.findViewById(R.id.fragment_b);
        }

        delete = (FloatingActionButton) view.findViewById(R.id.fab);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("clicked to delete");
                dbh.delete(mIdContact);

                if(f != null && f instanceof FrameLayout) {
                    FragmentContactList.arrayAdapter.addAll(dbh.getAllContacts());
                    FragmentContactList.arrayAdapter.notifyDataSetChanged();
                    f.removeAllViews();
                }


            }
        });


        image = (ImageView) view.findViewById(R.id.imageView);
        http.getImage(contact.getImageUrl(), new Http.ImageResponseListener() {
            @Override
            public void getResult(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }
        });

        name = (TextView) view.findViewById(R.id.textName);
        name.setText(contact.getFirstName() + ' ' + contact.getLastName());

        email = (TextView) view.findViewById(R.id.textEmail);
        email.setText(contact.getEmail());

        phone = (TextView) view.findViewById(R.id.textPhone);
        phone.setText(contact.getPhone());

        cell = (TextView) view.findViewById(R.id.textCell);
        cell.setText(contact.getCell());

        address = (TextView) view.findViewById(R.id.textAddress);
        address.setText(contact.getAddress());

        postalCode = (TextView) view.findViewById(R.id.textZipCode);
        postalCode.setText(contact.getPostalCode());

        city = (TextView) view.findViewById(R.id.textCity);
        city.setText(contact.getCity());

        return view;
    }
}
