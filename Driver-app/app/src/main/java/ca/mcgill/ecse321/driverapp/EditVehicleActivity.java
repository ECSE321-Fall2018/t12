package ca.mcgill.ecse321.driverapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ca.mcgill.ecse321.driverapp.model.Vehicle;

public class EditVehicleActivity extends AppCompatActivity {
    private EditText newModel;
    private EditText newMake;
    private EditText newColor;
    private Vehicle vehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vehicle =(Vehicle) getIntent().getSerializableExtra("VEHICLE_ID");
        newMake = (EditText) findViewById(R.id.MakeEditable);
        newMake.setText(vehicle.getMake());
        newColor= (EditText) findViewById(R.id.ColorEditable);
        newColor.setText(vehicle.getColor());
        newModel= (EditText) findViewById(R.id.ModelEditable);
        newModel.setText(vehicle.getModel());
    }

    private void finishVehiclebtn(){
        String ma = newMake.getText().toString();
        String mod = newModel.getText().toString();
        String col = newColor.getText().toString();


        if(ma ==""){

        }
        else if(mod==""){

        }
        else if(col==""){

        }
        else{
            updateVehicle();
        }
    }
    private void updateVehicle(){
        //update the vehicle and slit wrists
    }

}
