package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
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
import java.util.ArrayList;
import java.util.Date;

import ca.mcgill.ecse321.driverapp.model.TripNode;
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
        String destination = DestinationInput.getText().toString();
        String location = LocationInput.getText().toString();
        String timeString = TimeInput.getText().toString();
        String dateString = TimeInput.getText().toString();

        //Somehow create this here  Vehicle vehicle = VehicleInput.

        if(destination == ""){
            errorTxt.setText("Creation Failure: Please input a destination");
        }
        if(location == ""){
            errorTxt.setText("Creation Failure: Please input a starting location");
        }
        if(timeString == ""){
            errorTxt.setText("Creation Failure: Please input a time of departure");
        }
        if(timeString == ""){
            errorTxt.setText("Creation Failure: Please input a time of departure");
        }
        if(dateString == ""){
            errorTxt.setText("Creation Failure: Please input a date of departure");
        }
        //List adapter for the following two
        Vehicle vehicle =null;
        ArrayList<TripNode> tripnodes= null;

        //call controller or method calls controller
    }

    public void createVehiclebtnClick(View view){
            createVehicle();
    }


    private void createTripNodebtn(View view){
            createTripNode();
            //update the tripnode list
    }

    public void createTrip(String destination, String location, String timeString, String dateString,Vehicle vehicle, ArrayList<TripNode> tripnodes){
            //call controller
    }
    public void createVehicle(){
        Intent vehicleIntent = new Intent(this, CreateVehicleActivity.class);
        startActivity(vehicleIntent);
    }
    public void createTripNode(){
        Intent trip_node_Intent = new Intent(this, create_trip_nodeActivity.class);
        startActivity(trip_node_Intent);
    }

}
