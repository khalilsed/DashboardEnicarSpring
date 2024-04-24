package tn.enicarthage.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.entities.Etudiant_etranger;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.Note;
import tn.enicarthage.services.IEtudiantEtrangerService;
import tn.enicarthage.services.IEtudiantLocalService;
import tn.enicarthage.services.INoteService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NoteRestController {
	   @Autowired 
	   INoteService iNoteService;
	   @Autowired 
	   IEtudiantLocalService iEtudiantLocalService;
	   @Autowired 
	   IEtudiantEtrangerService iEtudiantEtrangerService;
	   @PostMapping("/addNote/{etud}")
	   void ajouterNote(@RequestBody Note note,@PathVariable("etud") String username) {
		   iNoteService.ajouterNote(note);
		   Etudiant_local el=iEtudiantLocalService.getEtudiantLocalByUsername(username);
		   if(el!=null) {
		   el.getNotes().add(note);
		   iEtudiantLocalService.modifierEtudiantLocal(el);
		   }else {
			   Etudiant_etranger et=iEtudiantEtrangerService.getEtudiantEtrangerByUsername(username);
			   et.getNotes().add(note);
			   iEtudiantEtrangerService.modifierEtudiantEtranger(et);
		   }

	   }
	   @PutMapping("/updateNote")
	   public void modifierNote(@RequestBody Note Note) {
    	   iNoteService.modifierNote(Note);
       }
	   @DeleteMapping("removeNote/{Note-id}")
	   public void supprimerNoteById(@PathVariable("Note-id") int id) {
    	   iNoteService.supprimerNoteById(id);
       }

	   @GetMapping("/getAllNotes")
	   public List<Note> listNotes(){
		   List<Note> l = iNoteService.listNotes();
		   return l;
	   }


}