package ca.mcgill.ecse321.webservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.service.TripService;

@RestController
@RequestMapping("/api/")
public class TripController {

	@Autowired
	private TripService tripService;
	
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
		Iterable<Trip> tripList = tripService.getTrips();
		return new ResponseEntity<>(tripList, HttpStatus.OK);
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
     * Creates a new trip
     * 
     * URL: http://hostname:port/api/trips/
     * HTTP method: POST
     * 
     */	
	@RequestMapping(value="/trips", method = RequestMethod.POST)
	public ResponseEntity<?> addTrip(@RequestBody Trip trip) {
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
	
	
	
	
}
