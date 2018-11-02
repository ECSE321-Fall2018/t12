package ca.mcgill.ecse321.driverapp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Vehicle implements Serializable {

    private Long id;
    private String model;
    private String make;
    private String color;
    private Set<Trip> trips = new HashSet<>();
    private User user;

    public Vehicle() {
    }

    public Vehicle(String model,
                   String make,
                   String color,
                   User user) {
        setModel(model);
        setMake(make);
        setColor(color);
        setUser(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String value) {
        this.model = value;
    }

    public String getModel() {
        return this.model;
    }

    public void setMake(String value) {
        this.make = value;
    }

    public String getMake() {
        return this.make;
    }

    public void setColor(String value) {
        this.color = value;
    }

    public String getColor() {
        return this.color;
    }


    public Set<Trip> getTrips() {
        return this.trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
        trip.setVehicle(this);
    }



    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
