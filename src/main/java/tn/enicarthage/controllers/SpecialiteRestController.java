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

import tn.enicarthage.entities.Specialite;
import tn.enicarthage.services.ISpecialiteService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SpecialiteRestController {
	   @Autowired 
	   ISpecialiteService iSpecialiteService;
	   @PostMapping("/addSpecialite")
	   void ajouterSpecialite(@RequestBody Specialite Specialite) {
		   iSpecialiteService.ajouterSpecialite(Specialite);
	   }
	   @PutMapping("/updateSpecialite")
	   public void modifierSpecialite(@RequestBody Specialite Specialite) {
    	   iSpecialiteService.modifierSpecialite(Specialite);
       }
	   @DeleteMapping("removeSpecialite/{Specialite-id}")
	   public void supprimerSpecialiteById(@PathVariable("Specialite-id") int id) {
    	   iSpecialiteService.supprimerSpecialiteById(id);
       }
	   
	   @GetMapping("/getAllSpecialites")
	   public List<Specialite> listSpecialites(){
		   List<Specialite> l = iSpecialiteService.listSpecialites();
		   return l;
	   }
	

}