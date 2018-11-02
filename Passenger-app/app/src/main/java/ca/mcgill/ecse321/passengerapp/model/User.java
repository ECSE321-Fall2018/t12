package ca.mcgill.ecse321.passengerapp.model;

/**
 * Created by michelabdelnour on 2018-10-31.
 */

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class User implements Serializable {


    private Long id;
    private String name;
    private String username;
    private float Dweight = 5;
    private float Pweight = 5;
    private String password;
    private float drivingRate;
    private float passRate;
    private Set<Registration> registrations = new HashSet<>();
    private Set<Vehicle> vehicles = new HashSet<>();
    private Set<UserRole> roles = new HashSet<>();

    // Default empty constructor
    public User() {
    }

    public User(String name,
                String username,
                String password,
                float drivingRate,
                float passRate) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.drivingRate = drivingRate;
        this.passRate = passRate;
    }

    public Long getId() {
        return id;
    }

    public float getDweight() {
        return Dweight;

    }
    public float getPweight() {
        return Pweight;
    }
    public void setPweight(float Pweight) {
        this.Pweight = Pweight;
    }
    public void setDweight(float Dweight) {
        this.Dweight = Dweight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setDrivingRate(float value) {
        this.drivingRate = value;
    }

    public float getDrivingRate() {
        return this.drivingRate;
    }

    public void setPassRate(float value) {
        this.passRate = value;
    }

    public float getPassRate() {
        return this.passRate;
    }

    public Set<Registration> getRegistrations() {
        return this.registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }

    public void addRegistration(Registration registration) {
        this.registrations.add(registration);
        registration.setUser(this);
    }

    public Set<Vehicle> getVehicles() {
        return this.vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        vehicle.setUser(this);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
        vehicle.setUser(null);
    }

    public Set<UserRole> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public void addRole(UserRole role) {
        roles.add(role);
    }

    public void updatePassRating(int score){
        Pweight++;
        if (score > this.passRate){
            this.passRate = passRate + (score/Pweight);
        }
        else if(score < this.passRate){
            this.passRate = passRate - (score/Pweight);
        }
    }

    public void updateDriverRating(int score){
        Dweight++;
        if (score > this.drivingRate){
            this.drivingRate = drivingRate + (score/Dweight);
        }
        else if(score < this.drivingRate){
            this.drivingRate = drivingRate - (score/Dweight);
        }
    }

}