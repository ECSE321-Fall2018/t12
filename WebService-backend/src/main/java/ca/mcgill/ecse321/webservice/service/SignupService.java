package ca.mcgill.ecse321.webservice.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.UserRole;
import ca.mcgill.ecse321.webservice.repository.UserRepository;

@Service
@Transactional
public class SignupService {
	
	@Autowired
	private UserRepository userRepository;
 
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User addUser(User user) {
		
		// if the username is taken, return null
		if (isUsernameTaken(user.getUsername())) {
			return null;
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	private boolean isUsernameTaken(String username) {
		return userRepository.findByUsername(username) != null;
	}
	
	/**
	 * 
	 * set up a default user with two roles USER and ADMIN
	 * 
	 */
	@PostConstruct
	private void setupDefaultUser() {
		//-- just to make sure there is an ADMIN user exist in the database for testing purpose
		if (userRepository.count() == 0) {
			
			User usr = new User("Bob", 
								"admin", 
								passwordEncoder.encode("password"),
								5, 5);
			
			usr.addRole(new UserRole("USER"));
			usr.addRole(new UserRole("ADMIN"));
			
			userRepository.save(usr);
		}		
	}
}
