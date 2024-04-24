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
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.entities.Enseignant;
import tn.enicarthage.entities.Etudiant_etranger;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.User;
import tn.enicarthage.services.IUserService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {
	   @Autowired 
	   IUserService iUserService;
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
       
       
	   @PostMapping("/addUser/{user-type}")
	   void ajouterUser(@RequestBody User user,@PathVariable("user-type") String userType) throws MessagingException {

		    user.setUsername(user.getNom()+" "+user.getPrenom()+" "+alphaNumericString(2));
		    user.setPassword(alphaNumericString(10));
		    if(userType.equals("Ensei")) {
		    	iUserService.ajouterUser(user); 	
		    	Enseignant en = (Enseignant) user;
		    	
		    	iUserService.ajouterUser(en);
		    }else if(userType.equals("Etud-local")) {
		    	Etudiant_local etl = (Etudiant_local) user;
		    	iUserService.ajouterUser(user);
		    	iUserService.ajouterUser(etl);
		    }else if(userType=="Etud-etranger") {
		    	Etudiant_etranger ete = (Etudiant_etranger) user;
		    	iUserService.ajouterUser(ete);
		    }
		   

	        MimeMessage message =mailSender.createMimeMessage();
	        MimeMessageHelper helper =new MimeMessageHelper(message,true);
	    
	        String mailSubject = "Bienvenue chez Ecole nationale d'ingénieur de carthage" ;
	        String  mailContent=  "<p><b>Votre nom d'utilisateur est :</b>"+user.getUsername()+"</p>";
	        mailContent += "<p><b>Votre mot de passe est :</b>"+user.getPassword()+"</p>";
	        mailContent += "<hr><img src:='cid:logo'/>";

	        helper.setTo(user.getMail());
	        helper.setSubject(mailSubject);
	        helper.setText(mailContent, true);

	        ClassPathResource path = new ClassPathResource("/static/logo.png");
	        helper.addInline("logo", path);
	        mailSender.send(message);

	   }
	   
	   
	   @PutMapping("/updateUser")
	   public void modifierUser(@RequestBody User user) {
    	   iUserService.modifierUser(user);
       }
	   
	   
	   @DeleteMapping("removeUser/{user-id}")
	   public void supprimerUserById(@PathVariable("user-id") int id) {
    	   iUserService.supprimerUserById(id);
       }
	   
	   
	   @GetMapping("/getAllUsers")
	   public List<User> listUsers(){
		   List<User> l = iUserService.listUsers();
		   return l;
	   }
	   
	   @GetMapping("/getUserLogged")
	   public Optional<User> getUserLogged(@RequestBody User user){
		   return(iUserService.getUserLogged(user.getUsername(), user.getPassword()));
	   }
	

}
