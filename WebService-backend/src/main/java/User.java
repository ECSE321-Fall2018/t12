import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import model.RegistrationManager;
import javax.persistence.ManyToMany;

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
private String previousModel;

public void setPreviousModel(String value) {
this.previousModel = value;
    }
public String getPreviousModel() {
return this.previousModel;
    }
private Set<Registration> registration;

@OneToMany(mappedBy="user")
public Set<Registration> getRegistration() {
   return this.registration;
}

public void setRegistration(Set<Registration> registrations) {
   this.registration = registrations;
}

private Set<RegistrationManager> registrationManager;

@ManyToMany
public Set<RegistrationManager> getRegistrationManager() {
   return this.registrationManager;
}

public void setRegistrationManager(Set<RegistrationManager> registrationManagers) {
   this.registrationManager = registrationManagers;
}

private Set<Car> car;

@ManyToMany
public Set<Car> getCar() {
   return this.car;
}

public void setCar(Set<Car> cars) {
   this.car = cars;
}

}
