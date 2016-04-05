package fdmpf.contactcard.contact;

import org.json.JSONObject;

import fdmpf.contactcard.fragments.FragmentContactList;

/**
 * Created by perryfaro on 05-04-16.
 */
public class RandomUser {

    static String url = "https://randomuser.me/api/";

    static ContactDatabaseHelper dbh = null;


    public static String randomUserUrl() {

        return url;
    }

    public static String getRandomUserByNatUrl(String nat) {
        return url + "?nat=" + nat;
    }

    public static void createNewUser (JSONObject json) {
        try {
            JSONObject user = json.getJSONArray("results").getJSONObject(0).getJSONObject("user");
            JSONObject name = user.getJSONObject("name");
            JSONObject address = user.getJSONObject("location");
            JSONObject picture = user.getJSONObject("picture");


            Contact contact = new Contact();
            //person info
            contact.setFirstName(name.getString("first"));
            contact.setLastName(name.getString("last"));

            //contact info
            contact.setEmail(user.getString("email"));
            contact.setPhone(user.getString("phone"));
            contact.setCell(user.getString("cell"));

            //address
            contact.setAddress(address.getString("street"));
            contact.setPostalCode(address.getString("zip"));
            contact.setCity(address.getString("city"));

            contact.setImageUrl(picture.getString("medium"));


            dbh.addContact(contact);
            ContactAdapter.addContact(contact);
            if (ContactAdapter.mContactArrayList.size() == 0) {
                FragmentContactList.arrayAdapter.notifyDataSetInvalidated();
            } else {
                FragmentContactList.arrayAdapter.notifyDataSetChanged();
            }
        } catch (Exception e ) {
            System.out.println("Exception");
            System.out.print(e.getMessage());
        }
    }
}
