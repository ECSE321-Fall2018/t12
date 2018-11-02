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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.mcgill.ecse321.driverapp.adapters.TripAdapter;
import ca.mcgill.ecse321.driverapp.adapters.VehicleAdapter;
import ca.mcgill.ecse321.driverapp.model.Trip;
import ca.mcgill.ecse321.driverapp.model.Vehicle;

public class MyVehiclesActivity extends AppCompatActivity implements VehicleAdapter.ItemClickListener {
    private RecyclerView myVehiclesView;

    private VehicleAdapter adapter;

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
        adapter = new VehicleAdapter(this, htmlGetVehicles());

        myVehiclesView.setLayoutManager(lm);
        myVehiclesView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    private List<Vehicle> htmlGetVehicles(){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        vehicles.add(new Vehicle("Camry", "Toyota", "Blue", null));
        vehicles.add(new Vehicle("Camry", "Toyota", "Blue", null));


        return vehicles;
    }

    public void onItemClick(View view, int position) {
        Intent mainIntent = new Intent(this, editDeleteVehicleActivity.class);
        startActivity(mainIntent);
    }
}
