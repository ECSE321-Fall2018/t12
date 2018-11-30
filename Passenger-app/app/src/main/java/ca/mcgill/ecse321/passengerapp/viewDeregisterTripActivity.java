package ca.mcgill.ecse321.passengerapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

public class viewDeregisterTripActivity extends ViewTrip {

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            deregister();
        }
        return super.onOptionsItemSelected(item);
    }

    public void deregister(){
        String url = "api/registrations/";

        Registration registration = null;

        /**
         * Determine associated registration for trip
         */
        for (Registration reg : MainActivity.mainUser.getRegistrations()) {
            Trip t = reg.getTrip();

            if (trip.equals(t)) {
                registration = reg;
                break;
            }
        }

        // Catch unexpected errors
        if (registration == null) {
            Snackbar.make(findViewById(android.R.id.content), viewDeregisterTripActivity.this.getString(R.string.error_deregister), Snackbar.LENGTH_LONG).show();
            return;
        }

        HttpRequest.withToken(MainActivity.token).delete(url + registration.getId().longValue(),
                new RequestParams(),
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        System.out.println("Deregistered!");
                        viewDeregisterTripActivity.super.onBackPressed();
                    }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        int a = R.menu.menu_register_trip;
        getMenuInflater().inflate(R.menu.menu_deregister_trip, menu);
        return super.onCreateOptionsMenu(menu);
    }




}
