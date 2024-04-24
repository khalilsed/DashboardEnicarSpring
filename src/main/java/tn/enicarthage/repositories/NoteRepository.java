package tn.enicarthage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.enicarthage.entities.Note;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

}
