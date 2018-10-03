package ca.mcgill.ecse321.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.service.USerService;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public ResponseEntity<?> getUsers() {
		Iterable<user> userList = userService.getUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@RequestMapping(value="/user/{userID}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable long userID){
		user = userService.getUser();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	//Post 
	@RequestMapping(value ="/users", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user){
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	//Put
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable long userId, @RequestBody User user){
		User updatedUser = userService.updateUser(userId, user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	//Delete
		
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCustomer(@PathVariable long userId){
		User user = userService.getUser(userId);
		userService.deleteCustomer(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/", mehtod= RequestMethod.GET)
	public ResponseEntity<?> home(){
		return new ResponseEntity<>("CRM REST API, JPA, Spring Security, and OAuth2", HttpStatus.OK);
	}
}
