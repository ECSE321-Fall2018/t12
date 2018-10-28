package ca.mcgill.ecse321.webservice.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.repository.VehicleRepository;

/**
 * Service for manipulating a Vehicle entity
 */
@Service
@Transactional
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	/**
	 * Returns all vehicles
	 */
	public Iterable<Vehicle> getVehicles(){
		return vehicleRepository.findAll();
	}
	
	/**
	 * Get a specific vehicle given an id. If the id given id does not exist,
	 * a NoSuchElementException will be thrown.
	 * @param id
	 * @return Vehicle
	 * @throws NoSuchElementException
	 */
	public Vehicle getVehicle(long id) throws NoSuchElementException {
		return vehicleRepository.findById(id).get();
	}
	
	/**
	 * Persists a user's vehicle in the DB. <br/><b>NOTE:</b> "Use the returned instance for further operations 
	 * as the save operation might have changed the entity instance completely."
	 * 
	 * @param user
	 * @param vehicle
	 * @return New vehicle object persisted in DB
	 */
	public Vehicle addVehicle(User user, Vehicle vehicle) {
		
		user.addVehicle(vehicle);
		
		return vehicleRepository.save(vehicle);
	}
	
	/**
	 * Updates a vehicle given
	 * @param id
	 * @param vehicle
	 * @return 
	 * @throws NoSuchElementException
	 */
	public Vehicle updateVehicle(long id, Vehicle vehicle) throws NoSuchElementException {
		Vehicle original = getVehicle(id);
		original.setModel(vehicle.getModel());
		original.setMake(vehicle.getMake());
		original.setColor(vehicle.getColor());
		return vehicleRepository.save(original);
	}
	
	/**
	 * Determines if this vehicle is associated with an active trip. </br>
	 * <b>See: </b> Trip.java
	 * 
	 * @param vehicle
	 * @return <code>true</code> if <code>vehicle</code> is associated with an active trip. </br>
	 * <code>false otherwise.</code>
	 */
	public boolean associatedWithActiveTrip(Vehicle vehicle) {
		for (Trip trip : vehicle.getTrips()) {
			if (trip.isActive()) return true;
		}
		return false;
	}
	
	/**
	 * Deletes a vehicle from the DB
	 * @param vehicle
	 */
	public void deleteVehicle(Vehicle vehicle) {
		
		// Remove all trip references
		for (Trip trip : vehicle.getTrips()) {
			trip.setVehicle(null);
		}	
		
		vehicleRepository.delete(vehicle);
	}
}
