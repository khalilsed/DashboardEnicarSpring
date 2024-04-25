package tn.enicarthage.services;

import java.util.List;
import java.util.Map;

import tn.enicarthage.entities.Absence;


public interface IAbsenceService {
	  public void ajouterAbsence(Absence n);
      public void supprimerAbsenceById(int id);
      public void modifierAbsence(Absence n);
      public List<Absence> listAbsences();
      public void ajouterAbsence(Long idEtudiant);
      public void ajouterAbsenceEtudiant(Long idEtudiant, int idMatiere);
      public Map<String, Integer> getNombreAbsencesParEtudiant();
      //public List<Object[]> getNombreAbsencesParEtudiantParMatiere();
      public List<Object[]> getNombreAbsencesParEtudiantParMatiere(Long idMatiere);
      public List<Object[]> getNombreAbsencesParEtudiantParMatiereAndGroupe(Long idMatiere, Long idGroupe);
      public List<Object[]> getNombreAbsencesParEtudiantMatiere(Long idMatiere);
      
}