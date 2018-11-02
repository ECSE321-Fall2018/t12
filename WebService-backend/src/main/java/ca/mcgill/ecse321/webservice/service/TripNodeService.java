package ca.mcgill.ecse321.webservice.service;

import java.sql.Time;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.webservice.model.Registration;
import ca.mcgill.ecse321.webservice.model.Role;
import ca.mcgill.ecse321.webservice.model.Trip;
import ca.mcgill.ecse321.webservice.model.TripNode;
import ca.mcgill.ecse321.webservice.model.User;
import ca.mcgill.ecse321.webservice.model.Vehicle;
import ca.mcgill.ecse321.webservice.repository.RegistrationRepository;
import ca.mcgill.ecse321.webservice.repository.TripNodeRepository;

@Service
@Transactional
public class TripNodeService {

	@Autowired
	private TripNodeRepository tripNodeRepository;

	
	public TripNode addTripNode(TripNode tripNode) {
		return tripNodeRepository.save(tripNode);
	}
	
	
	public Optional<TripNode> getTripNode(long id) {
		return tripNodeRepository.findById(id);
	}
	
	public Iterable<TripNode> deleteTripNode(TripNode tripNode) {
		tripNodeRepository.delete(tripNode);
		return tripNodeRepository.findAll();
	}
	
	public Iterable<TripNode> getTripNodes() {
		return tripNodeRepository.findAll();
	}



}