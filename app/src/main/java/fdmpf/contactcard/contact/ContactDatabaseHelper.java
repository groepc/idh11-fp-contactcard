package fdmpf.contactcard.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by perryfaro on 28-03-16.
 */
public class ContactDatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 2;

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade(DATABASE_VERSION);
    }


    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactList = new ArrayList<Contact>();

        Cursor cursor = fetchAllContacts();
        cursor.moveToFirst();
        while( cursor.moveToNext() ) {
            Contact contact = new Contact();

            contact.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
            contact.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
            contact.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            contact.setImageUrl(cursor.getString(cursor.getColumnIndex("photoUrl")));

            contactList.add(contact);
        }

        return contactList;
    }

    private Cursor fetchAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Contacts";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        db.close();
        return c;
    }

    public void addContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put("firstName", contact.getFirstName());
        values.put("lastName", contact.getLastName());
        values.put("email", contact.getEmail());
        values.put("photoUrl", contact.getImageUrl());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Contacts", null, values);
        db.close();
    }

}
