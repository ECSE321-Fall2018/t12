import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Car{
private String model;
   
   public void setModel(String value) {
this.model = value;
    }
public String getModel() {
return this.model;
    }
private Set<Trip> trip;

@OneToMany(mappedBy="car")
public Set<Trip> getTrip() {
   return this.trip;
}

public void setTrip(Set<Trip> trips) {
   this.trip = trips;
}

private Set<User> user;

@ManyToMany(mappedBy="car")
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

}
