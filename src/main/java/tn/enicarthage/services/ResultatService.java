package tn.enicarthage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.enicarthage.entities.Resultat;
import tn.enicarthage.repositories.ResultatRepository;

@Service
public class ResultatService implements IResultatService{
	   @Autowired
	   ResultatRepository ResultatRepository;
	   
	   @Override
	   public void ajouterResultat(Resultat r) {
  	   ResultatRepository.save(r);
     }
	   @Override
	   public void modifierResultat(Resultat r) {
  	   ResultatRepository.save(r);
     }
	   @Override
	   public void supprimerResultatById(int id) {
  	   ResultatRepository.deleteById((long) id);
     }
	   
	   @Override
	   public List<Resultat> listResultats(){

			 List<Resultat> l = (List<Resultat>) ResultatRepository.findAll();
			 return l;
	   }
}
