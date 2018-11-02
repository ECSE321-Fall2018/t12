package ca.mcgill.ecse321.driverapp.model;

import java.io.Serializable;
import java.sql.Time;


public class TripNode implements Serializable {

    private Long id;
    private Position position;
    private PointType pointType;
    private String name;
    private String time;
    private Trip trip;
    // maybe in the future have an x and y position to put on a map

    public TripNode() {
        this.setPosition(new Position("ontario"));
    }

    public TripNode(String name, PointType pointType,String time) {
        super();
        this.name= name;
        //this.position = position;
        this.pointType = pointType;
        this.time = time;
        //new Time(time);
        //this.position= new Position("ontario");
        this.setPosition(new Position("ontario"));
        System.out.println(this.position.getId());
        //this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }



    public void setPointType(PointType value) {
        this.pointType = value;
    }

    public PointType getPointType() {
        return this.pointType;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public String getTime() {
        return this.time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Trip getTrip() {
        return this.trip;
    }

    public void setTrip(Trip trip) {
        //trip.addTripNode(this);
        this.trip = trip;
    }
}
