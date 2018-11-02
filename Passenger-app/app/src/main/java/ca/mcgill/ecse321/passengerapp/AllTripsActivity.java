package ca.mcgill.ecse321.passengerapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ca.mcgill.ecse321.passengerapp.adapters.TripAdapter;
import ca.mcgill.ecse321.passengerapp.model.Position;
import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.TripNode;


public class AllTripsActivity extends AppCompatActivity  implements TripAdapter.ItemClickListener {
    private RecyclerView allTripsView;
    private TripAdapter adapter;
    private EditText searchText;

    private List<Trip> trips = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        allTripsView = (RecyclerView) findViewById(R.id.allTripsView);

        populateAllTripsView();
        searchText = (EditText) findViewById(R.id.searchText);


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

        httpUpdateTrips();
        adapter = new TripAdapter(this, trips);

        allTripsView.setLayoutManager(lm);
        allTripsView.setAdapter(adapter);
        adapter.setClickListener(this);
    }


    private void populateAllTripsViewSearch(String citySearched){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        httpUpdateTrips();
        adapter = new TripAdapter(this, searchTrips(citySearched));

        allTripsView.setLayoutManager(lm);
        allTripsView.setAdapter(adapter);
        adapter.setClickListener(this);

        adapter.notifyDataSetChanged();
    }

    private List<Trip> searchTrips(String citySearched) {
        List<Trip> searchedTrips = new ArrayList<Trip>();

        //whatever it is we need to do to get all of the trips and call it all trips
        for (Trip trip : trips){
            if (tripStopsAt(trip, citySearched)){
                searchedTrips.add(trip);
            }

        }

        //keep trips which have any non starting trip node with a name which matches citySearched
        return searchedTrips;
        //return the resulting list
    }

    private Boolean tripStopsAt(Trip trip,String city){
        // we need to fix this later to use trip nodes when the model is updated
        // in the mean time we will use end points

        return city.equalsIgnoreCase(trip.getEndpoint());

        /*
        Set<TripNode> nodes= trip.getTripNodes();
        for (TripNode node : nodes){
            if (node.
        }
        */




    }

    public void searchBtnClick (View view){
        String city = searchText.getText().toString();
        populateAllTripsViewSearch(city);
    }


    private void httpUpdateTrips(){
        trips = new ArrayList<Trip>();

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
    }

    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, viewRegisterTripActivity.class);
        intent.putExtra("TRIP_ID", adapter.getItem(position));
        startActivity(intent);
    }


}
