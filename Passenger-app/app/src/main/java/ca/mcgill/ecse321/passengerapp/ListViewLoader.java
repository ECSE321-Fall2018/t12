package ca.mcgill.ecse321.passengerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import ca.mcgill.ecse321.passengerapp.R;




import java.util.ArrayList;

import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.User;

/**
 * Created by michelabdelnour on 2018-10-31.
 */

public class ListViewLoader extends AppCompatActivity {


    ArrayList<Trip> tripList;
    ArrayList<User> userList;

    String searchText;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
