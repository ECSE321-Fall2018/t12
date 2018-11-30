package ca.mcgill.ecse321.passengerapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@JsonIdentityInfo(generator=JSOGGenerator.class)
public class Position implements Serializable {

    private Long id;
    private Set<TripNode> tripNodes = new HashSet<>();
    private String position;

    public Position() {

    }

    public Position(String position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<TripNode> getTripNodes() {
        return this.tripNodes;
    }

    public void setTripNodes(Set<TripNode> tripNodes) {
        this.tripNodes = tripNodes;
    }

    public void addTripNode(TripNode tripNode) {
        this.tripNodes.add(tripNode);
        tripNode.setPosition(this);
    }

    public void setPosition(String value) {
        this.position = value;
    }

    public String getPosition() {
        return this.position;
    }

}
