package ca.mcgill.ecse321.passengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import ca.mcgill.ecse321.passengerapp.util.HttpUtils;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

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
        else if(!userExists(un)){
            errorTxt.setText("Unable to login: Username does not exist");
        }
        else {
            try {
                login(un, pass);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
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
        else if(userExists(un)){
            errorTxt.setText("Unable to create user: Username already exists");
        }
        else {
            if(saveUser(un, pass)){
                try {
                    login(un, pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean userExists(String username){
        if(username.compareTo("admin") ==  0){
            return true;
        }
        return false;
    }

    public boolean saveUser(String username, String password){
        return true;
    }

    public boolean login(String username, String password) throws JSONException, UnsupportedEncodingException{

        /**
         * Example request:
         * {
         * 	    "name": "Alex",
         *     "username": "Bshizzl",
         *     "password": "123123"
         * }
         */

        RequestParams params = new RequestParams();
        params.add("name", "test");
        System.out.println("HERHEHRHEHRE ----------" + params.toString());

        JSONObject jsonParams = new JSONObject();
        jsonParams.put("name", "test");
        jsonParams.put("username", username);
        jsonParams.put("password", password);

        System.out.println(jsonParams.toString());

        StringEntity entity = new StringEntity(jsonParams.toString());
        //entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));


        HttpUtils.post(this.getBaseContext(), getString(R.string.signup_url), entity, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                errorTxt.setText(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("ERROR STATUS: " + statusCode);
                if (errorResponse != null)
                {
                    errorTxt.setText(errorResponse.toString());
                }
            }

        });


        if(username.compareTo("admin") == 0 && password.compareTo("password") == 0){
            //Changes view to main view
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);

            //Prevents user from pressing back to return to sign in page
            finish();
            return true;
        }
        return false;
    }
}

