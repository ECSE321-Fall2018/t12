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

    public static boolean isNetworkAvailable(Activity activity) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();


        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}