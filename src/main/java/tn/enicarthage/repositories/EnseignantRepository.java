package tn.enicarthage.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Enseignant;
import tn.enicarthage.entities.Matiere;

@Repository
public interface EnseignantRepository extends CrudRepository<Enseignant,Long>{
	Enseignant findByNom(String name);
	public Optional<Enseignant> findByUsernameAndPassword(String Enseignantname,String password);
}