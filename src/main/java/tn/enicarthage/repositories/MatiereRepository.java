package tn.enicarthage.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Matiere;

@Repository
public interface MatiereRepository extends CrudRepository<Matiere, Long> {
		Matiere findByNom(String name);
		Matiere findById(int id);
}
