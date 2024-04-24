package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import tn.enicarthage.entities.Etudiant_local;

public interface IEtudiantLocalService {
	 public void ajouterEtudiantLocal(Etudiant_local u);
     public void supprimerEtudiantLocalById(int id);
     public void modifierEtudiantLocal(Etudiant_local u);
     public List<Etudiant_local> listEtudiantLocal();
     public Optional<Etudiant_local> getEtudiantLocalLogged(String username,String passwd);
     public Etudiant_local getEtudiantLocalByUsername(String username);
}
