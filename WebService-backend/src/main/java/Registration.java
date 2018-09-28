import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import model.RegistrationManager;
import java.util.Set;
import javax.persistence.ManyToMany;

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

private Set<RegistrationManager> registrationManager;

@ManyToMany
public Set<RegistrationManager> getRegistrationManager() {
   return this.registrationManager;
}

public void setRegistrationManager(Set<RegistrationManager> registrationManagers) {
   this.registrationManager = registrationManagers;
}

}
