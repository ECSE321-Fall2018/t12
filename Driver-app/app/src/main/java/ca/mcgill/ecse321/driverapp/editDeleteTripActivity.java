package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import ca.mcgill.ecse321.driverapp.adapters.TripAdapter;
import ca.mcgill.ecse321.driverapp.adapters.VehicleAdapter;
import ca.mcgill.ecse321.driverapp.model.Registration;
import ca.mcgill.ecse321.driverapp.model.Role;
import ca.mcgill.ecse321.driverapp.model.Trip;
import ca.mcgill.ecse321.driverapp.model.User;
import ca.mcgill.ecse321.driverapp.model.Vehicle;

public class editDeleteTripActivity extends AppCompatActivity {

    //This activity views a trip before editing/ deletion
    private Trip trip;
    private TextView errorTxt;
    private TextView DestinationTxt;
    private TextView LocationTxt;
    private TextView DateTxt;
    private TextView TimeTxt;
    private TextView LengthTxt;
    private TextView seatsTxt;
    private TextView arrivalTxt;
    private RecyclerView Vehicle;
    private RecyclerView TripnodesView;
    private RecyclerView PassengerView;
    private VehicleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        trip =(Trip) getIntent().getSerializableExtra("TRIP_ID");
        DestinationTxt =(TextView) findViewById(R.id.DestinationDisplay);
        DestinationTxt.setText(trip.getEndpoint());

        LocationTxt =(TextView) findViewById(R.id.StartpointDisplay);
        LocationTxt.setText(trip.getStartpoint());

        DateTxt =(TextView) findViewById(R.id.DateDisplay);
        DateTxt.setText(trip.getDate());

        TimeTxt = (TextView) findViewById(R.id.TimeDisplay);
        TimeTxt.setText(trip.getStart_time());

        LengthTxt = (TextView) findViewById(R.id.LengthDisplay);
        LengthTxt.setText(trip.getEst_Trip_time()+ " hours");

        seatsTxt = (TextView) findViewById(R.id.seatsDisplay);
        seatsTxt.setText(trip.getSeats_available() + "");

        arrivalTxt = (TextView) findViewById(R.id.ArrivalDisplay);
        arrivalTxt.setText(trip.getEnd_time() + "");

        Vehicle = (RecyclerView) findViewById(R.id.VehicleDisplay);
        //populateVehicle();

        TripnodesView = (RecyclerView) findViewById(R.id.TripNodeDisplay);
        //populateTripNode();

        PassengerView = (RecyclerView) findViewById(R.id.PassengerDisplay);
        //populatePassengers();
    }

    private void populatePassengers(){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
    }
    private void populateVehicle(){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        ArrayList<Vehicle> temp= null;
        temp.add(trip.getVehicle());
        adapter = new VehicleAdapter(this, temp);
        Vehicle.setLayoutManager(lm);
        Vehicle.setAdapter(adapter);
    }
    private void populateTripNode(){

    }

    public void editbtnClick(View view){
        Intent editIntent = new Intent(this, edit_tripActivity.class);
        startActivity(editIntent);
    }
    public void deletebtnClick(View view){
       //Delete Trip, notify all registered the trip is cancelled

    }

}
