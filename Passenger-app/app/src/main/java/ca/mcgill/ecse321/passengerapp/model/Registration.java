package ca.mcgill.ecse321.passengerapp.model;

import java.io.Serializable;

/**
 * Created by michelabdelnour on 2018-11-01.
 */

public class Registration  implements Serializable {

    private Long id;
    private Role role;
    private User user;
    private Trip trip;

    public Registration() {
    }

    public Registration(Role role,
                        User user,
                        Trip trip) {
        setRole(role);
        setUser(user);
        setTrip(trip);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role value) {
        this.role = value;
    }

    public Role getRole() {
        return this.role;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return this.trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Registration){
            return id == ((Registration) obj).getId();
        }
        return false;
    }


    @Override
    public int hashCode(){
        return id.intValue();
    }

}
