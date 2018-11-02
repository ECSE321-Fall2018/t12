package ca.mcgill.ecse321.webservice.service;

import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.webservice.model.PointType;
import ca.mcgill.ecse321.webservice.model.Registration;
import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.TripNode;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.repository.TripRepository;

@Service
@Transactional
public class TripService {

	@Autowired
	private TripRepository tripRepository;
	public Object getTrip;
	
	public Iterable<Trip> getTrips() {
		
		return tripRepository.findAll();	
	}
	public TripNode getStartTripNode(Trip trip) {
		Iterable<TripNode> tripNodes= trip.getTripNodes();
		for (TripNode tripNode :tripNodes) {
			if (tripNode.getPointType()==PointType.START) {
				return tripNode;
				
			}
		}
		return null;
		
	}
	public TripNode getEndTripNode(Trip trip) {
		Iterable<TripNode> tripNodes= trip.getTripNodes();
		for (TripNode tripNode :tripNodes) {
			if (tripNode.getPointType()==PointType.END) {
				return tripNode;
				
			}
		}
		
		return null;
	}
	
	public Optional<Trip> getTrip(long id) {
		return tripRepository.findById(id);
	}
	
	public Trip updateTrip(long id, Trip trip) {
		Trip original =this.getTrip(id).get();
		original.setActive(trip.isActive());
		original.setCompleated(trip.isCompleated());
		original.setCost_per_customer(trip.getCost_per_customer());
		original.setDistance(trip.getDistance());
		original.setEnd_time(trip.getEnd_time());
		original.setStart_time(trip.getStart_time());
		original.setSeats_available(trip.getSeats_available());
		original.setDate(trip.getDate());
		original.setEndpoint(trip.getEndpoint());
		original.setStartpoint(trip.getStartpoint());
		original.setEst_Trip_time(trip.getEst_Trip_time());
		TripNode endTripNode= getEndTripNode(original);
		TripNode startTripNode= getStartTripNode(original);
		endTripNode.setTime(trip.getEnd_time());
		endTripNode.setName(trip.getEndpoint());
		startTripNode.setTime(trip.getStart_time());
		startTripNode.setName(trip.getStartpoint());
		return tripRepository.save(original);
	}
	
	public Trip addTrip(Trip trip) {
		return tripRepository.save(trip);
	}
	
	public boolean seatsAvailable(Trip trip) {
		return trip.getSeats_available() > 0; 
	}
	
	public void deleteTrip(Trip trip) {
		tripRepository.delete(trip);
	}

	//ahahhhahhahahhah
	public Trip addTripNode(Trip trip, TripNode tripNode) {
		trip.addTripNode(tripNode);
		tripRepository.save(trip);
		return trip;
	}
	
	public Trip removeTripNode(Trip trip, TripNode tripNode) {
		trip.getTripNodes().remove(tripNode);
		return trip;
	}
}
