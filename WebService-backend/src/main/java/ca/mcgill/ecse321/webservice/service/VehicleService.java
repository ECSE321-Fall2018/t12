package ca.mcgill.ecse321.webservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.repository.VehicleRepository;

@Service
@Transactional
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	public Iterable<Vehicle> getVehicles(){
		return vehicleRepository.findAll();
	}
	
	public Optional<Vehicle> getVehicle(long vehicleId){
		return vehicleRepository.findById(vehicleId);
	}
	
	public Vehicle addVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	public Vehicle updateVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	public boolean associatedWithActiveTrip(Vehicle vehicle) {
		for (Trip trip : vehicle.getTrips()) {
			if (trip.isActive()) return true;
		}
		return false;
	}
	
	public void deleteVehicle(Vehicle vehicle) {
		
		// Remove all trip references
		for (Trip trip : vehicle.getTrips()) {
			trip.setVehicle(null);
		}	
		
		vehicleRepository.delete(vehicle);
	}
}
