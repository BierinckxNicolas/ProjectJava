package com.crescendo.sql.informat.controllers;

import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.app.services.PdfMaker;
import com.crescendo.sql.informat.DTO.StudentHistoryDTO;
import com.crescendo.sql.informat.repositories.StudenthistoryRepositoryImpl;
import com.crescendo.sql.utils.CustomErrorType;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
/** Restcontroller studenthistory
 * @author Groep 5
 */
@RestController
@RequestMapping("/api")
@SuppressWarnings("Duplicates")
public class StudentHistoryController {

    @Autowired
    private StudenthistoryRepositoryImpl studenthistoryRepository;

    @Autowired
    private InformatService informatService;
    @Autowired
    private PdfMaker pdfMaker;

    /**
     * Api endpoitn om de filters in de frontend te tonen, via de meegegevens zal deze methode filters op de volledige join van de verschillende tabellen<br>
     * Hierbij worden de filters van de StudentHistoryRepositoryImpl gebruikt
     * @param name naam student
     * @param courseName cursusnaam
     * @param adminGroup admingroep
     * @param studyArea studiegebied
     * @param lector leerkracht
     * @param moduleName modulenaam
     * @param location locatie
     * @param cursusCode cursuscode
     * @param semester semester
     * @param username gebruikersnaam
     * @return De gefiltere historie voor de gevraagde paramaters
     * @throws IOException Wordt gethrowed als de configfile niet gelezen kan worden
     * @throws DocumentException Wordt gethrowed als de xml mailing file uit pfdmaker niet geladen kan worden
     */
    @GetMapping(path = "/studenthistory/{name}/{courseName}/{adminGroup}/{studyArea}/{lector}/{moduleName}/{location}/{cursusCode}/{semester}/{username}")
    public ResponseEntity<List<StudentHistoryDTO>> getHistories(@PathVariable("name") String name, @PathVariable("courseName") String courseName, @PathVariable("adminGroup") String adminGroup, @PathVariable("studyArea") String studyArea, @PathVariable("lector") String lector, @PathVariable("moduleName") String moduleName, @PathVariable("location") String location, @PathVariable("cursusCode") String cursusCode, @PathVariable("semester") String semester, @PathVariable("username") String username) throws IOException, DocumentException {

        List<StudentHistoryDTO> histories = studenthistoryRepository.findAllPuntenLijst(informatService);

        if(!name.equals("null")){
            histories = studenthistoryRepository.filteredName(histories, name);
        }

        if(!username.equals("null")){
            histories = studenthistoryRepository.filteredUsername(histories, username);
        }

        if(!adminGroup.equals("null")){
            histories = studenthistoryRepository.filteredAdminGroup(histories, adminGroup);
        }

        if(!courseName.equals("null")){
            histories = studenthistoryRepository.filteredCourse(histories, courseName);
        }

        if(!lector.equals("null")){
            histories = studenthistoryRepository.filteredLector(histories, lector);
        }

        if(!location.equals("null")){
            histories = studenthistoryRepository.filteredLocation(histories, location);
        }

        if(!moduleName.equals("null")){
            histories = studenthistoryRepository.filteredModuleName(histories, moduleName);
        }

        if(!semester.equals("null")){
            histories = studenthistoryRepository.filteredSemester(histories, semester);
        }

        if(!cursusCode.equals("null")){
            histories = studenthistoryRepository.filteredThirdPartyRef(histories, cursusCode);
        }

        if(!studyArea.equals("null")){
            histories = studenthistoryRepository.filteredStudyArea(histories, studyArea);
        }

        if (histories == null){
            return new ResponseEntity(new CustomErrorType("unable to retrieve history for student " + name + ". Not Found"), HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<List<StudentHistoryDTO>>(histories,HttpStatus.OK);
    }

    /**
     * Api endpoitn om de filters toe te passen op de join table en deze dan te gebruiken om de pfs aan te maken en de mails te verzenden naar de juiste cursisten
     * @param name naam student
     * @param courseName cursusnaam
     * @param adminGroup admingroep
     * @param studyArea studiegebied
     * @param lector leerkracht
     * @param moduleName modulenaam
     * @param location locatie
     * @param cursusCode cursuscode
     * @param semester semester
     * @param username gebruikersnaam
     * @return Returned de opgevraagde history
     * @throws IOException Wordt gethrowed als de configfile niet gelezen kan worden
     * @throws DocumentException Wordt gethrowed als de xml mailing file uit pfdmaker niet geladen kan worden
     */
    @GetMapping(path = "/puntenlijst/{name}/{courseName}/{adminGroup}/{studyArea}/{lector}/{moduleName}/{location}/{cursusCode}/{semester}/{username}")
    public ResponseEntity<List<StudentHistoryDTO>> getPuntenlijst(@PathVariable("name") String name, @PathVariable("courseName") String courseName,@PathVariable("adminGroup") String adminGroup,@PathVariable("studyArea") String studyArea,@PathVariable("lector") String lector,@PathVariable("moduleName") String moduleName,@PathVariable("location") String location, @PathVariable("cursusCode") String cursusCode,@PathVariable("semester") String semester, @PathVariable("username") String username) throws IOException, DocumentException {
        File file = new File("files/geenEmailPuntenlijst.txt");
        if (file.exists())
        {
            //delete if exists
            file.delete();
        }

        List<StudentHistoryDTO> histories = studenthistoryRepository.findAllGrades(informatService);
        if(!name.equals("null")){
            histories = studenthistoryRepository.filteredName(histories, name);
        }

        if(!username.equals("null")){
            histories = studenthistoryRepository.filteredUsername(histories, username);
        }

        if(!adminGroup.equals("null")){
            histories = studenthistoryRepository.filteredAdminGroup(histories, adminGroup);
        }

        if(!courseName.equals("null")){
            histories = studenthistoryRepository.filteredCourse(histories, courseName);
        }

        if(!lector.equals("null")){
            histories = studenthistoryRepository.filteredLector(histories, lector);
        }

        if(!location.equals("null")){
            histories = studenthistoryRepository.filteredLocation(histories, location);
        }

        if(!moduleName.equals("null")){
            histories = studenthistoryRepository.filteredModuleName(histories, moduleName);
        }

        if(!semester.equals("null")){
            histories = studenthistoryRepository.filteredSemester(histories, semester);
        }

        if(!cursusCode.equals("null")){
            histories = studenthistoryRepository.filteredThirdPartyRef(histories, cursusCode);
        }

        if(!studyArea.equals("null")){
            histories = studenthistoryRepository.filteredStudyArea(histories, studyArea);
        }

        if (histories == null){
            return new ResponseEntity(new CustomErrorType("unable to retrieve history for student " + name + ". Not Found"), HttpStatus.NOT_FOUND);
        }
        try {
            pdfMaker.createpdf(histories);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<List<StudentHistoryDTO>>(histories,HttpStatus.OK);
    }



}
