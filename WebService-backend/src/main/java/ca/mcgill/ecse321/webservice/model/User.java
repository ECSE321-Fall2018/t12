package ca.mcgill.ecse321.webservice.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class User{
private String name;
   
   public void setName(String value) {
this.name = value;
    }
public String getName() {
return this.name;
    }
private String iDNumber;

public void setIDNumber(String value) {
this.iDNumber = value;
    }
public String getIDNumber() {
return this.iDNumber;
    }
private String password;

public void setPassword(String value) {
this.password = value;
    }
public String getPassword() {
return this.password;
    }
private String username;

public void setUsername(String value) {
this.username = value;
    }
public String getUsername() {
return this.username;
    }
private int drivingRate;

public void setDrivingRate(int value) {
this.drivingRate = value;
    }
public int getDrivingRate() {
return this.drivingRate;
    }
private int passRate;

public void setPassRate(int value) {
this.passRate = value;
    }
public int getPassRate() {
return this.passRate;
    }
private Set<Registration> registration;

@OneToMany(mappedBy="user")
public Set<Registration> getRegistration() {
   return this.registration;
}

public void setRegistration(Set<Registration> registrations) {
   this.registration = registrations;
}

private Set<Vehicle> vehicle;

@OneToMany(mappedBy="user")
public Set<Vehicle> getVehicle() {
   return this.vehicle;
}

public void setVehicle(Set<Vehicle> vehicles) {
   this.vehicle = vehicles;
}

}
