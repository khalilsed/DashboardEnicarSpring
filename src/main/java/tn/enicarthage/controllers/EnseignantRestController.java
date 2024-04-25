package tn.enicarthage.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.entities.Enseignant;
import tn.enicarthage.services.IEnseignantService;
import tn.enicarthage.services.IMatiereService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EnseignantRestController {
	   @Autowired 
	   IEnseignantService iEnseignantService;
	   @Autowired
		IMatiereService iMatiereService;
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

	@PostMapping("/addEnseignant/{idG}")
	void ajouterEnseignant(@RequestBody Enseignant e,@PathVariable("idG") int idG) throws MessagingException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		String result = alphaNumericString(10);
		e.setUsername(e.getNom() + " " + e.getPrenom() + " " + alphaNumericString(2));
		e.setPassword(result);
		 e.setPassword(encoder.encode(result));
		iEnseignantService.ajouterEns(e,idG);

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		String mailSubject = "Bienvenue chez Ecole nationale d'ingénieur de carthage";
		String mailContent = "<p><b>Votre nom d'utilisateur est :</b>" + e.getUsername() + "</p>";
		mailContent += "<p><b>Votre mot de passe est :</b>" + result + "</p>";
		mailContent += "<hr><img src:='cid:logo'/>";

		helper.setTo(e.getMail());
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);

		ClassPathResource path = new ClassPathResource("/static/logo.png");
		helper.addInline("logo", path);
		mailSender.send(message);

	}
	   @PutMapping("/updateEnseignant")
	   public void modifierEnseignant(@RequestBody Enseignant Enseignant) {
    	   iEnseignantService.modifierEnseignant(Enseignant);
       }
	   @DeleteMapping("removeEnseignant/{Enseignant-id}")
	   public void supprimerEnseignantById(@PathVariable("Enseignant-id") long id) {
    	   iEnseignantService.supprimerEnseignantById(id);
       }
	   
	   @GetMapping("/getAllEnseignants")
	   public List<Enseignant> listEnseignants(){
		   List<Enseignant> l = iEnseignantService.listEnseignant();
		   return l;
	   }
	   @GetMapping("/getEnseignantLogged")
	   public Optional<Enseignant> getEnseignantLogged(@RequestParam String username,@RequestParam String password){
		   
		   return(iEnseignantService.getEnseignantLogged(username, password));
	   }
	   @GetMapping("/getEnseignant")
	   public Enseignant getEnseignantById(Enseignant e){
		   return(iEnseignantService.getEnseignantById(e.getId()));
	   }
	   
	   @GetMapping("/ensignants/count")
	    public ResponseEntity<Long> countMatieres() {
	        long count = iEnseignantService.countEnsignants();
	        return new ResponseEntity<>(count, HttpStatus.OK);
	    }
	@PostMapping("/addMatiereToEnseignant/{idMat}")
	Enseignant ajouterMatiereToEnseignant(@RequestBody String enseignant, @PathVariable("idMat") int idMat) {

		Enseignant ens = iEnseignantService.getEnseignantByNom(enseignant);

		ens.setMat(iMatiereService.getMatiereById(idMat));

		iEnseignantService.ajouterEnseignant(ens);
		return ens;
	}

}