package tn.enicarthage.services;

import java.util.List;

import tn.enicarthage.entities.Groupe;

public interface IGroupeService {
	  public void ajouterGroupe(Groupe u);
      public void supprimerGroupeById(int id);
      public void modifierGroupe(Groupe u);
      public List<Groupe> listGroupes();
      public Groupe getGroupeById(long id);
      public long countGroupes();

}
