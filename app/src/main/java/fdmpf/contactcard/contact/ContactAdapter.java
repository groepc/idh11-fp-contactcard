package fdmpf.contactcard.contact;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fdmpf.contactcard.R;
import fdmpf.contactcard.http.Http;

/**
 * Created by Hans on 27-3-2016.
 */
public class ContactAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflator;
    static ArrayList<Contact> mContactArrayList;
    Http http;

    public ContactAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Contact> personArrayList)
    {
        mContext = context;
        mInflator = layoutInflater;
        mContactArrayList = personArrayList;
        http = Http.getInstance();
    }

    public static void addContact (Contact contact) {
        mContactArrayList.add(contact);
    }

    @Override
    public int getCount() {
        int size = mContactArrayList.size();
        Log.i("getCount()", "=" + size);
        return size;
    }

    @Override
    public Contact getItem(int position) {
        return mContactArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if(convertView == null) {
            convertView = mInflator.inflate(R.layout.contact_listview_row, null);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.firstname = (TextView) convertView.findViewById(R.id.firstname);
            viewHolder.lastname = (TextView) convertView.findViewById(R.id.lastname);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = mContactArrayList.get(position);

        viewHolder.firstname.setText(contact.getFirstName());
        viewHolder.lastname.setText(contact.getLastName());


        http.getImage(contact.getImageUrl(), new Http.ImageResponseListener() {
            @Override
            public void getResult(Bitmap bitmap) {
                viewHolder.imageView.setImageBitmap(bitmap);
            }
        });


        //viewHolder.imageView = null; //setImageBitmap(person.imageUrl);

        return convertView;
    }

    private static class ViewHolder {
        public ImageView imageView;
        public TextView firstname;
        public TextView lastname;
    }

}
