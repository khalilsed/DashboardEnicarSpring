package tn.enicarthage.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.enicarthage.entities.Enseignant;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.Groupe;
import tn.enicarthage.services.IEtudiantLocalService;
import tn.enicarthage.services.IGroupeService;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class EtudiantLocalRestController {
	   @Autowired 
	   IEtudiantLocalService iEtudiantLocalService;
	   @Autowired 
	   IGroupeService iGroupeService;
	   @Autowired
       private JavaMailSender mailSender;
	   
	   
	   public static String alphaNumericString(int len) {
           String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
           Random rnd = new Random();

           StringBuilder sb = new StringBuilder(len);
           for (int i = 0; i < len; i++) {
               sb.append(AB.charAt(rnd.nextInt(AB.length())));
           }
           return sb.toString();
       }
	   
	   @PostMapping("/addEtudiantLocal/{idGroupe}")
	   void ajouterEtudiantLocal(@RequestBody Etudiant_local e,@PathVariable("idGroupe") int idGroupe) throws MessagingException {
		    
		   e.setUsername(e.getNom()+" "+e.getPrenom()+" "+alphaNumericString(2));
		    e.setPassword(alphaNumericString(10));
		    Groupe g=iGroupeService.getGroupeById(idGroupe);
		    e.setGrp(g);
		    iEtudiantLocalService.ajouterEtudiantLocal(e);

	        MimeMessage message =mailSender.createMimeMessage();
	        MimeMessageHelper helper =new MimeMessageHelper(message,true);
	    
	        String mailSubject = "Bienvenue chez Ecole nationale d'ingénieur de carthage" ;
	        String  mailContent=  "<p><b>Votre nom d'utilisateur est :</b>"+e.getUsername()+"</p>";
	        mailContent += "<p><b>Votre mot de passe est :</b>"+e.getPassword()+"</p>";
	        mailContent += "<hr><img src:='cid:logo'/>";

	        helper.setTo(e.getMail());
	        helper.setSubject(mailSubject);
	        helper.setText(mailContent, true);

	        ClassPathResource path = new ClassPathResource("/static/logo.png");
	        helper.addInline("logo", path);
	        mailSender.send(message);
	   }
	   
	 /*  @PostMapping("/upload-etudiant-data")
	    public ResponseEntity<?> uploadEtudiantsData(@RequestParam("file")MultipartFile file){
	        this.iEtudiantLocalService.saveEtudiantsToDatabase(file);
	        return ResponseEntity
	                .ok(Map.of("Message" , " Etudiants data uploaded avec succes"));
	    }*/
	   
	   @PutMapping("/updateEtudiantLocal")
	   public void modifierEtudiantLocal(@RequestBody Etudiant_local EtudiantLocal) {
    	   iEtudiantLocalService.modifierEtudiantLocal(EtudiantLocal);
       }
	   @DeleteMapping("removeEtudiantLocal/{EtudiantLocal-id}")
	   public void supprimerEtudiantLocalById(@PathVariable("EtudiantLocal-id") int id) {
    	   iEtudiantLocalService.supprimerEtudiantLocalById(id);
       }
	   
	   @GetMapping("/getAllEtudiantLocals2")
	   public List<Etudiant_local> listEtudiantLocals2(){
		   List<Etudiant_local> l =  iEtudiantLocalService.listEtudiantLocal();
		   
		   return l;
	   }
	   
	   
	   @Autowired
	    private JdbcTemplate jdbcTemplate;

	    @GetMapping("/getAllEtudiantLocals")
	    public List<Map<String, Object>> listEtudiantLocals() {
	        String sql = "SELECT e.*, g.*,s.* FROM T_ETUD_LOCAL e JOIN T_GROUPE g ON e.GRP_GROUPE_ID = g.GROUPE_ID JOIN T_SPECIALITE s ON g.SPEC_SPEC_ID= s.SPEC_ID";
	        return jdbcTemplate.queryForList(sql);
	    }
	    
	   @GetMapping("/getEtudiantLocalLogged")
	   public Optional<Etudiant_local> getEtudianLocalLogged(@RequestParam String username,@RequestParam String password){
		   return(iEtudiantLocalService.getEtudiantLocalLogged(username, password));
	   }
	    @GetMapping("/getEtudiantLocalLog")
	    public List<Map<String, Object>> listEtudiantLocalsLogged(@RequestParam String username,@RequestParam String password) {
	        String sql = "SELECT user_id,user_mail,user_nom,user_pre,user_name,grp_groupe_id FROM T_ETUD_LOCAL where user_name='"+username+"' AND user_password='"+password+"'";
	        return jdbcTemplate.queryForList(sql);
	    }
	   @GetMapping("/getEtudiantLocal")
	   public Etudiant_local getEtudiantLocalByUsername(Etudiant_local e){
		   return(iEtudiantLocalService.getEtudiantLocalByUsername(e.getUsername()));
	   }
	   
	   @GetMapping("/getEtudiantLocalByName")
	   public Etudiant_local getEtudiantLocalByUsername(@RequestParam String e){
		   return(iEtudiantLocalService.getEtudiantLocalByUsername(e));
	   }
	   
	   @PostMapping("/upload-etudiant-data")
	   @CrossOrigin(origins = "http://localhost:4200")
	    public ResponseEntity<?> uploadEtudiantsData(@RequestPart(value="file", required = true)  MultipartFile file,@RequestPart(value="nomMatiere", required = true) String nomMatiere,@RequestPart(value="grpId", required = true) String grpId){
	        this.iEtudiantLocalService.saveEtudiantsToDatabase(file,nomMatiere,grpId);
	        return ResponseEntity
	                .ok(" Etudiants data uploaded avec succes");
	    }
	   
	   @GetMapping("/etudiantslocales/count")
	    public ResponseEntity<Long> countMatieres() {
	        long count = iEtudiantLocalService.countEtudiantLocal();
	        return new ResponseEntity<>(count, HttpStatus.OK);
	    }
	   
	   @GetMapping("/getAllEtudiantLocalsWithNote/{idMat}")
	    public List<Map<String, Object>> listEtudiantLocalsWNotes(@PathVariable("idMat") int id) {
	        String sql = "SELECT e.*,en.*,n.* FROM t_etud_local e join t_etud_local_notes en ON e.user_id=en.etudiants_user_id join t_note n on en.notes_note_id=n.note_id where mat_matiere_id="+id;
	        return jdbcTemplate.queryForList(sql);
	    }
	   
	   @GetMapping("/getEtudiantLocalsWithNote/{idEtd}")
	    public List<Map<String, Object>> EtudiantLocalsWNotes(@PathVariable("idEtd") int id) {
	        String sql = "SELECT DISTINCT m.matiere_nom,m.matiere_coef,n.* FROM t_etud_local e join t_etud_local_notes en ON e.user_id=en.etudiants_user_id join t_note n on en.notes_note_id=n.note_id join t_matiere m on n.mat_matiere_id=m.matiere_id where e.user_id="+id;
	        return jdbcTemplate.queryForList(sql);
	    }
	   
	   @PostMapping("/upload-etudiant-data-absence")
	   @CrossOrigin(origins = "http://localhost:4200")
	    public ResponseEntity<?> uploadEtudiantsDataAbsence(@RequestPart(value="file", required = true)  MultipartFile file,@RequestPart(value="nomMatiere", required = true) String nomMatiere){
	        this.iEtudiantLocalService.saveEtudiantsAbsencesToDatabase(file,nomMatiere);
	        return ResponseEntity.ok(" Etudiants data uploaded avec succes");
	    }

	
}