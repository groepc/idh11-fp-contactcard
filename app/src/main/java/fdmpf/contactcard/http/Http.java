package fdmpf.contactcard.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import fdmpf.contactcard.R;

/**
 * Created by perryfaro on 27-03-16.
 */
public class Http {


    private static Http instance = null;
    public RequestQueue requestQueue;
    private Context context = null;

    private Http(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        this.context = context;
    }

    public static synchronized Http getInstance(Context context) {

        if (null == instance) {
            instance = new Http(context);
        }

        return instance;
    }

    //this is so we don't need to pass context each time
    public static synchronized Http getInstance() {
        if (null == instance) {
            throw new IllegalStateException(Http.class.getSimpleName() + " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void getJson(String url, final JsonResponseListener<JSONObject> listener) {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        listener.getResult(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, R.string.http_no_connection, Toast.LENGTH_LONG).show();
                        //throw new RuntimeException("Connection to RandomUserAPI failed. Try again");
                    }
                });

        requestQueue.add(jsObjRequest);
    }

    public void getImage(String url, final ImageResponseListener listener) {
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        listener.getResult(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });

        requestQueue.add(request);
    }

    public interface JsonResponseListener<T> {
        void getResult(JSONObject json);
    }

    public interface ImageResponseListener<T> {
        void getResult(Bitmap bitmap);
    }

}
