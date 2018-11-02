package ca.mcgill.ecse321.passengerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.passengerapp.R;
import ca.mcgill.ecse321.passengerapp.model.Trip;
import ca.mcgill.ecse321.passengerapp.model.User;

/**
 * Created by michelabdelnour on 2018-11-01.
 */

public class TripAdapter extends ArrayAdapter<Trip> implements View.OnClickListener {

    List<Trip> mData;
    int mResource;
    Context mContext;
    ProgressBar pb;

    public TripAdapter(Context context, int resource, List<Trip> objects) {
        super(context, resource, objects);
        this.mResource = resource;
        this.mContext = context;
        this.mData = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        final Trip trip = mData.get(position);
        TextView tripName = (TextView) convertView.findViewById(R.id.driverTrip);
        TextView createdBy = (TextView) convertView.findViewById(R.id.driverCreatedByText);
        Button viewBtn = (Button) convertView.findViewById(.id.passengerTripView);
        final Button joinBtn = (Button) convertView.findViewById(.id.passengerTripJoin);


        title.setText(trip.getStartpoint());
        title.setText(toString(trip.getSeats_available());
        return convertView;
    }


}
