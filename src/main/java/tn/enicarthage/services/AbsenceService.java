package tn.enicarthage.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.entities.Absence;
import tn.enicarthage.entities.Etudiant_etranger;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.repositories.AbsenceReposirtory;
import tn.enicarthage.repositories.EtudiantEtrangerRepository;
import tn.enicarthage.repositories.EtudiantLocalRepository;
import tn.enicarthage.repositories.MatiereRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AbsenceService implements IAbsenceService {
    @Autowired
    AbsenceReposirtory AbsenceReposirtory;
    @Autowired
    MatiereRepository MatiereRepository;
    @Autowired
    EtudiantLocalRepository etudiantLocalRepository;
    @Autowired
    EtudiantEtrangerRepository etudiantEtrangerRepository;




    @Override
    public void ajouterAbsence(Absence n) {
        AbsenceReposirtory.save(n);
    }

    @Override
    public void supprimerAbsenceById(Long id) {
        AbsenceReposirtory.deleteById(id);
    }

    @Override
    public void modifierAbsence(Absence n) {
        AbsenceReposirtory.save(n);
    }

    @Override
    public List<Absence> listAbsences() {
        return (List<Absence>) AbsenceReposirtory.findAll();
    }

    @Override
    public void ajouterAbsence(Long idEtudiant) {

    }

    @Override
    public void ajouterAbsenceEtudiant(Long idEtudiant, int idMatiere) {
        Optional<Etudiant_local> etudiantLocalOpt = etudiantLocalRepository.findById(idEtudiant);

        if (etudiantLocalOpt.isPresent()) {
            Etudiant_local etudiantLocal = etudiantLocalOpt.get();

            Matiere matiere = MatiereRepository.findById(idMatiere);

            Absence absence = new Absence();
            absence.setDate(new Date());
            absence.setMat(matiere);

            etudiantLocal.getAbsences().add(absence);

            AbsenceReposirtory.save(absence);
        } else {
            Etudiant_etranger etudiantEtranger = etudiantEtrangerRepository.findById(idEtudiant)
                    .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

            Matiere matiere = MatiereRepository.findById(idMatiere);

            Absence absence = new Absence();
            absence.setDate(new Date());
            absence.setMat(matiere);
            AbsenceReposirtory.save(absence);
        }
    }

    @Override
    public Map<String, Integer> getNombreAbsencesParEtudiant() {
        return null;
    }

    @Override
    public List<Object[]> getNombreAbsencesParEtudiantParMatiere() {
        return null;
    }

    @Override
    public List<Object[]> getNombreAbsencesParEtudiantParMatiere(Long idMatiere) {
        return null;
    }

    @Override
    public List<Object[]> getNombreAbsencesParEtudiantParMatiereAndGroupe(Long idMatiere, Long idGroupe) {
        return null;
    }
}
