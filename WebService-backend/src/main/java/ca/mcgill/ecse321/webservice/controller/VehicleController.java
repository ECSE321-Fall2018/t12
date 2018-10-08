package ca.mcgill.ecse321.webservice.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.service.UserService;
import ca.mcgill.ecse321.webservice.service.VehicleService;

@RestController
@RequestMapping("/api/")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * Returns all vehicles
	 * 
	 * URL: http://hostname:port/api/vehicles
	 * HTTP method: GET
	 * 
	 */
	@RequestMapping(value="/vehicles", method = RequestMethod.GET)
	public ResponseEntity<?> getVehicles(){
		Iterable<Vehicle> vehicleList = vehicleService.getVehicles();
		return new ResponseEntity<>(vehicleList, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Returns all vehicles associated with a specific user
	 * 
	 * URL: http://hostname:port/api/userId/vehicles
	 * HTTP method: GET
	 * 
	 */
	@RequestMapping(value="/users/{userId}/vehicles", method = RequestMethod.GET)
	public ResponseEntity<?> getVehiclesByUserId(@PathVariable long userId){
		
		Iterable<Vehicle> vehicleList;
		try {
			User user = userService.getUser(userId).get();
			vehicleList = user.getVehicles();
			
		} catch(NoSuchElementException e) {
			return new ResponseEntity<String>("User with id " + userId + " not found", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(vehicleList, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Returns a specific vehicle
	 * 
	 * URL: http://hostname:port/api/vehicles/vId
	 * HTTP method: GET
	 * 
	 */
	@RequestMapping(value="/vehicles/{v_id}", method=RequestMethod.GET)
	public ResponseEntity<?> getVehicleById(@PathVariable long v_id){
		
		Vehicle vehicle;
		try {
			vehicle = vehicleService.getVehicle(v_id).get();
		} catch(NoSuchElementException e) {
			return new ResponseEntity<String>("Vehicle with id " + v_id + " not found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(vehicle, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Adds a vehicle to a specific user
	 * 
	 * URL: http://hostname:port/api/userId/vehicles
	 * HTTP method: POST
	 * 
	 */
	@RequestMapping(value="/users/{user_id}/vehicles", method = RequestMethod.POST)
	public ResponseEntity<?> addVehicle(@PathVariable long user_id, @RequestBody Vehicle vehicle){		
		
		try {	
			User user = userService.getUser(user_id).get();
			user.addVehicle(vehicle);
			userService.updateUser(user.getId(), user);
			
		} catch(NoSuchElementException e) {
			return new ResponseEntity<String>("User with id " + user_id + " not found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	/**
	 * 
	 * Updates a vehicle
	 * 
	 * URL: http://hostname:port/api/vehicles/vId
	 * HTTP method: PUT
	 * 
	 */
	@RequestMapping(value="vehicles/{v_id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateVehicle(@PathVariable long v_id, @RequestBody Vehicle vehicle){
		
		try {
			vehicleService.getVehicle(v_id).get();
		} catch(NoSuchElementException e) {
			return new ResponseEntity<String>("Vehicle with id " + v_id + " not found", HttpStatus.NOT_FOUND);
		}
		
		Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
		return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Deletes a vehicle
	 * 
	 * URL: http://hostname:port/api/vehicles
	 * HTTP method: DELETE
	 * 
	 */
	@RequestMapping(value="/users/{u_id}/vehicles/{v_id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteVehicle(@PathVariable long u_id, @PathVariable long v_id){
		
		Vehicle vehicle;
		try {
			vehicle = vehicleService.getVehicle(v_id).get();
		} catch(NoSuchElementException e) {
			return new ResponseEntity<String>("Vehicle with id " + v_id + " not found", HttpStatus.NOT_FOUND);
		}
		
		User user;
		try {
			user = userService.getUser(u_id).get();
		} catch(NoSuchElementException e) {
			return new ResponseEntity<String>("User with id " + u_id + " not found", HttpStatus.NOT_FOUND);
		}
		
		user.removeVehicle(vehicle);
		vehicleService.deleteVehicle(vehicle);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
