package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {
    private TextView errorTxt;
    private EditText usernameTbx;
    private EditText passwordTbx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
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
        else if(userExists(un)){
            errorTxt.setText("Unable to create user: Username already exists");
        }
        else {
            if(saveUser(un, pass)){
                login(un, pass);
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

    public boolean login(String username, String password){
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
