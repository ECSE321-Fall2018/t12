package ca.mcgill.ecse321.webservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin{
	
	private Long id;
	private String username;
	private String password;
	
	public Admin() {
		
	}
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUsername(String value) {
		this.username = value;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String value) {	
		this.password = value;
    }
	
	public String getPassword() {
		return this.password;
    }
	
}
