package tn.enicarthage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Groupe;
import tn.enicarthage.repositories.GroupeRepository;

@Service
public class GroupeService implements IGroupeService {
	 @Autowired
	   GroupeRepository GroupeRepository;
	   
	   @Override
	   public void ajouterGroupe(Groupe u) {
  	   GroupeRepository.save(u);
     }
	   @Override
	   public void modifierGroupe(Groupe u) {
  	   GroupeRepository.save(u);
     }
	   @Override
	   public void supprimerGroupeById(int id) {
  	   GroupeRepository.deleteById((long) id);
     }
	   
	   @Override
	   public List<Groupe> listGroupes(){

			 List<Groupe> l = (List<Groupe>) GroupeRepository.findAll();
			 return l;
	   }
	   @Override
	   public Groupe getGroupeById(long id) {
		   return GroupeRepository.findById(id);
       }
	   @Override
	   public long countGroupes() {
	        return GroupeRepository.count();
	    }
}
