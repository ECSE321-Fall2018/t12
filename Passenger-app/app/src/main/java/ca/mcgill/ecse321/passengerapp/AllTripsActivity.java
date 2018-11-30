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
import android.widget.EditText;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.passengerapp.adapters.TripAdapter;
import ca.mcgill.ecse321.passengerapp.model.Registration;
import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.TripNode;
import ca.mcgill.ecse321.passengerapp.util.HttpRequest;
import cz.msebera.android.httpclient.Header;


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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpGetAllTrips();
    }

    private void populateAllTripsView(){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        adapter = new TripAdapter(this, trips);

        allTripsView.setLayoutManager(lm);
        allTripsView.setAdapter(adapter);
        adapter.setClickListener(this);

        httpGetAllTrips();
    }

    public void searchBtnClick (View view){
        String city = searchText.getText().toString();

        if (!city.equals("")) {

            List<Trip> searchedTrips = new ArrayList<>();

            main:
            for (Trip trip : trips) {

                // Ensure the user does not already exist in this trip
                for (Registration tripRegistrations : trip.getRegistrations()) {
                    for (Registration passRegistrations : MainActivity.mainUser.getRegistrations()) {
                        if (tripRegistrations.equals(passRegistrations)) break main;
                    }
                }


                // First check the endpoint
                if (city.equalsIgnoreCase(trip.getEndpoint())) {
                    searchedTrips.add(trip);
                } else {
                    // Check Nodes
                    for (TripNode node : trip.getTripNodes()) {
                        if (city.equalsIgnoreCase(node.getName())) {
                            searchedTrips.add(trip);
                            break;
                        }
                    }
                }


            }

            trips = searchedTrips;
            adapter.notifyDataSetChanged();
        }

        // Update the trip list
        httpGetAllTrips();
    }


    private void httpGetAllTrips(){
        String url = "api/trips/";
        HttpRequest.withToken(MainActivity.token).get(url, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                System.out.println(response.toString());
                trips.clear();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Trip trip = new ObjectMapper().readValue(obj.toString(), Trip.class);
                        trips.add(trip);

                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    throw(throwable);
                } catch (Throwable throwable1) {
                    throwable1.printStackTrace();
                }
            }
        });

    }

    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, viewRegisterTripActivity.class);
        intent.putExtra("TRIP_ID", adapter.getItem(position));
        startActivity(intent);
    }


}
