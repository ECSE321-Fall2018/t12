package ca.mcgill.ecse321.passengerapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class DisplaySpecificTripAlreadySignedUpActivityFragment extends Fragment {

    public DisplaySpecificTripAlreadySignedUpActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_specific_trip_already_signed_up, container, false);
    }
}
