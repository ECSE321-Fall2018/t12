package ca.mcgill.ecse321.webservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.webservice.model.Registration;
import ca.mcgill.ecse321.webservice.model.Role;
import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.TripNode;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.service.TripNodeService;
import ca.mcgill.ecse321.webservice.service.TripService;
import ca.mcgill.ecse321.webservice.service.UserService;
import ca.mcgill.ecse321.webservice.service.VehicleService;

@RestController
@RequestMapping("/api/")
public class TripController {
	
	public static final Logger logger = LoggerFactory.getLogger(TripController.class);

	@Autowired
	private TripService tripService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TripNodeService tripNodeService;
	
	@Autowired
	private VehicleService vehicleService;
	
	/**
	 * 
	 * Returns all trips
	 * 
	 * URL: http://hostname:port/api/trips
	 * HTTP method: GET
	 * 
	 */
	@RequestMapping(value="/trips", method = RequestMethod.GET)
	public ResponseEntity<?> getTrips() {
		logger.info("GET * TRIPS");
		Iterable<Trip> tripList = tripService.getTrips();
		return new ResponseEntity<>(tripList, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Returns all trip associated with a given user
	 * 
	 * URL: http://hostname:port/api/users/{userId}/trips
	 * HTTP method: GET
	 *
	 */
	@RequestMapping(value="/users/{userId}/trips", method = RequestMethod.GET)
	public ResponseEntity<?> getTripsByUser(@PathVariable long userId) {		
		
		Set<Registration> registrations;
		
		try {
			registrations = userService.getRegistrations(userId);
		} catch(IllegalArgumentException e)
		{
			logger.error(e.getMessage());
			return new ResponseEntity<String>("User with id " + userId + " not found", HttpStatus.NOT_FOUND);
		}
		
		List<Trip> trips = new ArrayList<>();
		for (Registration registration : registrations)
		{
			trips.add(registration.getTrip());
		}
		
		return new ResponseEntity<>(trips, HttpStatus.OK);
	}
	
	/**
     * 
     * Returns a specific trip given a ID
     * 
     * URL: http://hostname:port/api/trips/{tripId}
     * HTTP method: GET
     * 
     */		
	@RequestMapping(value="/trips/{tripId}", method = RequestMethod.GET)
	public ResponseEntity<?> getTrip(@PathVariable long tripId) {
		Optional<Trip> trip = tripService.getTrip(tripId);
		return new ResponseEntity<>(trip, HttpStatus.OK);
	}	
	
	/**
     * 
     * Creates a new trip associated with a given user
     * 
     * URL: http://hostname:port/api/uesrs/{userId}/trips/
     * HTTP method: POST
     * 
     */	
	@RequestMapping(value="/users/{userId}/vehicles/{vehicleId}/trips", method = RequestMethod.POST)
	public ResponseEntity<?> addUsersTrip(@PathVariable long userId, @PathVariable long vehicleId, @RequestBody Trip trip) {
		
		// If user is not found
		Optional<User> optionalUser =  userService.getUser(userId);
		if (!optionalUser.isPresent()) {
			return new ResponseEntity<String>("User with id " + userId + " not found", HttpStatus.NOT_FOUND);
		}
		
		// if vehicle is not found
		Optional<Vehicle> optionalVehicle = vehicleService.getVehicle(vehicleId);
		if (!optionalVehicle.isPresent()) {
			return new ResponseEntity<String>("Vehicle with id " + vehicleId + " not found", HttpStatus.NOT_FOUND);
		}
		
		User user = optionalUser.get();
		Vehicle vehicle = optionalVehicle.get();
		
		// If the user does not own the vehicle
		if (!user.getVehicles().contains(vehicle)) {
			return new ResponseEntity<String>("Vehicle with id " + vehicleId + " does not belong to user with id " + userId, HttpStatus.BAD_REQUEST);
		}
		
		Registration registration = new Registration(Role.DRIVER, user, trip);
		user.addRegistration(registration);
		trip.addRegistration(registration);
		vehicle.addTrip(trip);
		
		Trip newTrip = tripService.addTrip(trip);
		
		return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
	}

	/**
	 * 
	 * Update a given trip
	 * 
	 * URL: http://hostname:port/api/trips/id
	 * HTTP method: PUT
	 */
	@RequestMapping(value="/trips/{tripId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTrip(@PathVariable long tripId, @RequestBody Trip trip) {
		Trip updatedTrip = tripService.updateTrip(tripId, trip);
		return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * Delete a given trip
	 * 
	 * URL: http://hostname:port/api/trips/id
	 * HTTP method: DELETE
	 */
	@RequestMapping(value="/trips/{tripId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTrip(@PathVariable long tripId) {
		Optional<Trip> trip = tripService.getTrip(tripId);
		tripService.deleteTrip(trip.get());
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/trips/{tripID}/tripNode/{tripNodeID}", method= RequestMethod.PUT)
	public ResponseEntity<?> addTripNode(@PathVariable long tripID, @PathVariable long tripNodeID){
		Optional<Trip> trip = tripService.getTrip(tripID);
		Trip trip1;
		if (trip.isPresent()) {
			trip1 = trip.get();
		} else {
			return new ResponseEntity<String>("Trip with id " + tripID + " not found", HttpStatus.NOT_FOUND);
		}
		Optional<TripNode> tripNode = tripNodeService.getTripNode(tripNodeID);
		//getTripNode(tripNodeID);
		TripNode tripNode1;
		if (tripNode.isPresent()) {
			tripNode1 = tripNode.get();
		} else {
			return new ResponseEntity<String>("Trip Node with id " + tripNodeID + " not found", HttpStatus.NOT_FOUND);
		}
		
		//trip1.addTripNode(tripNode1);
		tripService.addTripNode(trip1, tripNode1);
		
		return new ResponseEntity<>(trip1, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/trips/{tripID}/tripNodeToRemove/{tripNodeID}", method= RequestMethod.PUT)
	public ResponseEntity<?> removeTripNode(@PathVariable long tripID, @PathVariable long tripNodeID){
		Optional<Trip> trip = tripService.getTrip(tripID);
		Trip trip1;
		if (trip.isPresent()) {
			trip1 = trip.get();
		} else {
			return new ResponseEntity<String>("Trip with id " + tripID + " not found", HttpStatus.NOT_FOUND);
		}
		Optional<TripNode> tripNode = tripNodeService.getTripNode(tripNodeID);
		//getTripNode(tripNodeID);
		TripNode tripNode1;
		if (tripNode.isPresent()) {
			tripNode1 = tripNode.get();
		} else {
			return new ResponseEntity<String>("Trip Node with id " + tripNodeID + " not found", HttpStatus.NOT_FOUND);
		}
		
		//trip1.addTripNode(tripNode1);
		tripService.removeTripNode(trip1, tripNode1);
		
		return new ResponseEntity<>(trip1, HttpStatus.OK);
		
	}
	
	
	
}
