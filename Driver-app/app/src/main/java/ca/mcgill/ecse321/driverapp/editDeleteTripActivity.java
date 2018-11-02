package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ca.mcgill.ecse321.driverapp.model.Trip;

public class editDeleteTripActivity extends AppCompatActivity {

    //This activity views a trip before editing/ deletion
    private Trip trip;
    private TextView errorTxt;
    private TextView Destination;
    private TextView Location;
    private TextView Date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Trip trip =(Trip) getIntent().getSerializableExtra("Trip_ID");
        Destination =(TextView) findViewById(R.id.DestinationDisplay);
        Destination.setText(trip.getEndpoint());
        Location =(TextView) findViewById(R.id.StartpointDisplay);
        Location.setText(trip.getStartpoint());
        Date =(TextView) findViewById(R.id.DateDisplay);
        Location.setText(trip.getDate());


    }

    private void editbtnClick(){
        Intent editIntent = new Intent(this, edit_tripActivity.class);
        startActivity(editIntent);
    }
    private void deletebtnClick(){
       //Delete Trip, notify all registered the trip is cancelled
    }

}
