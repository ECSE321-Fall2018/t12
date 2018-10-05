package ca.mcgill.ecse321.webservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.webservice.model.Registration;
import ca.mcgill.ecse321.webservice.service.RegistrationService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(value="/registrationsAll", method = RequestMethod.GET)
	public ResponseEntity<?> getAllRegistrations() {
		Iterable<Registration> registrationList = registrationService.getAllRegistrations();
		return new ResponseEntity<>(registrationList, HttpStatus.OK);
	}
	
	// can do the following method by importing user repository or service and then geting the user and then getting its registrations
	/*
	@RequestMapping(value="/registrations/{customerID}", method = RequestMethod.GET)   
	public ResponseEntity<?> getRegistrations(@PathVariable long customerID) {
		Iterable<Registration> registrationList = registrationService.getAllRegistrations();
		return new ResponseEntity<>(registrationList, HttpStatus.OK);
	}
	*/
	@RequestMapping(value="/addRegistration/{userID}/{tripID}", method = RequestMethod.POST)
	public ResponseEntity<?> addRegistration(@PathVariable long userID,@PathVariable long tripID) {
		Registration newRegistration= registrationService.addRegistration(userID, tripID);
		return new ResponseEntity<>(newRegistration, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/delete/{registrationID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRegistration(@PathVariable long registrationID) {
		Registration newRegistration= registrationService.deleteRegistration(registrationID);
				
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/registration/{registraionID}", method = RequestMethod.GET)
	public ResponseEntity<?> getRegistrationByID(@PathVariable long registrationID) {
		Optional <Registration> registration= registrationService.getRegistration(registrationID);
		Registration r = registration.get();
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	
	
}

