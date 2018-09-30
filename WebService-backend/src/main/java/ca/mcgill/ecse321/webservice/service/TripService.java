package ca.mcgill.ecse321.webservice.service;

import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.repository.TripRepository;

@Service
@Transactional
public class TripService {

	@Autowired
	private TripRepository tripRepository;
	
	public Iterable<Trip> getTrips() {
		
		if (tripRepository.count() == 0) {
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
		}
		
		return tripRepository.findAll();	
	}
	
	public Optional<Trip> getTrip(long id) {
		return tripRepository.findById(id);
	}
	
}
