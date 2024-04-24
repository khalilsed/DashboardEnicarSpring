package tn.enicarthage.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.bind.annotation.RestController;

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
	   
	   @PutMapping("/updateEtudiantLocal")
	   public void modifierEtudiantLocal(@RequestBody Etudiant_local EtudiantLocal) {
    	   iEtudiantLocalService.modifierEtudiantLocal(EtudiantLocal);
       }
	   @DeleteMapping("removeEtudiantLocal/{EtudiantLocal-id}")
	   public void supprimerEtudiantLocalById(@PathVariable("EtudiantLocal-id") int id) {
    	   iEtudiantLocalService.supprimerEtudiantLocalById(id);
       }
	   
	   @GetMapping("/getAllEtudiantLocals")
	   public List<Etudiant_local> listEtudiantLocals(){
		   List<Etudiant_local> l = iEtudiantLocalService.listEtudiantLocal();
		   return l;
	   }
	   @GetMapping("/getEtudiantLocalLogged")
	   public Optional<Etudiant_local> getEtudianLocalLogged(@RequestParam String username,@RequestParam String password){
		   return(iEtudiantLocalService.getEtudiantLocalLogged(username, password));
	   }
	   @GetMapping("/getEtudiantLocal")
	   public Etudiant_local getEtudiantLocalByUsername(Etudiant_local e){
		   return(iEtudiantLocalService.getEtudiantLocalByUsername(e.getUsername()));
	   }
	   
	
}