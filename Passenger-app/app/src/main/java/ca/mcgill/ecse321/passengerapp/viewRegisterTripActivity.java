package ca.mcgill.ecse321.passengerapp;


import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import ca.mcgill.ecse321.passengerapp.util.HttpRequest;
import cz.msebera.android.httpclient.Header;

public class viewRegisterTripActivity extends ViewTrip {

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            register();
        }
        return super.onOptionsItemSelected(item);
    }

    private void register() {
        String url = "api/users/";


        HttpRequest.withToken(MainActivity.token).post(url + MainActivity.mainUser.getId()
                + "/trips/" + this.trip.getId() + "/registrations",
                new RequestParams(),
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        System.out.println("Registered!");
                        viewRegisterTripActivity.super.onBackPressed();
                    }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        int a = R.menu.menu_register_trip;
        getMenuInflater().inflate(R.menu.menu_register_trip, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
