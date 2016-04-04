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
    private static final int DATABASE_VERSION = 1;

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<Contact> getAllContacts() {
        Cursor cursor = fetchAllContacts();
        return loopResult(cursor);
    }

    public ArrayList<Contact> searchContacts(String searchString) {
        String query = "SELECT * FROM Contacts WHERE firstName LIKE '%" + searchString + "%' OR lastName LIKE '%" + searchString + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return loopResult(cursor);
    }

    private Cursor fetchAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM Contacts";

        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public Contact getById(int id) {
        String query = "SELECT * FROM Contacts WHERE contactId='" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        return loopResult(cursor).get(0);
    }

    protected Contact createContact(Cursor cursor) {
        Contact contact = new Contact();

        contact.setId(cursor.getInt(cursor.getColumnIndex("contactId")));
        contact.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
        contact.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
        contact.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        contact.setImageUrl(cursor.getString(cursor.getColumnIndex("photoUrl")));
        contact.setAddress(cursor.getString(cursor.getColumnIndex("address")));
        contact.setPostalCode(cursor.getString(cursor.getColumnIndex("postalCode")));
        contact.setCity(cursor.getString(cursor.getColumnIndex("city")));
        contact.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
        contact.setCell(cursor.getString(cursor.getColumnIndex("cell")));

        return contact;
    }

    private ArrayList<Contact> loopResult(Cursor cursor) {
        ArrayList<Contact> contactList = new ArrayList<>();

        if (!cursor.moveToFirst()) {
            return contactList;
        }

        do {
            contactList.add(createContact(cursor));
        } while (cursor.moveToNext());

        cursor.close();

        return contactList;
    }

    public void addContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.putNull("contactId");
        values.put("firstName", contact.getFirstName());
        values.put("lastName", contact.getLastName());
        values.put("email", contact.getEmail());
        values.put("photoUrl", contact.getImageUrl());
        values.put("phone", contact.getPhone());
        values.put("cell", contact.getCell());
        values.put("address", contact.getAddress());
        values.put("postalCode", contact.getPostalCode());
        values.put("city", contact.getCity());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Contacts", null, values);
        db.close();
    }

}
