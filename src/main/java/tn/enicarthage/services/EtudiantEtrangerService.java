package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Etudiant_etranger;
import tn.enicarthage.repositories.EtudiantEtrangerRepository;


@Service
public class EtudiantEtrangerService implements IEtudiantEtrangerService{
	   
	   @Autowired
	   EtudiantEtrangerRepository etudiantEtrangerRepository;
	   
	   @Override
	   public void ajouterEtudiantEtranger(Etudiant_etranger e) {
		   etudiantEtrangerRepository.save(e);
       }
	   @Override
	   public void modifierEtudiantEtranger(Etudiant_etranger e) {
		   etudiantEtrangerRepository.save(e);
       }
	   @Override
	   public void supprimerEtudiantEtrangerById(int id) {
		   etudiantEtrangerRepository.deleteById((long) id);
       }
	   
	   @Override
	   public List<Etudiant_etranger> listEtudiantEtranger(){

			 List<Etudiant_etranger> l = (List<Etudiant_etranger>) etudiantEtrangerRepository.findAll();
			 return l;
	   }
	   
	   @Override
	   public Optional<Etudiant_etranger> getEtudiantEtrangerLogged(String username,String passwd){
		   	return etudiantEtrangerRepository.findByUsernameAndPassword(username, passwd);	 
	   }
	   @Override
	   public Etudiant_etranger getEtudiantEtrangerByUsername(String username){
		   	return etudiantEtrangerRepository.findByUsername(username);	 
	   }
	   @Override
	   public long countEtudiantEtranger() {
		   return etudiantEtrangerRepository.count();
	   }
	   
}