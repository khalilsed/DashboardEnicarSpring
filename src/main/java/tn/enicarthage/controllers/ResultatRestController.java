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

import tn.enicarthage.entities.Resultat;
import tn.enicarthage.services.IResultatService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ResultatRestController {
	   @Autowired 
	   IResultatService iResultatService;
	   @PostMapping("/addResultat")
	   void ajouterResultat(@RequestBody Resultat Resultat) {
		   iResultatService.ajouterResultat(Resultat);
	   }
	   @PutMapping("/updateResultat")
	   public void modifierResultat(@RequestBody Resultat Resultat) {
    	   iResultatService.modifierResultat(Resultat);
       }
	   @DeleteMapping("removeResultat/{Resultat-id}")
	   public void supprimerResultatById(@PathVariable("Resultat-id") int id) {
    	   iResultatService.supprimerResultatById(id);
       }
	   
	   @GetMapping("/get-all-Resultats")
	   public List<Resultat> listResultats(){
		   List<Resultat> l = iResultatService.listResultats();
		   return l;
	   }
	

}