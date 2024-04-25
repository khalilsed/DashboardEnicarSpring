package tn.enicarthage.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.entities.Absence;
import tn.enicarthage.entities.Etudiant_etranger;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.Note;
import tn.enicarthage.services.IAbsenceService;
import tn.enicarthage.services.IEtudiantEtrangerService;
import tn.enicarthage.services.IEtudiantLocalService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AbsenceRestController {
	@Autowired
	IAbsenceService iAbsenceService;
	@Autowired 
	   IEtudiantLocalService iEtudiantLocalService;
	   @Autowired 
	   IEtudiantEtrangerService iEtudiantEtrangerService;
	   
	   @PostMapping("/ajouterabsenceetud/{idEtudiant}/{idMatiere}")
	    public ResponseEntity<String> ajouterAbsence(@PathVariable Long idEtudiant, @PathVariable int idMatiere) {
		   iAbsenceService.ajouterAbsenceEtudiant(idEtudiant, idMatiere);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Absence ajoutée avec succès");
	    }
	   @GetMapping("/nombre-absences-par-etudiant")
	    public ResponseEntity<Map<String, Integer>> getNombreAbsencesParEtudiant() {
	        Map<String, Integer> nombreAbsencesParEtudiant = iAbsenceService.getNombreAbsencesParEtudiant();
	        return ResponseEntity.ok().body(nombreAbsencesParEtudiant);
	    }
	   /*
	   @GetMapping("/nombre-absences-par-etudiant-par-matiere")
	    public List<Object[]> getNombreAbsencesParEtudiantParMatiere() {
	        return iAbsenceService.getNombreAbsencesParEtudiantParMatiere();
	    }*/
	   
	    @GetMapping("/nombre-absences-par-etudiant-par-matiere/{idMatiere}")
	    public List<Object[]> getNombreAbsencesParEtudiantParMatiere(@PathVariable Long idMatiere) {
	        return iAbsenceService.getNombreAbsencesParEtudiantParMatiere(idMatiere);
	    }
	    

	    @GetMapping("/nombre-absences/{idMatiere}/{idGroupe}")
	    public List<Object[]> getNombreAbsencesParEtudiantParMatiereAndGroupe(
	            @PathVariable Long idMatiere,
	            @PathVariable Long idGroupe
	    ) {
	        return iAbsenceService.getNombreAbsencesParEtudiantParMatiereAndGroupe(idMatiere, idGroupe);
	    }

	@GetMapping("/nombre-absences/{idMatiere}")
	public List<Object[]> getNombreAbsencesParEtudiantMatiere(
			@PathVariable Long idMatiere
	) {
		return iAbsenceService.getNombreAbsencesParEtudiantMatiere(idMatiere);
	}
}
