package tn.enicarthage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Absence;


@Repository
public interface AbsenceReposirtory extends JpaRepository<Absence, Long> {

    @Query("SELECT a FROM Absence a JOIN a.mat m WHERE m.id = :idMatiere")
    List<Absence> findAbsencesByMatiereId(Long idMatiere);
    }