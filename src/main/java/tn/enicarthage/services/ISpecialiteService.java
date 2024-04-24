package tn.enicarthage.services;

import java.util.List;

import tn.enicarthage.entities.Specialite;

public interface ISpecialiteService {
	  public void ajouterSpecialite(Specialite s);
      public void supprimerSpecialiteById(int id);
      public void modifierSpecialite(Specialite s);
      public List<Specialite> listSpecialites();
      public Specialite getSpecialiteById(int id);
}
