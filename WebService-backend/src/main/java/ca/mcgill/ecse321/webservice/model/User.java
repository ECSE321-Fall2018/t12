package ca.mcgill.ecse321.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Usr")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="id")
public class User{
	
	
	private Long id; 
	private String name;
	private String username;
	private float Dweight = 5;
	private float Pweight = 5; 
	private String password;
	private float drivingRate;
	private float passRate;
	private Set<Registration> registrations = new HashSet<>();
	private Set<Vehicle> vehicles = new HashSet<>();
	
	// Default empty constructor
	public User() { 
	}
	
	public User(String name,
				String username, 
				String password,
				float drivingRate,
				float passRate) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.drivingRate = drivingRate;
		this.passRate = passRate;
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setDrivingRate(float value) {
		this.drivingRate = value;
	}

	public float getDrivingRate() {
		return this.drivingRate;
	}

	public void setPassRate(float value) {
		this.passRate = value;
	}

	public float getPassRate() {
		return this.passRate;
	}

	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	public Set<Registration> getRegistrations() {
		return this.registrations;
	}

	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}


	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	public Set<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void updatePassRating(int score){
		Pweight++; 
		if (score > this.passRate){
			this.passRate = passRate + (score/Pweight);
		}
		else if(score < this.passRate){
			this.passRate = passRate - (score/Pweight);
		}
	}

	public void updateDriverRating(int score){
		Dweight++;
		if (score > this.drivingRate){
			this.drivingRate = drivingRate + (score/Dweight);
		}
		else if(score < this.drivingRate){
			this.drivingRate = drivingRate - (score/Dweight);
		}
	}

}
