package tn.enicarthage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Specialite;



@Repository
public interface SpecialiteRepository extends CrudRepository<Specialite, Long> {
		public Specialite findById(int id);
}
