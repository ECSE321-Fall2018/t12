package ca.mcgill.ecse321.webservice.model;

import javax.persistence.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id")
public class Vehicle{
	
	private Long id; 
	private String model;
	private String make;
	private String color;
	private Set<Trip> trips = new HashSet<>();
	private User user;
   
	public Vehicle() {
	}
	
	public Vehicle(String model,
				   String make,
				   String color,
				   User user) {
		setModel(model);
		setMake(make);
		setColor(color);
		setUser(user);
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setModel(String value) {
		this.model = value;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public void setMake(String value) {
		this.make = value;
	}
	
	public String getMake() {
		return this.make;
	}
	
	public void setColor(String value) {
		this.color = value;
	}
	
	public String getColor() {
		return this.color;
	}
		  

	@OneToMany(cascade=CascadeType.ALL, mappedBy="vehicle")
	@JsonManagedReference
	public Set<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}
	
	public void addTrip(Trip trip) {
		this.trips.add(trip);
		trip.setVehicle(this);
	}


	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JsonBackReference
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}