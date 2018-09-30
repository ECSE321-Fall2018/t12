package ca.mcgill.ecse321.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@RequestMapping(value="/trips", method = RequestMethod.GET)
	public ResponseEntity<?> getTrips() {
		Iterable<Trip> tripList = tripService.getTrips();
		return new ResponseEntity<>(tripList, HttpStatus.OK);
	}
	
}
