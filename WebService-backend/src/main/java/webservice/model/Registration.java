package webservice.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Registration{
private Role role;
   
   public void setRole(Role value) {
this.role = value;
    }
public Role getRole() {
return this.role;
    }
private User user;

@ManyToOne(optional=false)
public User getUser() {
   return this.user;
}

public void setUser(User user) {
   this.user = user;
}

private Trip trip;

@ManyToOne(optional=false)
public Trip getTrip() {
   return this.trip;
}

public void setTrip(Trip trip) {
   this.trip = trip;
}

}
