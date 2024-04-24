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

import tn.enicarthage.entities.Etudiant_etranger;
import tn.enicarthage.entities.Groupe;
import tn.enicarthage.services.IEtudiantEtrangerService;
import tn.enicarthage.services.IGroupeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EtudiantEtrangerRestController {
	   @Autowired 
	   IEtudiantEtrangerService iEtudiantEtrangerService;
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
	   
	   @PostMapping("/addEtudiantEtranger/{idGroupe}")
	   void ajouterEtudiantEtranger(@RequestBody Etudiant_etranger e,@PathVariable("idGroupe") long idGroupe) throws MessagingException {
		    e.setUsername(e.getNom()+" "+e.getPrenom()+" "+alphaNumericString(2));
		    e.setPassword(alphaNumericString(10));
		    Groupe g=iGroupeService.getGroupeById(idGroupe);
		    e.setGrp(g);
		    iEtudiantEtrangerService.ajouterEtudiantEtranger(e);

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
	   
	   @PutMapping("/updateEtudiantEtranger")
	   public void modifierEtudiantEtranger(@RequestBody Etudiant_etranger EtudiantEtranger) {
    	   iEtudiantEtrangerService.modifierEtudiantEtranger(EtudiantEtranger);
       }
	   
	   @DeleteMapping("/removeEtudiantEtranger/{EtudiantEtranger-id}")
	   public void supprimerEtudiantEtrangerById(@PathVariable("EtudiantEtranger-id") int id) {
    	   iEtudiantEtrangerService.supprimerEtudiantEtrangerById(id);
       }
	   
	   @GetMapping("/getAllEtudiantEtrangers")
	   public List<Etudiant_etranger> listEtudiantEtrangers(){
		   List<Etudiant_etranger> l = iEtudiantEtrangerService.listEtudiantEtranger();
		   return l;
	   }
	   
	   @GetMapping("/getEtudiantEtrangerLogged")
	   public Optional<Etudiant_etranger> getEtudiantEtrangerLogged(@RequestParam String username,@RequestParam String password){
		   return(iEtudiantEtrangerService.getEtudiantEtrangerLogged(username, password));
	   }
	   @GetMapping("/getEtudiantEtranger")
	   public Etudiant_etranger getEtudiantEtrangerByUsername(Etudiant_etranger e){
		   return(iEtudiantEtrangerService.getEtudiantEtrangerByUsername(e.getUsername()));
	   }
	
}