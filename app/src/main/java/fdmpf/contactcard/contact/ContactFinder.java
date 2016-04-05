package fdmpf.contactcard.contact;

import org.json.JSONObject;

import fdmpf.contactcard.http.Http;

/**
 * Created by perryfaro on 02-04-16.
 */
public class ContactFinder {

    public static ContactDatabaseHelper dbh;

    public static void randomApiRequests(int findRandom) {

        for (int i = 0; i <= findRandom; i += 1) {

            Http http = Http.getInstance();
            http.getJson(RandomUser.getRandomUserByNatUrl("nl"), new Http.JsonResponseListener() {
                @Override
                public void getResult(JSONObject json) {
                    RandomUser.dbh = dbh;
                    RandomUser.createNewUser(json);
                }
            });


        }

    }
}
