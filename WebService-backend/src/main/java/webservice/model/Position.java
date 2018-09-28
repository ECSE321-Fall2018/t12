package webservice.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Position{
private Set<TripNode> tripNode;

@OneToMany(mappedBy="position")
public Set<TripNode> getTripNode() {
   return this.tripNode;
}

public void setTripNode(Set<TripNode> tripNodes) {
   this.tripNode = tripNodes;
}

private String position;

public void setPosition(String value) {
this.position = value;
    }
public String getPosition() {
return this.position;
       }
   }
