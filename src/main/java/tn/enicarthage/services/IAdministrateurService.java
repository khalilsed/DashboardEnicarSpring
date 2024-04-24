package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import tn.enicarthage.entities.Administrateur;

public interface IAdministrateurService {
   public void ajouterAdministrateur(Administrateur e);
   public void supprimerAdministrateurById(int id);
   public void modifierAdministrateur(Administrateur e);
   public List<Administrateur> listAdministrateur();
   public Optional<Administrateur> getAdminLogged(String username,String passwd);
}

