package fdmpf.contactcard.contact;

import org.json.JSONObject;

import fdmpf.contactcard.randomuser.RandomUser;

/**
 * Created by perryfaro on 02-04-16.
 */
public class ContactFinder {

    public static ContactDatabaseHelper dbh;

    public static void randomApiRequests (int findRandom) {

        for (int i = 0; i <= findRandom; i += 1) {
            try {
                JSONObject reponseObject = RandomUser.randomUser();
                System.out.println("We hebben een user opgehaald");
                System.out.print(reponseObject);
                JSONObject user = reponseObject.getJSONArray("results").getJSONObject(0).getJSONObject("user");
                JSONObject name = user.getJSONObject("name");
                JSONObject address = user.getJSONObject("location");
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

                dbh.addContact(contact);
            } catch (Exception e) {
                System.out.println("Exception");
            System.out.print(e.getMessage());
            }
        }

    }
}
