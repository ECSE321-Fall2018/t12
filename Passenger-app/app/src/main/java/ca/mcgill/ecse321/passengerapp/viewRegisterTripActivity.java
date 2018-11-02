package ca.mcgill.ecse321.passengerapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ca.mcgill.ecse321.passengerapp.adapters.VehicleAdapter;
import ca.mcgill.ecse321.passengerapp.model.Trip;

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
        trip =(Trip) getIntent().getSerializableExtra("TRIP_ID");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
