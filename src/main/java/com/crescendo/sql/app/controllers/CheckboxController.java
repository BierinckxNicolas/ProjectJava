package com.crescendo.sql.app.controllers;

import com.crescendo.sql.app.models.CheckBox;
import com.crescendo.sql.app.models.Email;
import com.crescendo.sql.app.repositories.CheckboxRepository;
import com.crescendo.sql.app.repositories.EmailRepository;
import com.crescendo.sql.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/** Checkbox restcontroller
 * @author Groep 5
 */
@RestController
@RequestMapping("/api")
public class CheckboxController {

    @Autowired
    private CheckboxRepository checkboxRepository;

    /**
     * Api endpoint om de state van een checkbox met gegeven id op te halen
     * @param id id van checkbox
     * @return state van checkbox + statuscode
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/checkbox/{id}")
    public ResponseEntity<CheckBox> getCheckboxState(@PathVariable("id") int id) {
        CheckBox checkBox = checkboxRepository.findOne(id);
        if (checkBox == null){
            return new ResponseEntity(new CustomErrorType("unable to retrieve checkbox with id " + id + ". Not Found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CheckBox>(checkBox,HttpStatus.OK);
    }

    /**
     * Api endpoint om de state van een checkbox met gegeven id up te date
     * @param id id van checkbox
     * @param checkBox checkbox state
     * @param bindingResult input checker
     * @return geupdate state van checkbox + statuscode
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "checkbox/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCheckBox(@PathVariable("id") int id,@Valid @RequestBody CheckBox checkBox, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new CustomErrorType("unable to update checkbox with id " + id + ". Bad Request"), HttpStatus.BAD_REQUEST);
        }
        CheckBox currentCheckbox = checkboxRepository.findOne(id);
        if (currentCheckbox == null ) {
            return new ResponseEntity<>(new CustomErrorType("unable to update checkbox with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }

        currentCheckbox.setCheckboxState(checkBox.isCheckboxState());
        checkboxRepository.save(currentCheckbox);

        return new ResponseEntity<CheckBox>(currentCheckbox, HttpStatus.OK);
    }

}
