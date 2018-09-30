package ca.mcgill.ecse321.webservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Set;

import javax.persistence.OneToMany;

@Entity
public class Position{

	private Long id;
	private Set<TripNode> tripNodes;
	private String position;
	
	public Position() {
		
	}
	
	public Position(Set<TripNode> tripNodes, String position) {
		this.tripNodes = tripNodes;
		this.position = position;
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="position")
	public Set<TripNode> getTripNodes() {
		return this.tripNodes;
	}

	public void setTripNodes(Set<TripNode> tripNodes) {
		this.tripNodes = tripNodes;
	}


	public void setPosition(String value) {
		this.position = value;
	}

	public String getPosition() {
		return this.position;
	}

}
