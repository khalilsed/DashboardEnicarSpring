package tn.enicarthage.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.entities.Enseignant;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.services.IMatiereService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MatiereRestController {
	   @Autowired 
	   IMatiereService iMatiereService;
	   @PostMapping("/addMatiere")
	   void ajouterMatiere(@RequestBody Matiere Matiere) {
		   iMatiereService.ajouterMatiere(Matiere);
	   }
	   @PutMapping("/updateMatiere")
	   public void modifierMatiere(@RequestBody Matiere Matiere) {
    	   iMatiereService.modifierMatiere(Matiere);
       }
	   @DeleteMapping("removeMatiere/{Matiere-id}")
	   public void supprimerMatiereById(@PathVariable("Matiere-id") int id) {
    	   iMatiereService.supprimerMatiereById(id);
       }
	   
	   @GetMapping("/getAllMatieres")
	   public List<Matiere> listMatieres(){
		   List<Matiere> l = iMatiereService.listMatieres();
		   return l;
	   }
	   @GetMapping("/getMatiere")
	   public Matiere getMatiere(Matiere m){
		   Matiere mat = iMatiereService.getMatiereByNom(m);
		   return mat;
	   }
	   
	   @GetMapping("/getMatiereByName/{nomMat}")
	   public Matiere getMatiere(@PathVariable("nomMat") String m){
		   Matiere mat = iMatiereService.getMatiereByNom2(m);
		   return mat;
	   }
	   
	   @GetMapping("/getEnseignantsByMatiere/{id_mat}")
	   public Set<Enseignant> getEnseignantsByMatiere(@PathVariable("id_mat") int idMat) {
	       Matiere matiere = iMatiereService.getMatiereById(idMat);
	       if (matiere != null) {
	           return matiere.getEnseignants();
	       } else {
	           return new HashSet<>();
	       }
	   }

	@GetMapping("/getEnseignantByMatiere/{id-mat}")
	public Set<Enseignant> getEnseignantByMatiere(@PathVariable("id-mat") int idMat){
		Matiere mat = iMatiereService.getMatiereById(idMat);
		if(mat != null) {
			return mat.getEnseignants();
		}else {
			return new HashSet<>();
		}

	}
	   
	   @GetMapping("/matieres/count")
	    public ResponseEntity<Long> countMatieres() {
	        long count = iMatiereService.countMatieres();
	        return new ResponseEntity<>(count, HttpStatus.OK);
	    }
	

}