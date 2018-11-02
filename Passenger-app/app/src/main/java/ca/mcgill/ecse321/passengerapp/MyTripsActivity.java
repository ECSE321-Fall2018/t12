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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.mcgill.ecse321.passengerapp.adapters.TripAdapter;
import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.User;


public class MyTripsActivity extends AppCompatActivity implements TripAdapter.ItemClickListener {
    private RecyclerView myTripsView;
    private TripAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setTitle("My Trips"); // i have no idea why this is may have to just delete the toolbar

        myTripsView = (RecyclerView) findViewById(R.id.myTripsView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        populateMyTripsView();
    }


    private void populateMyTripsView(){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        adapter = new TripAdapter(this, htmlGetMyTrips(0));

        myTripsView.setLayoutManager(lm);
        myTripsView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    private List<Trip> htmlGetMyTrips(long id){
        List<Trip> trips = new ArrayList<Trip>();



        return trips;
    }

    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, viewDeregisterTripActivity.class);
        intent.putExtra("TRIP_ID", adapter.getItem(position));
        startActivity(intent);
    }
}
