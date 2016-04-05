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
    ArrayList<Contact> mContactArrayList;
    Http http;

    public ContactAdapter(Context context, LayoutInflater layoutInflater)
    {
        mContext = context;
        mInflator = layoutInflater;
        //mContactArrayList = personArrayList;
        http = Http.getInstance();
    }

    public void addAll(ArrayList<Contact> result) {
        if(mContactArrayList==null){
            mContactArrayList = new ArrayList<Contact>();
        }
        mContactArrayList.addAll(result);
        notifyDataSetChanged();
    }

    public void addContact (Contact contact) {
        if(mContactArrayList==null){
            mContactArrayList = new ArrayList<Contact>();
        }
        mContactArrayList.add(contact);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int size = mContactArrayList.size();
        Log.i("getCount()", "=" + size);
        return size;
    }

    @Override
    public Contact getItem(int position) {
        System.out.println("we komen hier nu");
        System.out.print(mContactArrayList);
        System.out.println(getCount());
        System.out.println(position);
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

        return convertView;
    }

    private static class ViewHolder {
        public ImageView imageView;
        public TextView firstname;
        public TextView lastname;
    }

}
