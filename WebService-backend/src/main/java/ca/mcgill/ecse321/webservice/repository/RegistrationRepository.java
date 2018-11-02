package ca.mcgill.ecse321.webservice.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.webservice.model.Registration;
import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.User;


public interface RegistrationRepository extends CrudRepository<Registration, Long> {
	Registration findByUser(User user);
	
	Registration findByTrip(Trip trip);
	
}
