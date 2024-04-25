package tn.enicarthage.services;

import java.util.List;
import java.util.Optional;

import tn.enicarthage.entities.Enseignant;

public interface IEnseignantService {
	public void ajouterEnseignant(Enseignant e);
    public void supprimerEnseignantById(long id);
    public Enseignant getEnseignantById(long id);
    public Enseignant getEnseignantByNom(String enseignantNom);
    public void modifierEnseignant(Enseignant e);
    public List<Enseignant> listEnseignant();
    public long countEnsignants();
    public Optional<Enseignant> getEnseignantLogged(String username,String passwd);
}
