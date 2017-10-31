package com.crescendo.sql.app.controllers;

import com.crescendo.sql.app.models.Email;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Logfile Restcontroller
 * @author Groep 5
 */
@RestController
@RequestMapping("/api")
public class DownloadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Api endpoint om de logfile door te streamen zodat deze in de frontend kan getoont worden
     * @param session Httpsession
     * @param response de response
     * @param filename de naam van de logfile
     * @return de inhoud van de opgevraagde logfile + status code
     * @throws Exception Wordt gehtrowed als de log file niet gevonden kan worden
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value="/logfile/{filename}")
    public ResponseEntity<?> getLogFile(HttpSession session, HttpServletResponse response, @PathVariable("filename") String filename) throws Exception {
        try {
            String filePathToBeServed = "logs/" + filename + " Application.log";
            File fileToDownload = new File(filePathToBeServed);
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+filename+" Application.log");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("File niet gevonden", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Api endpoint om de file met cursisten zonder email door te streamen aan de hand van de gegeven parameter,<br>
     * @param session Httpsession
     * @param response de response
     * @param filename de naam van de file
     * @return de inhoud van de opgevraagde file + status code
     * @throws Exception Wordt gehtrowed als de file niet gevonden kan worden
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value="/noemailfiles/{filename}")
    public ResponseEntity<?> getNoEmailFile(HttpSession session, HttpServletResponse response, @PathVariable("filename") String filename) throws Exception {
        try {
            String filePathToBeServed = "files/" + filename + ".txt";
            File fileToDownload = new File(filePathToBeServed);
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+filename + ".txt");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("File niet gevonden", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Api endpoint om de puntenlijst pdf door te streamen aan de hand van de gegeven parameter,<br>
     * @param session Httpsession
     * @param response de response
     * @param voornaam de voornaam van cursist
     * @param achternaam de achternaam van cursist
     * @return de inhoud van de opgevraagde file + status code
     * @throws Exception Wordt gehtrowed als de file niet gevonden kan worden
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value="/puntenpdf/{voornaam}/{achternaam}", produces = "application/pdf")
    public ResponseEntity<?> getPdfDownload(HttpSession session, HttpServletResponse response, @PathVariable("voornaam") String voornaam, @PathVariable("achternaam") String achternaam) throws Exception {
        try {
            String filePathToBeServed = "puntenPdfs/" + voornaam + "_" + achternaam + ".pdf";
            File fileToDownload = new File(filePathToBeServed);
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + voornaam + "_" + achternaam + ".pdf");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("File niet gevonden", HttpStatus.NOT_FOUND);
        }
    }

}
