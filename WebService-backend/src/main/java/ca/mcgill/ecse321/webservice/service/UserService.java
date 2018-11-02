package ca.mcgill.ecse321.webservice.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.webservice.model.Registration;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.repository.UserRepository;

/**
 * Service for interacting with a User entity
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Returns all users
	 * @return Iterable<User>
	 */
	public Iterable<User> getUsers(){
		return userRepository.findAll(); 
	}
	
	/**
	 * Get a specific user given an id. 
	 * @param userId
	 * @return
	 * @throws NoSuchElementException
	 */
	public User getUser(long id) throws NoSuchElementException {
		
		User user = userRepository.findById(id).orElseThrow(
				() -> new NoSuchElementException("No user with id {" + id + "} was found."));
		
		return user;
	}
	
	/**
	 * Get a specific user given a username
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Updates a user given an id to get the User stored in the DB
	 * and the user with new information to update the stored entity.
	 * @param id
	 * @param user
	 * @return
	 * @throws NoSuchElementException
	 */
	public User updateUser(long id, User user) throws NoSuchElementException {
		User original = getUser(id);
		original.setName(user.getName());
		original.setUsername(user.getUsername());
		original.setDweight(user.getDweight());
		original.setPweight(user.getPweight());
		original.setPassword(user.getPassword());
		original.setDrivingRate(user.getDrivingRate());
		return userRepository.save(original);
	}
	
	public Set<Registration> getRegistrations(long userId) {
		User user = getUser(userId);
		return user.getRegistrations();
	}

	/**
	 * Returns true if the User param <code>user</code> has <code>trip</code> contained within its entity
	 * 
	 * @param user
	 * @param vehicle
	 * @return True if <code>user</code> has <code>vehicle</code>
	 */
	public boolean hasVehicle(User user, Vehicle vehicle)
	{
		return user.getVehicles().contains(vehicle);
	}
	
	/**
	 * Persits a user in the DB. <br/><b>NOTE:</b> "Use the returned instance for further operations 
	 * as the save operation might have changed the entity instance completely."
	 * 
	 * @param user
	 * @return
	 */
	public User addUser(User user){
		return userRepository.save(user);
	}
	
	/**
	 * Deletes a user from the DB
	 * @param user
	 */
	public void deleteUser(User user){
		userRepository.delete(user);
	}

	
}
