package tn.enicarthage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Note;
import tn.enicarthage.repositories.NoteRepository;

@Service
public class NoteService implements INoteService {
	 @Autowired
	   NoteRepository NoteRepository;
	   
	   @Override
	   public void ajouterNote(Note n) {
  	   NoteRepository.save(n);
     }
	   @Override
	   public void modifierNote(Note n) {
  	   NoteRepository.save(n);
     }
	   @Override
	   public void supprimerNoteById(int id) {
  	   NoteRepository.deleteById((long) id);
     }
	   
	   @Override
	   public List<Note> listNotes(){

			 List<Note> l = (List<Note>) NoteRepository.findAll();
			 return l;
	   }
}
