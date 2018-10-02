package ca.mcgill.ecse321.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Set;

import javax.persistence.OneToMany;

@Entity
@Table(name = "Usr")
public class User{
	
	
	private Long id; 
	private String name;
	private String username;
	private float Dweight = 5.0;
	private float Pweight = 5.0; 
	private String password;
	private float drivingRate;
	private float passRate;
	private Set<Registration> registrations;
	private Set<Vehicle> vehicles;
	
	// Default empty constructor
	public User() { 
	}
	
	public User(String name,
				String username, 
				String password,
				float drivingRate,
				float passRate,
				Set<Registration> registrations,
				Set<Vehicle> vehicles) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.drivingRate = drivingRate;
		this.passRate = passRate;
		this.registrations = registrations;
		this.vehicles = vehicles;
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
	
	public static void updatePassRating(int score){
		Pweight++; 
		if (score > pass_rating){
			pass_rating = pass_rating + (score/Pweight);
		}
		else if(score < pass_rating){
			pass_rating = pass_rating - (score/Pweight);
		}
	}

	public static void updateDriverRating(int score){
		Dweight++;
		if (score > drive_rating){
			drive_rating = drive_rating + (score/Dweight);
		}
		else if(score < drive_rating){
			drive_rating = drive_rating - (score/Dweight);
		}
	}

}
