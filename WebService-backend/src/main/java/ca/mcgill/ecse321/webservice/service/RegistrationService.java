package ca.mcgill.ecse321.webservice.service;

import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.webservice.model.Registration;
import ca.mcgill.ecse321.webservice.model.Role;
import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.repository.RegistrationRepository;

@Service
@Transactional
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;

	
	public Iterable<Registration> getAllRegistrations() {
		return registrationRepository.findAll();	
		
	}
	
	public Optional<Registration> getRegistrationByID(long id) {
		return registrationRepository.findById(id);
	}
	
	public Registration addRegistration(User user, Trip trip, Role role) {
		Registration newRegistration = new Registration(role, user, trip);
		registrationRepository.save(newRegistration);
		
		trip.decrementAvailableSeats();
		
		user.addRegistration(newRegistration);
		trip.addRegistration(newRegistration);
		return newRegistration;	
	}
	public Registration deleteRegistration (long registrationID) {
		Optional<Registration> registrationO = registrationRepository.findById(registrationID);
		Registration registration = registrationO.get();
		registrationRepository.delete(registration);
		return registration; 
		
	}
	
	public Registration updateRegistration (long registrationID, Registration registration) {
		return registrationRepository.save(registration);
	}
	
	

}
