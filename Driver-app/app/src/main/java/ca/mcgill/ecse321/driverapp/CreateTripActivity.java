package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.mcgill.ecse321.driverapp.adapters.VehicleSpinAdapter;
import ca.mcgill.ecse321.driverapp.model.TripNode;
import ca.mcgill.ecse321.driverapp.model.Vehicle;
import ca.mcgill.ecse321.driverapp.util.HttpRequest;
import cz.msebera.android.httpclient.Header;

public class CreateTripActivity extends AppCompatActivity {

    private EditText DestinationInput;
    private EditText LocationInput;
    private EditText DateInput;
    private EditText TimeInput;
    private RecyclerView VehicleInput;
    private RecyclerView TripnodeInput;
    private TextView errorTxt;
    private Spinner vehicleSpinner;

    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayAdapter<Vehicle> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DestinationInput =(EditText)findViewById(R.id.Destinationbox);
        LocationInput =(EditText)findViewById(R.id.Locationbox);
        DateInput = (EditText) findViewById(R.id.Datebox);
        TimeInput = (EditText) findViewById((R.id.Timebox));//String then cast as a date object
        TripnodeInput = (RecyclerView) findViewById(R.id.TripNodeBox);
        vehicleSpinner = (Spinner) findViewById(R.id.vehicleSpinner);


        vehicleSpinner.setAdapter(spinnerAdapter);

        htmlGetVehicles();


    }

    public void createBtnClick(View view){
        String destination = DestinationInput.getText().toString();
        String location = LocationInput.getText().toString();
        String timeString = TimeInput.getText().toString();
        String dateString = TimeInput.getText().toString();

        //Somehow create this here  Vehicle vehicle = VehicleInput.

        if(destination == ""){
            errorTxt.setText("Creation Failure: Please input a destination");
        }
        if(location == ""){
            errorTxt.setText("Creation Failure: Please input a starting location");
        }
        if(timeString == ""){
            errorTxt.setText("Creation Failure: Please input a time of departure");
        }
        if(timeString == ""){
            errorTxt.setText("Creation Failure: Please input a time of departure");
        }
        if(dateString == ""){
            errorTxt.setText("Creation Failure: Please input a date of departure");
        }
        //List adapter for the following two
        Vehicle vehicle =null;
        ArrayList<TripNode> tripnodes= null;


        RequestParams params = new RequestParams();
        params.add("startpoint", location);
        params.add("endpoint", destination);
        params.add("distance", null);
        params.add("active", "true");
        params.add("start_time", timeString);
        params.add("end_time", null);
        params.add("est_Trip_Time", null);
        params.add("seats_available", null);
        params.add("date", dateString);
        params.add("compleated", null);
        params.add("cost_per_customer", null);

        String url = "/users/"+ MainActivity.mainUser.getId() + "/vehicles/" + "/trips";

        HttpRequest.withToken(MainActivity.token).post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                onBackPressed();
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {

                    errorTxt.setText(errorResponse.toString());
                    Snackbar.make(findViewById(android.R.id.content), CreateTripActivity.this.getString(R.string.error_invalid_credentials), Snackbar.LENGTH_LONG).show();

                } else {
                    Snackbar.make(findViewById(android.R.id.content), CreateTripActivity.this.getString(R.string.error_service_down), Snackbar.LENGTH_LONG).show();
                }
            }
        });

        //call controller or method calls controller
    }

    public void createVehiclebtnClick(View view){
            createVehicle();
    }


    private void createTripNodebtn(View view){
            createTripNode();
            //update the tripnode list
    }

    public void createTrip(String destination, String location, String timeString, String dateString,Vehicle vehicle, ArrayList<TripNode> tripnodes){
            //call controller
    }
    public void createVehicle(){
        Intent vehicleIntent = new Intent(this, CreateVehicleActivity.class);
        startActivity(vehicleIntent);
    }
    public void createTripNode(){
        Intent trip_node_Intent = new Intent(this, create_trip_nodeActivity.class);
        startActivity(trip_node_Intent);
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
                spinnerAdapter.notifyDataSetChanged();

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

}
