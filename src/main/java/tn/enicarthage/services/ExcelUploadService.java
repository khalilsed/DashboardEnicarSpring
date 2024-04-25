package tn.enicarthage.services;




import org.apache.poi.ss.usermodel.Cell;



import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import tn.enicarthage.repositories.MatiereRepository;
import tn.enicarthage.entities.Enseignant;
import tn.enicarthage.entities.Etudiant;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.Matiere;
import tn.enicarthage.entities.Note;
import tn.enicarthage.entities.noteType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.enicarthage.repositories.MatiereRepository;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.Note;
import tn.enicarthage.entities.noteType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service public class ExcelUploadService {

	private static JavaMailSender mailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        mailSender = javaMailSender;
    }

    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }

    public static List<Etudiant_local> getEtudiantsDataFromExcel(InputStream inputStream,String nomMatiere,String grpId) {
        
    	List<Etudiant_local> etudiants = new ArrayList<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("notes"); // Assurez-vous que le nom de la feuille est correct
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Etudiant_local etudiant = new Etudiant_local();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            etudiant.setCin((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            etudiant.setNom(cell.getStringCellValue());
                            break;
                        case 2:
                            etudiant.setPrenom(cell.getStringCellValue());
                            break;
                        case 3:
                            Note note = new Note();
                            note.setType(noteType.tp);
                            note.setVnote((float) cell.getNumericCellValue());
                            etudiant.getNotes().add(note);
                            break;
                        case 4:
                            Note note2 = new Note();
                            note2.setType(noteType.cc);
                            note2.setVnote((float) cell.getNumericCellValue());
                            etudiant.getNotes().add(note2);
                            
                            break;
                        case 5:
                            Note note3 = new Note();
                            note3.setType(noteType.examen);
                            note3.setVnote((float) cell.getNumericCellValue());
                            
                            
                            etudiant.getNotes().add(note3);
                            String pwd = alphaNumericString(10);
                            String usern = etudiant.getNom()+" "+etudiant.getPrenom()+" "+alphaNumericString(2);
                            etudiant.setMail("alikhila80@gmail.com");
                            etudiant.setUsername(usern);
                            etudiant.setPassword(pwd);
                            
                            try {
                                MimeMessage message = mailSender.createMimeMessage();
                                MimeMessageHelper helper = new MimeMessageHelper(message, true); // Utilisez le constructeur avec le drapeau 'multipart' défini sur true
                                String mailSubject = "Bienvenue chez Ecole nationale d'ingénieur de carthage";
                                String mailContent = "<p><b>Votre nom d'utilisateur est :</b>" + usern + "</p>";
                                mailContent += "<p><b>Votre mot de passe est :</b>" + pwd + "</p>";
                                helper.setTo("alikhila80@gmail.com");
                                helper.setSubject(mailSubject);
                                helper.setText(mailContent, true);
                                ClassPathResource path = new ClassPathResource("/static/logo.png");
                                helper.addInline("logo", path);
                                mailSender.send(message);
                            } catch (MessagingException e) {
                                System.err.println("Failed to send email to: ");
                                e.printStackTrace();
                            }
                	        
                            
                            
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                etudiants.add(etudiant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return etudiants;
    }
    
    
	  
	   public static String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
    
}
