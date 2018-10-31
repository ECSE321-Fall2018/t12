package ca.mcgill.ecse321.passengerapp.util;

import android.app.Activity;
import android.content.Context;

import com.loopj.android.http.*;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class HttpUtils {

    public static final String DEFAULT_BASE_URL = "https://webservice-backend-12.herokuapp.com/";

    private static String baseUrl;
    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        baseUrl = DEFAULT_BASE_URL;
        client.addHeader("Content-Type", "application/json");
    }

    private static String getAbsoluteUrl(String relativeUrl) {
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
        postByUrl(context, getAbsoluteUrl(relativeUrl), jsonData, responseHandler);
    }

    public static void postByUrl(Context context, String url, JSONObject jsonData, AsyncHttpResponseHandler responseHandler) throws UnsupportedEncodingException {

        // convert json data into a StringEntity
        StringEntity entity = new StringEntity(jsonData.toString());

        // Preform a POST request
        client.post(context, url, entity, "application/json", responseHandler);
    }




}