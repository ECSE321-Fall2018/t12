package ca.mcgill.ecse321.webservice.service;

import java.sql.Time;
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
	
	public Iterable<Trip> getTrips() {
		
		/*if (tripRepository.count() == 0) {
			Trip trip = new Trip();
			trip.setStartpoint("Toronto");
			trip.setEndpoint("Montreal");
			trip.setDistance(1000);
			trip.setActive(true);
			trip.setStart_time(Time.valueOf("01:40:02"));
			trip.setEnd_time(Time.valueOf("15:00:00"));
			trip.setEst_Trip_time(100);
			trip.setSeats_available(4);
			trip.setCompleated(false);
			
			
			Vehicle v = new Vehicle();
			v.setUser(new User());
			trip.setVehicle(v);
			
			tripRepository.save(trip);
		}		*/
		
		return tripRepository.findAll();	
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
		original.setEndpoint(trip.getEndpoint());
		original.setStartpoint(trip.getStartpoint());
		original.setEst_Trip_time(trip.getEst_Trip_time());
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
