package model;

import javax.persistence.Entity;
import User;
import java.util.Set;
import javax.persistence.ManyToMany;
import Registration;
import Trip;

@Entity
public class RegistrationManager{
private Set<User> user;

@ManyToMany(mappedBy="registrationManager")
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

private Set<Registration> registration;

@ManyToMany(mappedBy="registrationManager")
public Set<Registration> getRegistration() {
   return this.registration;
}

public void setRegistration(Set<Registration> registrations) {
   this.registration = registrations;
}

private Set<Trip> trip;

@ManyToMany(mappedBy="registrationManager")
public Set<Trip> getTrip() {
   return this.trip;
}

public void setTrip(Set<Trip> trips) {
   this.trip = trips;
}

}
