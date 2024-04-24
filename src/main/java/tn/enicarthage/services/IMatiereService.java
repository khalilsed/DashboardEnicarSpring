package tn.enicarthage.services;

import java.util.List;

import tn.enicarthage.entities.Matiere;

public interface IMatiereService {
	  public void ajouterMatiere(Matiere m);
      public void supprimerMatiereById(int id);
      public void modifierMatiere(Matiere m);
      public List<Matiere> listMatieres();
      public Matiere getMatiereByNom(String m);
      public Matiere getMatiereById(int id);
}
