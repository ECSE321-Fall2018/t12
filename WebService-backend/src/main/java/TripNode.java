import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TripNode{
private Time time;
   
   public void setTime(Time value) {
this.time = value;
    }
public Time getTime() {
return this.time;
    }
private Position position;

public void setPosition(Position value) {
this.position = value;
    }
public Position getPosition() {
return this.position;
    }
private PointType type;

public void setType(PointType value) {
this.type = value;
    }
public PointType getType() {
return this.type;
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
