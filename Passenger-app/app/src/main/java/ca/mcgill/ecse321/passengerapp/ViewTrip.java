package ca.mcgill.ecse321.passengerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.passengerapp.model.Registration;
import ca.mcgill.ecse321.passengerapp.model.Role;
import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.TripNode;

public class ViewTrip extends AppCompatActivity {

    private Trip trip;
    private List<String> stops;
    private List<String> passengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);


        // Get trip via intent
        trip = (Trip) getIntent().getSerializableExtra("TRIP_ID");
        stops = new ArrayList<>();
        passengers = new ArrayList<>();

        // Get driver name
        String driverName = "Error";
        for (Registration reg : trip.getRegistrations()) {
            if (reg.getRole() == Role.DRIVER) {
                driverName = reg.getUser().getName();
                break;
            }
        }


        // Add each trip node to the stops array list
        for (TripNode node : trip.getTripNodes()) {
            stops.add(node.getName());
        }

        // Add each passenger to list
        for (Registration reg : trip.getRegistrations()) {
            passengers.add(reg.getUser().getName());
        }


        // Fill fields
        ((TextView) findViewById(R.id.DateDisplay)).setText(trip.getDate());
        ((TextView) findViewById(R.id.TimeDisplay)).setText(trip.getStart_time());
        ((TextView) findViewById(R.id.LengthDisplay)).setText(trip.getDistance() + "km");
        ((TextView) findViewById(R.id.ArrivalDisplay)).setText(trip.getEnd_time());
        ((TextView) findViewById(R.id.DriverDisplay)).setText(driverName);
        ((TextView) findViewById(R.id.VehicleDisplay)).setText(trip.getVehicle().getModel());


        ArrayAdapter<String> stopsAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, stops);
        ArrayAdapter<String> passengersAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, passengers);


        ((ListView) findViewById(R.id.StopsList)).setAdapter(stopsAdapter);
        ((ListView) findViewById(R.id.PassengersList)).setAdapter(passengersAdapter);

        supportInvalidateOptionsMenu();

    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        int a = R.menu.menu_view_trip;
        getMenuInflater().inflate(R.menu.menu_view_trip, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
