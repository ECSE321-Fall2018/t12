package ca.mcgill.ecse321.webservice.model;

import javax.persistence.Entity;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@Entity
@JsonIdentityInfo(generator=JSOGGenerator.class)
public class Trip {

	private Long id; 
	private String startpoint;
	private String endpoint;
	private int distance;
	private boolean active;
	private String start_time;
	private String end_time;
	private int est_Trip_time;
	private int seats_available;
	private String date;
	private boolean compleated;
	private Vehicle vehicle;
	private int cost_per_customer;
	
	private Set<Registration> registrations = new HashSet<>();
	private Set<TripNode> tripNodes = new HashSet<>();
	
	
	public Trip() {
		
	}
	
	public Trip(
			String startpoint, 
			String endpoint, 
			int distance,
			boolean active, 
			String start_time, 
			String end_time, 
			int est_Trip_time,
			int seats_available, 
			String date,
			boolean compleated,
			Vehicle vehicle
			, int cost_per_customer) {
		this.startpoint = startpoint;
		this.endpoint = endpoint;
		this.distance = distance;
		this.active = active;
		this.start_time = start_time;
		this.end_time = end_time;
		this.est_Trip_time = est_Trip_time;
		this.seats_available = seats_available;
		this.date = date;
		this.compleated = compleated;
		this.vehicle = vehicle;
		this.cost_per_customer= cost_per_customer;
	}
	

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setCost_per_customer(int i) {
		this.cost_per_customer= i; 
	}
	public int getCost_per_customer() {
		return this.cost_per_customer;
	}

  public String getStartpoint() {
		return startpoint;
	}

	public void setStartpoint(String startpoint) {
		this.startpoint = startpoint;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getEst_Trip_time() {
		return est_Trip_time;
	}

	public void setEst_Trip_time(int est_Trip_time) {
		this.est_Trip_time = est_Trip_time;
	}

	public int getSeats_available() {
		return seats_available;
	}

	public void setSeats_available(int seats_available) {
		this.seats_available = seats_available;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void decrementAvailableSeats() {
		if (this.seats_available > 0) this.seats_available--;
	}

	public boolean isCompleated() {
		return compleated;
	}

	public void setCompleated(boolean compleated) {
		this.compleated = compleated;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="trip")
	public Set<Registration> getRegistrations() {
		return this.registrations;
	}
	
	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}
	
	public void addRegistration(Registration registration) {
		this.registrations.add(registration);
		registration.setTrip(this);
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	public Set<TripNode> getTripNodes() {
		return this.tripNodes;
	}
	
	public void setTripNodes(Set<TripNode> tripNodes) {
		this.tripNodes = tripNodes;
	}
	
	public void addTripNode(TripNode tripNode) {
		this.tripNodes.add(tripNode);
	}
	
	@ManyToOne(cascade=CascadeType.ALL,optional=false)
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
