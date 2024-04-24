
package tn.enicarthage.repositories;



import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import tn.enicarthage.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	public Optional<User> findByUsernameAndPassword(String username,String password);
}
