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

import java.util.List;

import ca.mcgill.ecse321.passengerapp.R;
import ca.mcgill.ecse321.passengerapp.model.Trip;

/**
 * Created by michelabdelnour on 2018-11-01.
 */

public class TripAdapter extends ArrayAdapter<Trip> implements View.OnClickListener {

    List<Trip> tripList;
    int mResource;
    Context mContext;
    ProgressBar pb;
    IData activity;

    public TripByDriver(Context context, int resource, List<Trip> objects, IData activity) {
        super(context, resource, objects);
        this.mResource = resource;
        this.mContext = context;
        this.tripList = objects;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(mResource,parent,false);
        }

        final Trip trip = tripList.get(position);
        pb = (ProgressBar) convertView.findViewById(R.id.tripByDriver);
        ImageView tripIcon = (ImageView) convertView.findViewById(R.id.driverTripIcon);
        TextView title = (TextView)convertView.findViewById(R.id.driverTrip);
        TextView createdBy = (TextView)convertView.findViewById(R.id.driverCreatedByText);
        Button viewBtn = (Button)convertView.findViewById(R.id.passengerTripView);
        final Button joinBtn = (Button)convertView.findViewById(R.id.passengerTripJoin);
        if(trip.getCoverpicUrl()!=null) {
            if(!trip.getCoverpicUr().isEmpty()) {
                Picasso.with(mContext).load(trip.getCoverpicUrl()).error(R.drawable.ic_flight_takeoff_black_24dp)
                        .placeholder(R.drawable.ic_flight_takeoff_black_24dp)
                        .into(tripIcon, new ImageLoadedCallback(pb) {
                            public void onSuccess() {
                                if (pb != null)
                                    pb.setVisibility(View.GONE);
                            }

                            public void onError() {
                                pb.setVisibility(View.GONE);
                            }
                        });
            }
            else
            {
                pb.setVisibility(View.GONE);
                tripIcon.setBackgroundResource(R.drawable.ic_flight_takeoff_black_24dp);
            }
        }
        else {
            pb.setVisibility(View.GONE);
            tripIcon.setBackgroundResource(R.drawable.ic_flight_takeoff_black_24dp);
        }

        title.setText(trip.getTripname());
        createdBy.setText(trip.createdUserName);
        ArrayList<String> memberIds=new ArrayList<String>();
        for (User u:trip.getMembers()) {
            memberIds.add(u.getUserid());
        }
       
        }
        else if(memberIds.contains(fUser.getUid())){
            joinBtn.setText("Joined");
            joinBtn.setEnabled(false);
        }

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.friendViewTrip(trip);
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert=new AlertDialog.Builder(v.getContext());
                alert.setMessage("Are you sure to join the group "+trip.getTripname()+"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                joinBtn.setText("Joined");
                                joinBtn.setEnabled(false);
                                activity.friendTripJoin(trip);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });

        return convertView;
    }

}
