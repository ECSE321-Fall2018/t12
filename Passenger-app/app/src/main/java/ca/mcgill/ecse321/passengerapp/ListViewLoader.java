package ca.mcgill.ecse321.passengerapp;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.database.Cursor;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;



import java.util.ArrayList;

/**
 * Created by michelabdelnour on 2018-10-31.
 */

public class ListViewLoader extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter tripAdapter;
    SimpleCursorAdapter nodeAdapter;
    ArrayList<Trip> tripList;
    ArrayList<User> userList;
    String searchText;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
