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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ca.mcgill.ecse321.driverapp.adapters.TripAdapter;
import ca.mcgill.ecse321.driverapp.adapters.VehicleAdapter;
import ca.mcgill.ecse321.driverapp.model.Trip;
import ca.mcgill.ecse321.driverapp.model.User;
import ca.mcgill.ecse321.driverapp.model.Vehicle;
import ca.mcgill.ecse321.driverapp.util.HttpRequest;
import cz.msebera.android.httpclient.Header;

public class MyVehiclesActivity extends AppCompatActivity implements VehicleAdapter.ItemClickListener {
    private RecyclerView myVehiclesView;

    private VehicleAdapter adapter;

    private List<Vehicle> vehicles = new ArrayList<>();

    private Timer autoUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myVehiclesView = (RecyclerView) findViewById(R.id.MyVehiclesView);

        populateTrips();
    }

    private void populateTrips(){
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        adapter = new VehicleAdapter(this, vehicles);

        myVehiclesView.setLayoutManager(lm);
        myVehiclesView.setAdapter(adapter);
        adapter.setClickListener(this);

        htmlGetVehicles();

    }

    private void htmlGetVehicles(){
        //List<Vehicle> vehicles = new ArrayList<Vehicle>();

        long userId = MainActivity.mainUser.getId();

        String url = getString(R.string.users_api) + "/" + userId + "/vehicles/";
        HttpRequest.withToken(MainActivity.token).get(url, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Gson gson = new GsonBuilder().create();

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            Vehicle vehicle = (Vehicle) gson.fromJson(obj.toString(), Vehicle.class);
                            vehicles.add(vehicle);

                        } catch (JSONException e) {
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

    @Override
    public void onResume() {
        super.onResume();
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        vehicles.clear();
                        htmlGetVehicles();
                    }
                });
            }
        }, 0, 5000); // updates each 5 secs
    }

    @Override
    public void onPause() {
        autoUpdate.cancel();
        super.onPause();
    }

    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, editDeleteVehicleActivity.class);
        intent.putExtra("VEHICLE_ID", adapter.getItem(position));
        startActivity(intent);
    }
}
