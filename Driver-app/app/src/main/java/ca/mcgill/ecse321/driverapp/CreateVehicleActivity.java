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

public class CreateVehicleActivity extends AppCompatActivity {

    private EditText Color;
    private EditText Make;
    private EditText Model;
    private TextView errorTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Color = (EditText) findViewById(R.id.Colortxt);
        Make = (EditText) findViewById(R.id.Maketxt);
        Model = (EditText) findViewById(R.id.Modeltxt);
        errorTxt = (TextView) findViewById(R.id.errorTxt);
    }


    private void FinishBtnClick(View view){
        String MakeString = Make.getText().toString();
        String modelString = Model.getText().toString();
        String colorString = Color.getText().toString();
        if(modelString == ""){
            errorTxt.setText("Unable to complete: Please input a Model");
        }
        else if (MakeString == ""){
            errorTxt.setText("Unable to complete: Please input a Make");
        }
        else if(colorString ==""){
            errorTxt.setText("Unable to complete: Please input a Color");
        }
        else {
            createVehicle();
        }
    }

    private void createVehicle(){
        //create vehicle
    }
}
