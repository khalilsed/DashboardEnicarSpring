package tn.enicarthage.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.enicarthage.entities.Note;

public interface INoteService {
	  public void ajouterNote(Note n);
      public void supprimerNoteById(int id);
      public void modifierNote(Note n);
      public List<Note> listNotes();
}
