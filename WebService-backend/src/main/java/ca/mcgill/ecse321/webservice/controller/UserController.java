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

import ca.mcgill.ecse321.webservice.service.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public ResponseEntity<?> getUsers() {
		Iterable<User> userList = userService.getUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@RequestMapping(value="/users/{userID}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable long userID){
		Optional<User> user = userService.getUser(userID);
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
	public ResponseEntity<?> deleteUser(@PathVariable long userId){
		Optional<User> user = userService.getUser(userId);
		userService.deleteUser(user.get());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
