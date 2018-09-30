package ca.mcgill.ecse321.webservice.model;

import javax.persistence.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle{
	
	private Long id; 
	private String model;
	private String make;
	private String color;
	private Set<Trip> trips;
	private User user;
   
	public Vehicle() {
	}
	
	public Vehicle(String model,
				   String make,
				   String color,
				   Set<Trip> trips,
				   User user) {
		setModel(model);
		setMake(make);
		setColor(color);
		setTrip(trips);
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
	public Set<Trip> getTrip() {
		return this.trips;
	}

	public void setTrip(Set<Trip> trips) {
		this.trips = trips;
	}


	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}