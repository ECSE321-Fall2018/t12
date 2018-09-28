package ca.mcgill.ecse321.webservice.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle{
private String model;
   
   public void setModel(String value) {
this.model = value;
    }
public String getModel() {
return this.model;
    }
private Set<Trip> trip;

@OneToMany(mappedBy="vehicle")
public Set<Trip> getTrip() {
   return this.trip;
}

public void setTrip(Set<Trip> trips) {
   this.trip = trips;
}

private User user;

@ManyToOne(optional=false)
public User getUser() {
   return this.user;
}

public void setUser(User user) {
   this.user = user;
}

private String make;

public void setMake(String value) {
this.make = value;
    }
public String getMake() {
return this.make;
    }
private String color;

public void setColor(String value) {
this.color = value;
    }
public String getColor() {
return this.color;
       }
   }
