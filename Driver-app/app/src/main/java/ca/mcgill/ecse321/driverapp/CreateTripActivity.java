package ca.mcgill.ecse321.driverapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.Date;

import ca.mcgill.ecse321.driverapp.model.Vehicle;

public class CreateTripActivity extends AppCompatActivity {

    private EditText DestinationInput;
    private EditText LocationInput;
    private EditText DateInput;
    private EditText TimeInput;
    private RecyclerView VehicleInput;
    private RecyclerView TripnodeInput;
    private TextView errorTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DestinationInput =(EditText)findViewById(R.id.Destinationbox);
        LocationInput =(EditText)findViewById(R.id.Locationbox);
        DateInput = (EditText) findViewById(R.id.Datebox);
        TimeInput = (EditText) findViewById((R.id.Timebox));//String then cast as a date object
        VehicleInput = (RecyclerView) findViewById(R.id.Vehiclebox);
        TripnodeInput = (RecyclerView) findViewById(R.id.TripNodeBox);
    }

    public void createBtnClick(View view){

    }

    public void createVehiclebtnClick(View view){
       String destination = DestinationInput.getText().toString();
       String location = LocationInput.getText().toString();
       String timeString = TimeInput.getText().toString();
       String dateString = TimeInput.getText().toString();

      //Somehow create this here  Vehicle vehicle = VehicleInput.

        if(destination == ""){
            errorTxt.setText("Unable to login: Please input a password");
        }
    }

    private void createTripNodebtn(View view){

    }

    public void createTrip(){

    }


}
