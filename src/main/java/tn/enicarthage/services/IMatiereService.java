package tn.enicarthage.services;

import java.util.List;

import tn.enicarthage.entities.Matiere;

public interface IMatiereService {
	  public void ajouterMatiere(Matiere m);
      public void supprimerMatiereById(int id);
      public void modifierMatiere(Matiere m);
      public List<Matiere> listMatieres();
      public Matiere getMatiereByNom(Matiere m);
      public Matiere getMatiereByNom2(String nomMat);
      public Matiere getMatiereById(int id);
      public long countMatieres();
}
