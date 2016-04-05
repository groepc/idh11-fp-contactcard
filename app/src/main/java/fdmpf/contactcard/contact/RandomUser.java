package fdmpf.contactcard.contact;

/**
 * Created by perryfaro on 05-04-16.
 */
public class RandomUser {

    static String url = "https://randomuser.me/api/";


    public static String randomUserUrl() {

        return url;
    }

    public static String getRandomUserByNatUrl(String nat) {
        return url + "?nat=" + nat;
    }
}
