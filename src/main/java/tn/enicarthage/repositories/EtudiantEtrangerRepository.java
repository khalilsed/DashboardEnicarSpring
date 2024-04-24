package tn.enicarthage.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Etudiant_etranger;

@Repository
public interface EtudiantEtrangerRepository extends CrudRepository<Etudiant_etranger,Long>{
	public Optional<Etudiant_etranger> findByUsernameAndPassword(String username,String password);
	public Etudiant_etranger findByUsername(String username);
}
