package ca.mcgill.ecse321.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.webservice.model.TripNode;

public interface TripNodeRepository extends CrudRepository<TripNode, Long> {

}
