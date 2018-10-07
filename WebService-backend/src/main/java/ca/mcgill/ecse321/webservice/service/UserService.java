package ca.mcgill.ecse321.webservice.service;

import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> getUsers(){
		if(userRepository.count() ==0){
			User user = new User();
			user.setName("Test case");
			user.setUsername("UsernameTest");
			user.setPassword("PasswordTest");
			user.setDrivingRate(3);
			user.setPassRate(3);


			userRepository.save(user);
		}
		return userRepository.findAll(); 
	}
	
	public Optional<User> getUser(long userId) {
		return userRepository.findById(userId);
	}

	public User updateUser( long userId,User user){
		return userRepository.save(user);
	}

	public User addUser(User user){
		return userRepository.save(user);
	}
	public void deleteUser(User user){
		userRepository.delete(user);
	}


	
}
