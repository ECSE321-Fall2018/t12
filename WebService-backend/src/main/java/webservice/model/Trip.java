package webservice.model;

import javax.persistence.Entity;
import java.sql.Time;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Trip{
private String startpoint;
   
   public void setStartpoint(String value) {
this.startpoint = value;
    }
public String getStartpoint() {
return this.startpoint;
    }
private String endpoint;

public void setEndpoint(String value) {
this.endpoint = value;
    }
public String getEndpoint() {
return this.endpoint;
    }
private int distance;

public void setDistance(int value) {
this.distance = value;
    }
public int getDistance() {
return this.distance;
    }
private boolean active;

public void setActive(boolean value) {
this.active = value;
    }
public boolean isActive() {
return this.active;
    }
private Time start_time;

public void setStart_time(Time value) {
this.start_time = value;
    }
public Time getStart_time() {
return this.start_time;
    }
private Time end_time;

public void setEnd_time(Time value) {
this.end_time = value;
    }
public Time getEnd_time() {
return this.end_time;
    }
private int est_Trip_time;

public void setEst_Trip_time(int value) {
this.est_Trip_time = value;
    }
public int getEst_Trip_time() {
return this.est_Trip_time;
    }
private int seats_available;

public void setSeats_available(int value) {
this.seats_available = value;
    }
public int getSeats_available() {
return this.seats_available;
    }
private boolean compleated;

public void setCompleated(boolean value) {
this.compleated = value;
    }
public boolean isCompleated() {
return this.compleated;
    }
private Set<Registration> registration;

@OneToMany(mappedBy="trip")
public Set<Registration> getRegistration() {
   return this.registration;
}

public void setRegistration(Set<Registration> registrations) {
   this.registration = registrations;
}

private Set<TripNode> tripNode;

@OneToMany(mappedBy="trip")
public Set<TripNode> getTripNode() {
   return this.tripNode;
}

public void setTripNode(Set<TripNode> tripNodes) {
   this.tripNode = tripNodes;
}

private Vehicle vehicle;

@ManyToOne(optional=false)
public Vehicle getVehicle() {
   return this.vehicle;
}

public void setVehicle(Vehicle vehicle) {
   this.vehicle = vehicle;
}

}
