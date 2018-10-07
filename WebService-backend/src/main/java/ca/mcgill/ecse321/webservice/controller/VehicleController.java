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
		Optional<User> user = userService.getUser(userId);
		Iterable<Vehicle> vehicleList = user.get().getVehicles();
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
		Optional<Vehicle> vehicle = vehicleService.getVehicle(v_id);
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
		Optional<User> user = userService.getUser(user_id);
		user.get().getVehicles().add(vehicle);
		vehicle.setUser(user.get());
		userService.updateUser(user.get().getId(), user.get());
		Vehicle newVehicle = vehicleService.addVehicle(vehicle);
		return new ResponseEntity<>(newVehicle, HttpStatus.OK);
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
	@RequestMapping(value="vehicles/{v_id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteVehicle(@PathVariable long v_id){
		Optional<Vehicle> vehicle = vehicleService.getVehicle(v_id);
		vehicleService.deleteVehicle(vehicle.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
