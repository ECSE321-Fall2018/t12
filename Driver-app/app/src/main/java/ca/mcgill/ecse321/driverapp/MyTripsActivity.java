package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.mcgill.ecse321.driverapp.adapters.TripAdapter;
import ca.mcgill.ecse321.driverapp.model.Trip;
import ca.mcgill.ecse321.driverapp.model.User;
import ca.mcgill.ecse321.driverapp.util.HttpRequest;
import cz.msebera.android.httpclient.Header;

public class MyTripsActivity extends AppCompatActivity  implements TripAdapter.ItemClickListener{
    private RecyclerView myTripsView;

    private TripAdapter adapter;

    private List<Trip> trips;

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
        httpGetTrips();

        adapter = new TripAdapter(this, trips);
        myTripsView.setLayoutManager(lm);
        myTripsView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    private void httpGetTrips(){
        String url = "api/users/" + MainActivity.mainUser.getId() + "/trips/";

        HttpRequest.withToken(MainActivity.token).get(url, new RequestParams(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new GsonBuilder().create();

                trips = (List<Trip>) gson.fromJson(response.toString(), User.class);
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

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, editDeleteTripActivity.class);
        intent.putExtra("TRIP_ID", adapter.getItem(position));
        startActivity(intent);
    }
}


