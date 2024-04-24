package tn.enicarthage.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Etudiant_local;

@Repository
public interface EtudiantLocalRepository extends CrudRepository<Etudiant_local,Long>{
	public Optional<Etudiant_local> findByUsernameAndPassword(String  username,String password);
	public Etudiant_local findByUsername(String  username);
}
