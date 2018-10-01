package ca.mcgill.ecse321.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.webservice.model.Trip;

public interface TripRepository extends CrudRepository<Trip, Long> {

}
