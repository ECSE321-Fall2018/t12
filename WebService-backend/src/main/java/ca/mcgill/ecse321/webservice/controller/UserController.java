package ca.mcgill.ecse321.webservice.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.service.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * Get all users
	 */
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public ResponseEntity<?> getUsers() {
		Iterable<User> userList = userService.getUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}


	/**
	 * Get specific user
	 * 
	 * @param userId
	 */
	@RequestMapping(value="/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable long userId){
		
		User user;
		try {
			user = userService.getUser(userId);
		} catch(NoSuchElementException | NullPointerException e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/name/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("username") String username){
		
		User user =  userService.getUser(username);
		if (user == null) {
			return new ResponseEntity<String>("Username does not exist", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
	/**
	 * Adds new user
	 * @param user
	 */
	@RequestMapping(value ="/users", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user){
		if(user != null) {
			User newUser = userService.addUser(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User cannot be null", HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	/**
	 * Update existing user
	 * @param userId
	 * @param user
	 */
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody User user){
		
		User updatedUser;
		try {
			updatedUser = userService.updateUser(userId, user);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	/**
	 * Delete specific user </br>
	 * <b>NOTE:</b> This method requires <b>ADMIN</b> authority to access
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable long userId){
		User user;
		try {
			user = userService.getUser(userId);
		} catch(NoSuchElementException e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		userService.deleteUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}