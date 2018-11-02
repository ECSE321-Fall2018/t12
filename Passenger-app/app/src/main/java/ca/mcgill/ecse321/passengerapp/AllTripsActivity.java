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

public class AllTripsActivity extends AppCompatActivity  implements TripAdapter.ItemClickListener {
    private RecyclerView allTripsView;
    private TripAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        allTripsView = (RecyclerView) findViewById(R.id.allTripsView);

        populateAllTripsView();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void populateAllTripsView(){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        adapter = new TripAdapter(this, htmlGetAllTrips(0));

        allTripsView.setLayoutManager(lm);
        allTripsView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    private List<Trip> htmlGetAllTrips(long id){
        List<Trip> trips = new ArrayList<Trip>();

        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Toronto", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));
        trips.add(new Trip("Montreal", "Quebec", 200, true, new Time(1000000), new Time(1000000000), 200, 3, new Date(1000000), false, null));


        return trips;
    }

    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, viewRegisterTripActivity.class);
        intent.putExtra("TRIP_ID", adapter.getItem(position));
        startActivity(intent);
    }

}
