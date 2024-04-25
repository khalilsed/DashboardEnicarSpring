package tn.enicarthage.services;


import java.util.List;
import java.util.Optional;

import tn.enicarthage.entities.Etudiant_etranger;


public interface IEtudiantEtrangerService {
       public void ajouterEtudiantEtranger(Etudiant_etranger u);
       public void supprimerEtudiantEtrangerById(int id);
       public void modifierEtudiantEtranger(Etudiant_etranger u);
       public List<Etudiant_etranger> listEtudiantEtranger();
       public Optional<Etudiant_etranger> getEtudiantEtrangerLogged(String username,String passwd);
       public Etudiant_etranger getEtudiantEtrangerByUsername(String username);
       public long countEtudiantEtranger();
}
