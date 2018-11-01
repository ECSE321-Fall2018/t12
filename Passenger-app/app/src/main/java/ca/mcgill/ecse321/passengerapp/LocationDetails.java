package ca.mcgill.ecse321.passengerapp;

import java.io.Serializable;

/**
 * Created by michelabdelnour on 2018-10-31.
 */

public class LocationDetails implements Serializable{

    String locationId,locationName;

    public LocationDetails(String locationId, String locationName, String locationAddress, Double locLatitude, Double locLongitude) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public LocationDetails() {
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
