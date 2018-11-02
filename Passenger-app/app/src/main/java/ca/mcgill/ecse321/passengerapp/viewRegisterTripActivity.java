package ca.mcgill.ecse321.passengerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import ca.mcgill.ecse321.passengerapp.adapters.VehicleAdapter;
import ca.mcgill.ecse321.passengerapp.model.Registration;
import ca.mcgill.ecse321.passengerapp.model.Role;
import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.User;
import ca.mcgill.ecse321.passengerapp.model.Vehicle;

public class viewRegisterTripActivity extends AppCompatActivity {

    private Trip trip;
    private TextView errorTxt;
    private TextView Destination;
    private TextView Location;
    private TextView Date;
    private TextView Time;
    private TextView ArrivalTime;
    private TextView Length;
    private TextView Seats;
    private RecyclerView Vehicle;
    private RecyclerView Tripnodes;
    private RecyclerView Passenger;
    private VehicleAdapter adapter;
    private TextView DriverText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_register_trip);
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
        trip = (Trip) getIntent().getSerializableExtra("TRIP_ID");

        DriverText = (TextView) findViewById(R.id.textViewDriverInfo);
        Iterable<Registration> registrations = trip.getRegistrations();
        User driver = new User();
        for (Registration r : registrations) {
            if (r.getRole() == Role.DRIVER) {
                driver = r.getUser();

            }

            ArrivalTime = (TextView) findViewById(R.id.ArrivalDisplay);
            ArrivalTime.setText(trip.getEnd_time() + "");

            Destination = (TextView) findViewById(R.id.DestinationDisplay);
            Destination.setText(trip.getEndpoint());
            Location = (TextView) findViewById(R.id.StartpointDisplay);
            Location.setText(trip.getStartpoint());
            Date = (TextView) findViewById(R.id.DateDisplay);
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");


        }

        ArrivalTime = (TextView) findViewById(R.id.ArrivalDisplay);
        ArrivalTime.setText(trip.getEnd_time() + "");

        Destination = (TextView) findViewById(R.id.DestinationDisplay);
        Destination.setText(trip.getEndpoint());
        Location = (TextView) findViewById(R.id.StartpointDisplay);
        Location.setText(trip.getStartpoint());
        Date = (TextView) findViewById(R.id.DateDisplay);
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");

        Time = (TextView) findViewById(R.id.TimeDisplay);
        Time.setText(trip.getStart_time().toString());
        Length = (TextView) findViewById(R.id.LengthDisplay);
        Length.setText(trip.getEst_Trip_time() + " hours");
        Seats = (TextView) findViewById(R.id.SeatsDisplay);
        Seats.setText(trip.getSeats_available() + "");
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
        ArrayList<ca.mcgill.ecse321.passengerapp.model.Vehicle> temp= null;
        temp.add(trip.getVehicle());
        adapter = new VehicleAdapter(this, temp);
        Vehicle.setLayoutManager(lm);
        Vehicle.setAdapter(adapter);
    }
    private void populateTripNode(){

    }


    public void RegisterBtnClick(View view){
        //here i must call the backend to deregister this user from this trip

        long tripId = trip.getId();

        String url = "api/users/" + MainActivity.mainUser.getId() + "/trips/" + tripId + "/registrations/";

        Intent editIntent = new Intent(this, MyTripsActivity.class);//return to my trips activity
        startActivity(editIntent);

    }


}
