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
public class TripNodeController {
	public static final Logger logger = LoggerFactory.getLogger(TripNodeController.class);

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
	 * Returns all tripNodes
	 * 
	 * URL: http://hostname:port/api/tripNodes
	 * HTTP method: GET
	 * 
	 */
	@RequestMapping(value="/tripNodes", method = RequestMethod.GET)
	public ResponseEntity<?> getTripNodes() {
		//logger.info("GET * TRIPS");
		Iterable<TripNode> tripNodeList = tripNodeService.getTripNodes();
		return new ResponseEntity<>(tripNodeList, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * Returns the tripNodes corresponding to the id
	 * 
	 * URL: http://hostname:port/api/tripNodes/{tripNodeID}
	 * HTTP method: GET
	 * 
	 */
	@RequestMapping(value="/tripNodes/{tripNodeID}", method = RequestMethod.GET)
	public ResponseEntity<?> getTripNodeByID(@PathVariable long tripNodeID) {
		//logger.info("GET * TRIPS");
		Optional<TripNode> tripNode = tripNodeService.getTripNode(tripNodeID);
		TripNode tripNode1;
		if (tripNode.isPresent()) {
			tripNode1 = tripNode.get();
		} else {
			return new ResponseEntity<String>("Trip Node with id " + tripNodeID + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tripNode, HttpStatus.OK);
	}
	
	/**
	 * 
	 * deletes the trip node and return the other trip nodes
	 * 
	 * URL: http://hostname:port/api/tripNodes/{tripNodeID}
	 * HTTP method: DELETE
	 * 
	 */
	@RequestMapping(value="/tripNodes/{tripNodeID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTripNodeByID(@PathVariable long tripNodeID) {
		//logger.info("GET * TRIPS");
		Optional<TripNode> tripNode = tripNodeService.getTripNode(tripNodeID);
		TripNode tripNode1;
		if (tripNode.isPresent()) {
			tripNode1 = tripNode.get();
		} else {
			return new ResponseEntity<String>("Trip Node with id " + tripNodeID + " not found", HttpStatus.NOT_FOUND);
		}
		Iterable<TripNode> tripNodeList=tripNodeService.deleteTripNode(tripNode1);
		return new ResponseEntity<>(tripNodeList, HttpStatus.OK);
	}
	
	//Post 
		@RequestMapping(value ="/addTripNode/{tripID}", method = RequestMethod.POST)
		public ResponseEntity<?> addTripNode(@PathVariable long tripID, @RequestBody TripNode tripNode){
			Optional<Trip> trip = tripService.getTrip(tripID);
			Trip trip1;
			if (trip.isPresent()) {
				trip1 = trip.get();
			} else {
				return new ResponseEntity<String>("Trip with id " + tripID + " not found", HttpStatus.NOT_FOUND);
			}
			//tripNode.setTrip(trip1);
			trip1.addTripNode(tripNode);
			TripNode newTripNode = tripNodeService.addTripNode(tripNode);
			return new ResponseEntity<>(newTripNode, HttpStatus.CREATED);
		}
	
	
	
	

}
