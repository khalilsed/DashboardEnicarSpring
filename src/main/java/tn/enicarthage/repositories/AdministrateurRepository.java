package tn.enicarthage.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Administrateur;

@Repository
public interface AdministrateurRepository extends CrudRepository<Administrateur,Long>{
	public Optional<Administrateur> findByUsernameAndPassword(String name,String password);
}