package ca.mcgill.ecse321.passengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

import ca.mcgill.ecse321.passengerapp.util.HttpRequest;
import ca.mcgill.ecse321.passengerapp.util.HttpUtils;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private TextView errorTxt;
    private EditText usernameTbx;
    private EditText passwordTbx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        errorTxt = (TextView)findViewById(R.id.errorTxt);
        usernameTbx = (EditText) findViewById(R.id.usernameTbx);
        passwordTbx = (EditText) findViewById(R.id.passwordTbx);
    }

    //Make sure input is ok before trying to login and try to login
    public void loginBtnClick(View view){
        String pass = passwordTbx.getText().toString();
        String un = usernameTbx.getText().toString();

        if(pass == ""){
            errorTxt.setText("Unable to login: Please input a password");
        }
        else if (un == ""){
            errorTxt.setText("Unable to login: Please input a username");
        }
        else {
            login(un, pass);
        }
    }



    //Make sure the user does not exist and try to log in
    public void signUpBtnClick(View view){
        String pass = passwordTbx.getText().toString();
        String un = usernameTbx.getText().toString();

        if(pass == ""){
            errorTxt.setText("Please input a password");
        }
        else if (un == ""){
            errorTxt.setText("Please input a username");
        }
        else {
            saveUser(un, pass);
        }
    }

    public boolean userExists(String username){

        return true;
    }

    public void saveUser(String username, String password){

        try {
            signupUser(username, password);
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.error_exception_thrown) + e.getMessage(), Snackbar.LENGTH_LONG).show();
        }

    }

    public void signupUser(String username, String password) throws JSONException, UnsupportedEncodingException {
        // Check if the network is available
        if (!HttpUtils.isNetworkAvailable(this)) {
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.error_no_internet), Snackbar.LENGTH_LONG).show();
            return;
        }

        /**
         * Example request:
         * {
         * 	   "name": "Alex",
         *     "username": "Bshizzl",
         *     "password": "123123"
         * }
         */

        JSONObject jsonParams = new JSONObject();
        jsonParams.put("name", "test");
        jsonParams.put("username", username);
        jsonParams.put("password", password);

        System.out.println(jsonParams.toString());

        final String u = username, p = password;

        HttpRequest.withNoAuth().post(this, getString(R.string.signup_url), jsonParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                errorTxt.setText(response.toString());
                login(u, p);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("ERROR STATUS: " + statusCode);
                if (errorResponse != null) {
                    errorTxt.setText(errorResponse.toString());
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Service is down!", Snackbar.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                errorTxt.setText(responseString);
            }
        });

    }

    public void login(String username, String password) {
        // Check if the network is available
        if (!HttpUtils.isNetworkAvailable(this)) {
            Snackbar.make(findViewById(android.R.id.content), getString(R.string.error_no_internet), Snackbar.LENGTH_LONG).show();
            return;
        }

        RequestParams params = new RequestParams();
        params.add("username", username);
        params.add("password", password);
        params.add("grant_type", getString(R.string.oauth_grantype));

        HttpRequest.withBasicAuth(getString(R.string.client_name), getString(R.string.client_secret), getString(R.string.content_type_xform))
                .post(getString(R.string.get_access_token_url), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                errorTxt.setText(response.toString());

                //Changes view to main view
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainIntent);

                //Prevents user from pressing back to return to sign in page
                finish();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {

                    errorTxt.setText(errorResponse.toString());
                    Snackbar.make(findViewById(android.R.id.content), LoginActivity.this.getString(R.string.error_invalid_credentials), Snackbar.LENGTH_LONG).show();

                } else {
                    Snackbar.make(findViewById(android.R.id.content), LoginActivity.this.getString(R.string.error_service_down), Snackbar.LENGTH_LONG).show();
                }
            }

        });
    }

}

