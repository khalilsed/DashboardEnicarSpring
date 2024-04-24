package tn.enicarthage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Specialite;
import tn.enicarthage.repositories.SpecialiteRepository;

@Service
public class SpecialiteService implements ISpecialiteService {
	   @Autowired
	   SpecialiteRepository SpecialiteRepository;
	   
	   @Override
	   public void ajouterSpecialite(Specialite s) {
  	   SpecialiteRepository.save(s);
     }
	   @Override
	   public void modifierSpecialite(Specialite s) {
  	   SpecialiteRepository.save(s);
     }
	   @Override
	   public void supprimerSpecialiteById(int id) {
  	   SpecialiteRepository.deleteById((long) id);
     }
	   
	   @Override
	   public List<Specialite> listSpecialites(){

			 List<Specialite> l = (List<Specialite>) SpecialiteRepository.findAll();
			 return l;
	   }
	   @Override
	   public Specialite getSpecialiteById(int id) {
		    return (SpecialiteRepository.findById(id));
	   }
}
