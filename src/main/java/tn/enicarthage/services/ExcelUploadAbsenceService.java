package tn.enicarthage.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.enicarthage.entities.Absence;
import tn.enicarthage.entities.Etudiant_local;
import tn.enicarthage.entities.Note;
import tn.enicarthage.entities.noteType;

@Service
public class ExcelUploadAbsenceService {
	  public static boolean isValidExcelFile(MultipartFile file){
	        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
	    }

	    public static List<Etudiant_local> getEtudiantsDataFromExcel(InputStream inputStream,String nomMatiere) {
	        List<Etudiant_local> etudiants = new ArrayList<>();
	        
	        try {
	            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	            XSSFSheet sheet = workbook.getSheet("absence"); // Assurez-vous que le nom de la feuille est correct
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
	                        	Absence absence = new Absence();
	                            absence.setDate((Date) cell.getDateCellValue());
	                            etudiant.getAbsences().add(absence);
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
	}
