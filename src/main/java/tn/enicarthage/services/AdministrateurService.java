package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Administrateur;
import tn.enicarthage.repositories.AdministrateurRepository;

@Service
public class AdministrateurService implements IAdministrateurService{
	   
	   @Autowired
	   AdministrateurRepository administrateurRepository;
	   
	   
	   @Override
	   public void ajouterAdministrateur(Administrateur e) {
		   
		   administrateurRepository.save(e);
		   
       }
	   @Override
	   public void modifierAdministrateur(Administrateur e) {
		   administrateurRepository.save(e);
       }
	   @Override
	   public void supprimerAdministrateurById(int id) {
		   
		   administrateurRepository.deleteById((long) id);
		   
       }
	   
	   @Override
	   public List<Administrateur> listAdministrateur(){
		   
			 List<Administrateur> l = (List<Administrateur>) administrateurRepository.findAll();
			 
			 return l;
			 
	   }
	   
	   @Override
	   public Optional<Administrateur> getAdminLogged(String username,String password){
		   System.out.println(username+password);
		   	return administrateurRepository.findByUsernameAndPassword(username, password);	 
	   }
	   
}