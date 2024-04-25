package tn.enicarthage.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Enseignant;

@Repository
public interface EnseignantRepository extends CrudRepository<Enseignant,Long>{
	public Optional<Enseignant> findByUsername(String Enseignantname);
	Enseignant findByNom(String name);
	public Optional<Enseignant> findByUsernameAndPassword(String Enseignantname,String password);
}