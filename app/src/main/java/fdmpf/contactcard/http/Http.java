package fdmpf.contactcard.http;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by perryfaro on 27-03-16.
 */
public class Http {


    public static JSONObject getJson(String urlString, String action) {

        // HttpClient client = new DefaultHttpClient();
        InputStream inputStream = null;
        int responsCode = -1;
        JSONObject response = null;
        try {
            System.out.println("Get 1");
            URL url = new URL(urlString + action);
            URLConnection urlConnection = url.openConnection();

            if (!(urlConnection instanceof HttpURLConnection)) {
                // Url
                System.out.println("Get 2");
                //return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            responsCode = httpConnection.getResponseCode();

            if (responsCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = stringToJson(getStringFromInputStream(inputStream));
            }
        } catch (Exception e) {
            System.out.println("Exception1");
            Log.e("TAG", e.getLocalizedMessage());
            //return null;
        }

        return response;

    }

    private static JSONObject stringToJson (String string) {

        JSONObject returnValue = null;

        try {
            returnValue = new JSONObject(string);
        } catch (Exception e) {

        }
        return returnValue;
    }


    //
    // convert InputStream to String
    //
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

}
