package ca.mcgill.ecse321.passengerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Vehicle vehicle = new Vehicle();

    int[] SEATS_AVAILABLE = {1, 3, 4, 2, 1, 3, 4};


}
