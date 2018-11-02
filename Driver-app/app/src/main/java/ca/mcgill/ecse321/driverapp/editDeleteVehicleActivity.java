package ca.mcgill.ecse321.driverapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ca.mcgill.ecse321.driverapp.model.Vehicle;

public class editDeleteVehicleActivity extends AppCompatActivity {


    private TextView makeset;
    private TextView modelset;
    private TextView colorset;
    private Vehicle vehicle;
    private TextView errortxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        makeset = (TextView) findViewById(R.id.MakeDisplay);
        vehicle =(Vehicle) getIntent().getSerializableExtra("VEHICLE_ID");
        makeset.setText(vehicle.getMake());
        modelset = (TextView) findViewById(R.id.ModelDisplay);
        modelset.setText(vehicle.getModel());
        colorset = (TextView) findViewById(R.id.ColorDisplay);
        colorset.setText(vehicle.getColor());
        errortxt = (TextView)findViewById(R.id.ErrrorMessage);
        errortxt.setText("");

    }
    public void EditVehiclebtnClick(View view){
        Intent editIntent = new Intent(this, EditVehicleActivity.class);
        startActivity(editIntent);
    }
    public void DeleteVehiclebtnClick(View view){
        //Delete System 32
    }

}
