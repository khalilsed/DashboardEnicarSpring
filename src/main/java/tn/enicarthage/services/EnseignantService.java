package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Enseignant;
import tn.enicarthage.repositories.EnseignantRepository;

@Service
public class EnseignantService implements IEnseignantService{
	   
	   @Autowired
	   EnseignantRepository enseignantRepository;
	   
	   @Override
	   public void ajouterEnseignant(Enseignant e) {
		   enseignantRepository.save(e);
       }
	   @Override
	   public Enseignant getEnseignantById(long id) {
		   return enseignantRepository.findById(id).get();
	   }
	   @Override
	   public Enseignant getEnseignantByNom(String enseignantNom) {
		return enseignantRepository.findByNom(enseignantNom);
	}
	   @Override
	   public void modifierEnseignant(Enseignant e) {
		   enseignantRepository.save(e);
       }
	   @Override
	   public void supprimerEnseignantById(long id) {
		   enseignantRepository.deleteById(id);
       }
	   
	   @Override
	   public List<Enseignant> listEnseignant(){

			 List<Enseignant> l = (List<Enseignant>) enseignantRepository.findAll();
			 return l;
	   }
	   
	   @Override
	   public Optional<Enseignant> getEnseignantLogged(String username,String passwd){
		   	return enseignantRepository.findByUsernameAndPassword(username, passwd);	 
	   }
	   
}