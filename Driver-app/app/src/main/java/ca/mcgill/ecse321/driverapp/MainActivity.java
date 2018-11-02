package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ca.mcgill.ecse321.driverapp.model.User;

public class  MainActivity extends AppCompatActivity {

    public static String token;
    public static User mainUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewMyTripsBtnClick(View view){
        Intent mainIntent = new Intent(this, MyTripsActivity.class);
        startActivity(mainIntent);
    }

    public void viewMyVehiclesBtnClick(View view){
        Intent mainIntent = new Intent(this, MyVehiclesActivity.class);
        startActivity(mainIntent);
    }

}
