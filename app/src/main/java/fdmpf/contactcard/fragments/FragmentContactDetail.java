package fdmpf.contactcard.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fdmpf.contactcard.R;

/**
 * Created by Hans on 27-3-2016.
 */
public class FragmentContactDetail extends Fragment {
    private TextView infoTextView;

    public FragmentContactDetail() {
        // Required empty public constructor
        Log.i("FragmentContactDetail()", "Constructor");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_detail, container, false);



        return view;
    }

    public void infoText(String msg)
    {
        Log.i("setInfoText()",msg);
        infoTextView.setText(msg);
    }
}
