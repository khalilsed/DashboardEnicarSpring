package tn.enicarthage.controllers;

import java.util.List;

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

import tn.enicarthage.entities.Groupe;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Specialite;
import tn.enicarthage.services.IGroupeService;
import tn.enicarthage.services.IMatiereService;
import tn.enicarthage.services.ISpecialiteService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GroupeRestController {
	   @Autowired 
	   IGroupeService iGroupeService;
	   @Autowired 
	   ISpecialiteService iSpecialiteService;
	   @Autowired 
	   IMatiereService iMatiereService;
	   
	   @PostMapping("/addGroupe/{idSpec}")
	   void ajouterGroupe(@RequestBody Groupe groupe,@PathVariable("idSpec") int idSpec) {
		   Specialite s=iSpecialiteService.getSpecialiteById(idSpec);
		   groupe.setSpec(s);
		   iGroupeService.ajouterGroupe(groupe);
	   }
	   
	   @PostMapping("/addMatiereToGroupe/{idGrp}")
	   Groupe ajouterMatiereToGroupe(@PathVariable("idGrp") long idGrp, @RequestBody String nomMat) {
		   
	        Groupe groupe = iGroupeService.getGroupeById(idGrp);
	        	Matiere mat = iMatiereService.getMatiereByNom2(nomMat);

	        groupe.getMatieres().add(mat);

	        iGroupeService.ajouterGroupe(groupe);
	        return groupe;
	   }
	   @PutMapping("/updateGroupe")
	   public void modifierGroupe(@RequestBody Groupe groupe) {
    	   iGroupeService.modifierGroupe(groupe);
       }
	   @DeleteMapping("removeGroupe/{Groupe-id}")
	   public void supprimerGroupeById(@PathVariable("Groupe-id") int id) {
    	   iGroupeService.supprimerGroupeById(id);
       }
	   
	   @GetMapping("/getAllGroupes")
	   public List<Groupe> listGroupes(){
		   List<Groupe> l = iGroupeService.listGroupes();
		   return l;
	   }
	   @GetMapping("/getGroupe")
	   public Groupe getEnseignantById(Groupe g){
		   return(iGroupeService.getGroupeById(g.getId()));
	   }
	   
	   @GetMapping("/groupes/count")
	    public ResponseEntity<Long> countMatieres() {
	        long count = iGroupeService.countGroupes();
	        return new ResponseEntity<>(count, HttpStatus.OK);
	    }
	   

}