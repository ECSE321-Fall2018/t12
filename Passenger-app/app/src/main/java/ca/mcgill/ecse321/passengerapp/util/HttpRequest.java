package ca.mcgill.ecse321.passengerapp.util;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class HttpRequest {

    /**
     * Base URL
     */
    public static final String DEFAULT_BASE_URL = "https://webservice-backend-12.herokuapp.com/";

    private AsyncHttpClient client;

    /**
     * Public constructor
     * NOTE: Use static factory methods instead (for ease of use)
     */
    public HttpRequest(AsyncHttpClient client) {
        this(client, "application/json");
    }

    public HttpRequest(AsyncHttpClient client, String contentType) {
        this.client = client;
        this.client.addHeader("Content-Type", contentType);
    }

    public static HttpRequest withToken(String token) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Bearer " + token);

        return new HttpRequest(client);
    }

    public static HttpRequest withBasicAuth(String secret, String password, String contentType) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth(secret, password);

        return new HttpRequest(client, contentType);
    }

    public static HttpRequest withNoAuth() {
        return new HttpRequest(new AsyncHttpClient());
    }


    public void post(Context context, String url, JSONObject jsonData, AsyncHttpResponseHandler responseHandler) throws UnsupportedEncodingException {

        // convert json data into a StringEntity
        StringEntity entity = new StringEntity(jsonData.toString());

        // Preform a POST request
        client.post(context, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        client.post(getAbsoluteUrl(url), params, responseHandler);
    }


    public String getAbsoluteUrl(String relativeUrl) {
        return DEFAULT_BASE_URL + relativeUrl;
    }


}
