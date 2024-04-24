package tn.enicarthage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Groupe;

@Repository
public interface GroupeRepository extends CrudRepository<Groupe, Long> {
		Groupe findById(long id);
}
