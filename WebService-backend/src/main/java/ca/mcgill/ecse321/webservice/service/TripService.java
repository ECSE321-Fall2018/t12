package ca.mcgill.ecse321.webservice.service;

import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public Optional<Trip> getTrip(long id) {
		return tripRepository.findById(id);
	}
	
	public Trip updateTrip(long id, Trip trip) {
		return tripRepository.save(trip);
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
