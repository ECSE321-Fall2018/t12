package ca.mcgill.ecse321.passengerapp.util;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;

import com.loopj.android.http.*;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import ca.mcgill.ecse321.passengerapp.R;
import cz.msebera.android.httpclient.entity.StringEntity;

public class HttpUtils {

    public static final String DEFAULT_BASE_URL = "https://webservice-backend-12.herokuapp.com/";

    private static String baseUrl;
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static AsyncHttpClient noauthClient = new AsyncHttpClient();

    private static final String client_name = "12Client1";
    private static final String client_secret = "12SuperSecret";


    static {
        baseUrl = DEFAULT_BASE_URL;
        client.setBasicAuth(client_name, client_secret);
    }

    public static String getAbsoluteUrl(String relativeUrl) {
        return getBaseUrl() + relativeUrl;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(Context context, String relativeUrl, JSONObject jsonData, AsyncHttpResponseHandler responseHandler) throws UnsupportedEncodingException {
        postByUrl(context, true, getAbsoluteUrl(relativeUrl), jsonData, responseHandler);
    }

    public static void post(Context context, String relativeUrl, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        postByUrl(context, true, getAbsoluteUrl(relativeUrl), params, responseHandler);
    }

    public static void postWithoutAuth(Context context, String relativeUrl, JSONObject jsonData, AsyncHttpResponseHandler responseHandler) throws UnsupportedEncodingException {
        postByUrl(context, false, getAbsoluteUrl(relativeUrl), jsonData, responseHandler);
    }



    private static void postByUrl(Context context, boolean auth, String url, JSONObject jsonData, AsyncHttpResponseHandler responseHandler) throws UnsupportedEncodingException {

        AsyncHttpClient c = (auth) ? client : noauthClient;

        // convert json data into a StringEntity
        StringEntity entity = new StringEntity(jsonData.toString());

        c.addHeader("Content-Type", "application/json");

        // Preform a POST request
        c.post(context, url, entity, "application/json", responseHandler);
        c.removeAllHeaders();
    }

    private static void postByUrl(Context context, boolean auth, String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        AsyncHttpClient c = (auth) ? client : noauthClient;

        c.addHeader("Content-Type", "application/x-www-form-urlencoded");

        c.post(url, params, responseHandler);
        c.removeAllHeaders();
    }

    public static boolean isNetworkAvailable(Activity activity) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();


        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }


}