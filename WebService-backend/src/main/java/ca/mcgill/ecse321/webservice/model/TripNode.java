package ca.mcgill.ecse321.webservice.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TripNode{
private Position position;

@ManyToOne(optional=false)
public Position getPosition() {
   return this.position;
}

public void setPosition(Position position) {
   this.position = position;
}

private PointType pointType;

public void setPointType(PointType value) {
this.pointType = value;
    }
public PointType getPointType() {
return this.pointType;
    }
private Time time;
   
   public void setTime(Time value) {
this.time = value;
    }
public Time getTime() {
return this.time;
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
