package tn.enicarthage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.enicarthage.entities.Absence;

import java.util.List;
@Repository
public interface AbsenceReposirtory extends CrudRepository<Absence, Long> {

}
