package ca.mcgill.ecse321.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.webservice.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
