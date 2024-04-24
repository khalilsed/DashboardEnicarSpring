package tn.enicarthage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Resultat;

@Repository
public interface ResultatRepository extends CrudRepository<Resultat, Long> {

}
