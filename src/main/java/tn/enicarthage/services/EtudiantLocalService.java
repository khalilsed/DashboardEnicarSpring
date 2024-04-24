package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.repositories.EtudiantLocalRepository;

@Service
public class EtudiantLocalService implements IEtudiantLocalService{
	   
	   @Autowired
	   EtudiantLocalRepository etudiantLocalRepository;
	   
	   @Override
	   public void ajouterEtudiantLocal(Etudiant_local e) {
		   etudiantLocalRepository.save(e);
       }
	   @Override
	   public void modifierEtudiantLocal(Etudiant_local e) {
		   etudiantLocalRepository.save(e);
       }
	   @Override
	   public void supprimerEtudiantLocalById(int id) {
		   etudiantLocalRepository.deleteById((long) id);
       }
	   
	   @Override
	   public List<Etudiant_local> listEtudiantLocal(){

			 List<Etudiant_local> l = (List<Etudiant_local>) etudiantLocalRepository.findAll();
			 return l;
	   }
	   
	   @Override
	   public Optional<Etudiant_local> getEtudiantLocalLogged(String username,String passwd){
		   	return etudiantLocalRepository.findByUsernameAndPassword(username, passwd);	 
	   }
	   @Override
	   public Etudiant_local getEtudiantLocalByUsername(String username){
		   	return etudiantLocalRepository.findByUsername(username);	 
	   }
	   
}
