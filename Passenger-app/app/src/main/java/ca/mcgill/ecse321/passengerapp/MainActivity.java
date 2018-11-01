package ca.mcgill.ecse321.passengerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ca.mcgill.ecse321.passengerapp.model.Vehicle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dont know if this part of the code is necesary
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
    }


    public void myTripBtnClick(View view){
        //Changes view to main view
        Intent myTripsIntent = new Intent(this, MyTripsActivity.class);
        startActivity(myTripsIntent);

    }


    public void allTripsBtnClick(View view){
        //Changes view to main view
        Intent myTripsIntent = new Intent(this, AllTripsActivity.class);
        startActivity(myTripsIntent);

    }


}
