package ca.mcgill.ecse321.driverapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import ca.mcgill.ecse321.driverapp.adapters.VehicleAdapter;
import ca.mcgill.ecse321.driverapp.model.Trip;
import ca.mcgill.ecse321.driverapp.model.User;
import ca.mcgill.ecse321.driverapp.model.Vehicle;

public class edit_tripActivity extends AppCompatActivity {

    private EditText newDestination;
    private EditText newLocation;
    private EditText newDate;
    private EditText newTime;
    private RecyclerView newVehicle;
    private RecyclerView newTripNode;
    private VehicleAdapter adapter;
    private Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        trip =(Trip) getIntent().getSerializableExtra("TRIP_ID");
        newDestination =(EditText) findViewById(R.id.editDestination);
        newDestination.setText(trip.getEndpoint());
        newLocation =(EditText) findViewById(R.id.editLocation);
        newLocation.setText(trip.getStartpoint());
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String printedDate= formatter.format(trip.getDate());
        newDate =(EditText) findViewById(R.id.Date_editor);
        newDate.setText(printedDate);
        newTime = (EditText) findViewById(R.id.time_editor);
        newTime.setText(trip.getStart_time().toString());
        newVehicle = (RecyclerView) findViewById(R.id.VehicleEditableList);
        populateVehicle();
        newVehicle = (RecyclerView) findViewById(R.id.TripNodeEditable);


    }
    public void deleteNodeClick(View view){

    }
    public void FinishUpdateClick(View view){

    }
    public void editNodeClick(View view){

    }
    public void UpdatevehButton(View view){

    }

    public void addnodeClick(View view) {

    }
    public void populateVehicle(){
   /*     RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        Set<Vehicle> temp= null;
        User user =(User) getIntent().getSerializableExtra("USER_ID");
        temp = user.getVehicles();
        Vehicle.setLayoutManager(lm);
        Vehicle.setAdapter(adapter);*/
    }
}
