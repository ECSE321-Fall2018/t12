package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse321.driverapp.adapters.TripAdapter;
import ca.mcgill.ecse321.driverapp.model.Trip;
import ca.mcgill.ecse321.driverapp.util.HttpRequest;

public class MyTripsActivity extends AppCompatActivity  implements TripAdapter.ItemClickListener{
    private RecyclerView myTripsView;

    private TripAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myTripsView = (RecyclerView) findViewById(R.id.MyTripsView);

        populateTrips();
    }

    private void populateTrips(){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        adapter = new TripAdapter(this, htmlGetTrips());

        myTripsView.setLayoutManager(lm);
        myTripsView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    private List<Trip> htmlGetTrips(){
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

        //HttpRequest.withToken(MainActivity.token).

        return trips;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, editDeleteTripActivity.class);
        intent.putExtra("TRIP_ID", adapter.getItem(position));
        startActivity(intent);
    }
}


