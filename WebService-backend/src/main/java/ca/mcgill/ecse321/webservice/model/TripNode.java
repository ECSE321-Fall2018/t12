package ca.mcgill.ecse321.webservice.model;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id")
public class TripNode{

	private Long id; 
	private Position position;
	private PointType pointType;
	private Time time;
	private Trip trip;
	
	public TripNode() {
		
	}
	
	public TripNode(Position position, PointType pointType, Time time, Trip trip) {
		super();
		this.position = position;
		this.pointType = pointType;
		this.time = time;
		this.trip = trip;
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	public Position getPosition() {
		return this.position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}


	public void setPointType(PointType value) {
		this.pointType = value;
    }

	@Enumerated(EnumType.STRING)
	public PointType getPointType() {
		return this.pointType;
    }
   
   public void setTime(Time value) {
	   this.time = value;
   }

   public Time getTime() {
	   return this.time;
   }

   @ManyToOne(cascade=CascadeType.ALL, optional=false)
   public Trip getTrip() {
	   return this.trip;
   }

   public void setTrip(Trip trip) {
	   this.trip = trip;
   }

}
