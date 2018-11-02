package ca.mcgill.ecse321.passengerapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
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

import ca.mcgill.ecse321.passengerapp.adapters.TripAdapter;
import ca.mcgill.ecse321.passengerapp.adapters.VehicleAdapter;
import ca.mcgill.ecse321.passengerapp.model.Registration;
import ca.mcgill.ecse321.passengerapp.model.Role;
import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.User;
import ca.mcgill.ecse321.passengerapp.model.Vehicle;

public class viewDeregisterTripActivity extends AppCompatActivity {
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
    private VehicleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_deregister_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        //trip = getIntent().getStringExtra("Trip_ID");
        //trip =(Trip) getIntent().getSerializableExtra("Trip_ID");
        //example
        trip = new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, new Vehicle("model","make","color", new User()));


        Destination =(TextView) findViewById(R.id.DestinationDisplay);
        Destination.setText(trip.getEndpoint());
        Location =(TextView) findViewById(R.id.StartpointDisplay);
        Location.setText(trip.getStartpoint());
        Date =(TextView) findViewById(R.id.DateDisplay);
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        //something about a date fix the date later


       // String printedDate= formatter.format(trip.getDate());
        //Date.setText(printedDate);
        Time = (TextView) findViewById(R.id.TimeDisplay);
        Time.setText(trip.getStart_time().toString());
        Length = (TextView) findViewById(R.id.LengthDisplay);
        Length.setText(trip.getEst_Trip_time()+ " hours");
        Seats = (TextView) findViewById(R.id.SeatsDisplay);
       // Seats.setText(trip.getSeats_available());
        Vehicle = (RecyclerView) findViewById(R.id.VehicleDisplay);
       // populateVehicle();
        Tripnodes = (RecyclerView) findViewById(R.id.TripNodeDisplay);
        populateTripNode();
        Passenger = (RecyclerView) findViewById(R.id.PassengerDisplay);
        populatePassengers();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    private void editbtnClick(){
        //Intent editIntent = new Intent(this, edit_tripActivity.class);
       // startActivity(editIntent);

        // USETHIS BUTTON TO DO SOMETHING USEFUL LIKE DEREGISTERING FROM A TRIP



        Intent editIntent = new Intent(this, MyTripsActivity.class);//return to my trips activity
         startActivity(editIntent);
    }

}
