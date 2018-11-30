package ca.mcgill.ecse321.passengerapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.passengerapp.adapters.TripAdapter;
import ca.mcgill.ecse321.passengerapp.adapters.VehicleAdapter;
import ca.mcgill.ecse321.passengerapp.model.Registration;
import ca.mcgill.ecse321.passengerapp.model.Role;
import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.User;
import ca.mcgill.ecse321.passengerapp.model.Vehicle;
import ca.mcgill.ecse321.passengerapp.util.HttpRequest;
import cz.msebera.android.httpclient.Header;

public class viewDeregisterTripActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_view_deregister_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        trip =(Trip) getIntent().getSerializableExtra("TRIP_ID");

        DriverText = (TextView) findViewById(R.id.textViewDriverInfo);
        Iterable<Registration> registrations = trip.getRegistrations();
        User driver= new User();
        for (Registration r: registrations){
            if (r.getRole()==Role.DRIVER){
                driver= r.getUser();

            }
        }
        //un comment this !!!!!!!! when we are using acutal code
        //DriverText.setText(driver.getName());

        ArrivalTime= (TextView) findViewById(R.id.ArrivalDisplay);
        ArrivalTime.setText( trip.getEnd_time()+"");

        Destination =(TextView) findViewById(R.id.DestinationDisplay);
        Destination.setText(trip.getEndpoint());
        Location =(TextView) findViewById(R.id.StartpointDisplay);
        Location.setText(trip.getStartpoint());
        Date =(TextView) findViewById(R.id.DateDisplay);
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        //something about a date fix the date later

        Date.setText(trip.getDate());
        Time = (TextView) findViewById(R.id.TimeDisplay);
        Time.setText(trip.getStart_time().toString());
        Length = (TextView) findViewById(R.id.LengthDisplay);
        Length.setText(trip.getEst_Trip_time()+ " hours");
        Seats = (TextView) findViewById(R.id.SeatsDisplay);
        Seats.setText(trip.getSeats_available()+"");
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
        ArrayList<Vehicle> temp= null;
        temp.add(trip.getVehicle());
        adapter = new VehicleAdapter(this, temp);
        Vehicle.setLayoutManager(lm);
        Vehicle.setAdapter(adapter);
    }
    private void populateTripNode(){

    }

    public void deRegisterBtnClick(View view){
        //here i must call the backend to deregister this user from this trip
        String userUrl = "api/users/name/" + MainActivity.mainUser.getUsername();

        for (Registration reg : MainActivity.mainUser.getRegistrations()) {
            Trip t = reg.getTrip();
            //if (t.equals())
        }


        HttpRequest.withToken(MainActivity.token).get(userUrl, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                Gson gson = new GsonBuilder().create();
                User user = (User) gson.fromJson(response.toString(), User.class);

                MainActivity.mainUser = user;

                String tripsUrl = "api/users/" + MainActivity.mainUser.getId() + "/trips/";

                HttpRequest.withToken(MainActivity.token).get(tripsUrl, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        Gson gson = new GsonBuilder().create();
                        Set<Registration> userRegs = MainActivity.mainUser.getRegistrations();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Trip trip = (Trip) gson.fromJson(obj.toString(), Trip.class);

                                Set<Registration> tripRegs = trip.getRegistrations();


                                for (Iterator<Registration> tripIt = tripRegs.iterator(); tripIt.hasNext(); ) {
                                    Registration tripReg = tripIt.next();

                                    if (userRegs.contains(tripReg)) {
                                        if (tripReg.getRole() == Role.PASSENGER) {

                                            long registrationId = tripReg.getId();
                                            String deletionUrl = "api/registrations/" + registrationId;

                                            HttpRequest.withToken(MainActivity.token).delete(deletionUrl, new RequestParams(), new JsonHttpResponseHandler() {

                                                @Override
                                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                                    Intent editIntent = new Intent(viewDeregisterTripActivity.this, MyTripsActivity.class);//return to my trips activity
                                                    startActivity(editIntent);
                                                }

                                                @Override
                                                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                                    try {
                                                        throw (throwable);
                                                    } catch (Throwable throwable1) {
                                                        throwable1.printStackTrace();
                                                    }
                                                }

                                            });

                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        try {
                            throw (throwable);
                        } catch (Throwable throwable1) {
                            throwable1.printStackTrace();
                        }
                    }

                });
            }
        });


    }




}
