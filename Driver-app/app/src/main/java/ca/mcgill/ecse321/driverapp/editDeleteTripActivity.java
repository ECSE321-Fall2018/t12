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

import java.text.Format;
import java.text.SimpleDateFormat;

import ca.mcgill.ecse321.driverapp.model.Trip;

public class editDeleteTripActivity extends AppCompatActivity {

    //This activity views a trip before editing/ deletion
    private Trip trip;
    private TextView errorTxt;
    private TextView Destination;
    private TextView Location;
    private TextView Date;
    private TextView Time;
    private TextView Length;
    private TextView Seats;
    private RecyclerView Vehicle;
    private RecyclerView Tripnodes;
    private RecyclerView Passenger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        trip =(Trip) getIntent().getSerializableExtra("Trip_ID");
        Destination =(TextView) findViewById(R.id.DestinationDisplay);
        Destination.setText(trip.getEndpoint());
        Location =(TextView) findViewById(R.id.StartpointDisplay);
        Location.setText(trip.getStartpoint());
        Date =(TextView) findViewById(R.id.DateDisplay);
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String printedDate= formatter.format(trip.getDate());
        Date.setText(printedDate);
        Time = (TextView) findViewById(R.id.TimeDisplay);
        Time.setText(trip.getStart_time().toString());
        Length = (TextView) findViewById(R.id.LengthDisplay);
        Length.setText(trip.getEst_Trip_time()+ " hours");
        Seats = (TextView) findViewById(R.id.SeatsDisplay);
        Seats.setText(trip.getSeats_available());

    }

    private void populatePassengers(){

    }
    private void populateVehicle(){

    }
    private void populateTripNode(){

    }
    private void editbtnClick(){
        Intent editIntent = new Intent(this, edit_tripActivity.class);
        startActivity(editIntent);
    }
    private void deletebtnClick(){
       //Delete Trip, notify all registered the trip is cancelled

    }

}
