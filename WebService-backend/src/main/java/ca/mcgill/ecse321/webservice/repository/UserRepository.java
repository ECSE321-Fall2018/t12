package ca.mcgill.ecse321.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.webservice.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
