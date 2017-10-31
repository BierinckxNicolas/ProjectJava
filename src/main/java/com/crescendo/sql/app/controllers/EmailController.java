package com.crescendo.sql.app.controllers;

import com.crescendo.sql.app.models.Email;
import com.crescendo.sql.app.repositories.EmailRepository;
import com.crescendo.sql.app.services.SendMail;
import com.crescendo.sql.informat.models.NewStudents;
import com.crescendo.sql.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;


/** Email restcontroller
 * @author Groep 5
 */
@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private SendMail sendMail;


    /**
     * Api endpoint om de emailcontent met gegeven id uit de database op te halen
     * @param id de id van de email
     * @return de content van de email + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/email/{id}")
    public ResponseEntity<Email> getEmail(@PathVariable("id") int id) {
        Email email = emailRepository.findOne(id);
        if (email == null){
            return new ResponseEntity(new CustomErrorType("unable to retrieve email with id " + id + ". Not Found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Email>(email,HttpStatus.OK);
    }

    /**
     * Api endpoint om de emailcoontent met gegeven id up te daten in de databank
     * @param id de id van de email
     * @param email de content van de email
     * @param bindingResult checkt of de input correct is
     * @return de upgedate email + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "email/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateEmail(@PathVariable("id") int id, @Valid @RequestBody Email email, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new CustomErrorType("unable to update email with id " + id + ". Bad Request"), HttpStatus.BAD_REQUEST);
        }
        Email currentEmail = emailRepository.findOne(id);
        if (currentEmail == null) {
            return new ResponseEntity<>(new CustomErrorType("unable to update email with id " + id + " not found"), HttpStatus.BAD_REQUEST);
        }

        currentEmail.setEmailContent(email.getEmailContent());
        emailRepository.save(currentEmail);

        return new ResponseEntity<Email>(currentEmail, HttpStatus.OK);
    }

    /**
     * Api endpoint manueel de welkomst mail te versturen naar 1 student aan de hand een studentobject
     * @param newStudents student die de welkomstmail moet ontvangen
     * @return de student die de welkomstmail moet ontvangen + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "sendwelkomstmail",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NewStudents> sendWelkomstmail(@Valid @RequestBody NewStudents newStudents){
        NewStudents newStudents1 = new NewStudents();
        newStudents1.setEmail(newStudents.getEmail());
        newStudents1.setFirstName(newStudents.getFirstName());
        try {
            sendMail.sendWelkommail(newStudents1, emailRepository);
            return new ResponseEntity<NewStudents>(newStudents, HttpStatus.OK);

        } catch (MessagingException e) {
            return new ResponseEntity<NewStudents>(newStudents, HttpStatus.BAD_REQUEST);
        }

    }


}
