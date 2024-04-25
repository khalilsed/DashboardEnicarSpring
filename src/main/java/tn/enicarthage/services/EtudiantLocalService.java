package tn.enicarthage.services;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import tn.enicarthage.entities.Note;
import tn.enicarthage.entities.Absence;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.Groupe;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.repositories.EtudiantLocalRepository;
import tn.enicarthage.repositories.GroupeRepository;
import tn.enicarthage.repositories.MatiereRepository;

@Service
public class EtudiantLocalService implements IEtudiantLocalService{
	   
	   @Autowired
	   EtudiantLocalRepository etudiantLocalRepository;
	   @Autowired
	   MatiereRepository matiereRepository;
	   @Autowired
	   GroupeRepository groupeRepository;
	   @PersistenceContext
	    private EntityManager entityManager;
	   @Override
	   public void ajouterEtudiantLocal(Etudiant_local e) {
		   etudiantLocalRepository.save(e);
       }
	   @Override
	   public void modifierEtudiantLocal(Etudiant_local e) {
		   etudiantLocalRepository.save(e);
       }
	   @Override
	   public void supprimerEtudiantLocalById(int id) {
		   etudiantLocalRepository.deleteById((long) id);
       }
	   
	   @Override
	   public List<Etudiant_local> listEtudiantLocal(){

		   return  (List<Etudiant_local>) etudiantLocalRepository.findAll();
	   }
	   
	   @Override
	   public Optional<Etudiant_local> getEtudiantLocalLogged(String username,String passwd){
		   	return etudiantLocalRepository.findByUsernameAndPassword(username, passwd);	 
	   }
	   @Override
	   public Etudiant_local getEtudiantLocalByUsername(String username){
		   	return etudiantLocalRepository.findByUsername(username);	 
	   }
	   
	   public Matiere getMatiereByNom(String nomMatiere) {
		    return matiereRepository.findByNom(nomMatiere);
		}

	   
	   public void saveEtudiantsToDatabase(MultipartFile file, String nomMatiere,String grpId){
		    if(ExcelUploadService.isValidExcelFile(file)){
		        try {
		            List<Etudiant_local> etudiants = ExcelUploadService.getEtudiantsDataFromExcel(file.getInputStream(),nomMatiere,grpId);
		            Groupe groupe = groupeRepository.findById(Integer.parseInt(grpId));
		            for (Etudiant_local etudiant : etudiants) {
		                Set<Note> notes = etudiant.getNotes();
		                Matiere matiere = getMatiereByNom(nomMatiere);
		                etudiant.setGrp(groupe);
		                for (Note note : notes) {
		                    note.setMat(matiere);
		                    note.getEtudiants().add(etudiant);
		                }
		            }
		           
		            this.etudiantLocalRepository.saveAll(etudiants);
		        } catch (IOException e) {
		            throw new IllegalArgumentException("Le fichier n'est pas un excel");
		        }
		    }
		}
	@Override
	public long countEtudiantLocal() {
		 return etudiantLocalRepository.count();
	}
	
	 public List<Etudiant_local> getEtudiantsLocauxParMatiere(long idMatiere) {
		    Integer idMatiereInteger = (int) idMatiere; // Conversion de long en Integer
		    TypedQuery<Etudiant_local> query = entityManager.createQuery(
		        "SELECT e FROM Etudiant_local e JOIN e.grp g JOIN g.matieres m WHERE m.id = :idMatiere", Etudiant_local.class);
		    query.setParameter("idMatiere", idMatiereInteger);
		    return query.getResultList();
		}
	 
	 public void saveEtudiantsAbsencesToDatabase(MultipartFile file, String nomMatiere){
		    if(ExcelUploadService.isValidExcelFile(file)){
		        try {
		            List<Etudiant_local> etudiants = ExcelUploadAbsenceService.getEtudiantsDataFromExcel(file.getInputStream(),nomMatiere);
		            
		            // Boucle sur les étudiants pour associer les notes à chacun
		            for (Etudiant_local etudiant : etudiants) {
		                Set<Absence> absences = etudiant.getAbsences();
		                Matiere matiere = getMatiereByNom(nomMatiere);
		                for (Absence absence : absences) {
		                    // Associer chaque note à la matière
		                	absence.setMat(matiere);
		                    // Associer chaque note à l'étudiant actuel
		                	absence.getEtudiants().add(etudiant);
		                }
		            }
		            
		            // Enregistrer les étudiants avec leurs notes associées
		            this.etudiantLocalRepository.saveAll(etudiants);
		        } catch (IOException e) {
		            throw new IllegalArgumentException("Le fichier n'est pas un excel");
		        }
		    }
		}
}
