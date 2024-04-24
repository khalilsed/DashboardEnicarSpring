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

import tn.enicarthage.entities.Administrateur;
import tn.enicarthage.services.IAdministrateurService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdministrateurRestController {
	   @Autowired 
	   IAdministrateurService iAdministrateurService;
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
       
	   @PostMapping("/addAdministrateur")
	   void ajouterAdministrateur(@RequestBody Administrateur e) throws MessagingException {

		    e.setUsername(e.getNom()+" "+e.getPrenom()+" "+alphaNumericString(2));
		    e.setPassword(alphaNumericString(10));
		    iAdministrateurService.ajouterAdministrateur(e);

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
	   @PutMapping("/updateAdministrateur")
	   public void modifierAdministrateur(@RequestBody Administrateur Administrateur) {
    	   iAdministrateurService.modifierAdministrateur(Administrateur);
       }
	   @DeleteMapping("removeAdministrateur/{Administrateur-id}")
	   public void supprimerAdministrateurById(@PathVariable("Administrateur-id") int id) {
    	   iAdministrateurService.supprimerAdministrateurById(id);
       }
	   
	   @GetMapping("/getAllAdministrateurs")
	   public List<Administrateur> listAdministrateurs(){
		   List<Administrateur> l = iAdministrateurService.listAdministrateur();
		   return l;
	   }
	   @GetMapping("/getAdminLogged")
	   public Optional<Administrateur> getAdminLogged(@RequestParam String username,@RequestParam String password){
		   System.out.println(username+password);
		   return(iAdministrateurService.getAdminLogged(username, password));
	   }
}