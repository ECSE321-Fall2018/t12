package ca.mcgill.ecse321.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.repository.UserRepository;

/**
 * Based off UserDetailsService interface
 * Tells Spring Security where and how users and roles are persisted
 *
 */
@Service
public class UsrDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if(user == null){
            throw new UsernameNotFoundException("UserName "+userName+" not found");
        }
        return new UsrDetails(user);
    }	
 
}

