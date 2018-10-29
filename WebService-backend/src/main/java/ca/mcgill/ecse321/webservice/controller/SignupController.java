package ca.mcgill.ecse321.webservice.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.UserRole;
import ca.mcgill.ecse321.webservice.service.SignupService;

@RestController
public class SignupController {

	@Autowired
	private SignupService signupService;
 
    /**
     * user signup
     * @param user
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@RequestBody User user) {
    	
    	// Add USER role
   		user.addRole(new UserRole("USER"));
   		
   		// Attempt to add the user
    	User newUser = signupService.addUser(user);
    	
    	// If the new user is using a existing username
    	if (newUser == null)
    	{
    		return new ResponseEntity<String>("Username: " + user.getUsername() + " is already in use.", HttpStatus.OK);
    	}
    	
    	return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
	
}
