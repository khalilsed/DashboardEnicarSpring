package tn.enicarthage.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Matiere;
import tn.enicarthage.repositories.MatiereRepository;

@Service
public class MatiereService implements IMatiereService{
	 @Autowired
	   MatiereRepository MatiereRepository;
	   
	   @Override
	   public void ajouterMatiere(Matiere u) {
  	   MatiereRepository.save(u);
     }
	   @Override
	   public void modifierMatiere(Matiere u) {
  	   MatiereRepository.save(u);
     }
	   @Override
	   public void supprimerMatiereById(int id) {
//  	   MatiereRepository.deleteById((int) id);
     }
	   @Override
	   public Matiere getMatiereByNom(Matiere m) {
  	   return MatiereRepository.findByNom(m.getNom());
     }
	   
	   @Override
	   public Matiere getMatiereByNom2(String m) {
  	   return MatiereRepository.findByNom(m);
     }
	   @Override
	   public Matiere getMatiereById(int id) {
  	   return MatiereRepository.findById(id);
     }
	   
	   
	  
	   
	   @Override
	   public List<Matiere> listMatieres(){

			 List<Matiere> l = (List<Matiere>) MatiereRepository.findAll();
			 return l;
	   }
	   
	   @Override
	   public long countMatieres() {
	        return MatiereRepository.count();
	    }
}
