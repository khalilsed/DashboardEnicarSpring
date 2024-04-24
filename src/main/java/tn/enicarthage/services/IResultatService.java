package tn.enicarthage.services;

import java.util.List;

import tn.enicarthage.entities.Resultat;

public interface IResultatService {
	  public void ajouterResultat(Resultat r);
      public void supprimerResultatById(int id);
      public void modifierResultat(Resultat r);
      public List<Resultat> listResultats();
}
