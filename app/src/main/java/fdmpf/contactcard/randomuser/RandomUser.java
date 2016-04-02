package fdmpf.contactcard.randomuser;

import org.json.JSONObject;

import fdmpf.contactcard.http.Http;

/**
 * Created by perryfaro on 27-03-16.
 */
public class RandomUser {

    static String url = "https://randomuser.me/api/";


    public static JSONObject randomUser() {

        return Http.getJson(url, "");
    }

    public static JSONObject getRandomUserByNat(String nat) {
        return Http.getJson(url, "?nat=" + nat);
    }

}
