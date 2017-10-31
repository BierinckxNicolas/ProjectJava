package com.crescendo.app.authentication.Controllers;

import java.util.List;


import com.crescendo.app.authentication.models.AppUser;
import com.crescendo.app.authentication.repositories.AppUserRepository;
import com.crescendo.app.security.PasswordEncoder;
import com.crescendo.sql.utils.Schoolyear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * private API voor admin users!
 * @author Groep 5
 */
@RestController
@RequestMapping(value = "/api")
public class AppUserRestController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //temp fix
    private static final String SALT = "AXPEOEL304953";
    /**
     * Api endpoint om alle appusers van de app op te halen
     *
     * @return Lijst van alle appusers
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<AppUser> users() {
        return appUserRepository.findAll();
    }


    /**
     * Api endpoint om een users op te halen via zijn id
     *
     * @param id appUser id
     * @return appUser
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppUser> userById(@PathVariable Long id) {
        AppUser appUser = appUserRepository.findOne(id);
        if (appUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
        }
    }


    /**
     * Api endpoint om een user te deleten via zijn id
     *
     * @param id appUser id
     * @return statuscode
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppUser> deleteUser(@PathVariable Long id) {
        AppUser appUser = appUserRepository.findOne(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        if (appUser == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
        } else if (appUser.getUsername().equalsIgnoreCase(loggedUsername)) {
            throw new RuntimeException("Je kan jou account niet verwijderen");
        } else {
            appUserRepository.delete(appUser);
            return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
        }


    }


    /**
     * Api endpoint om appUser toe te voegen
     *
     * @param appUser een user
     * @return de users en status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        if (appUserRepository.findOneByUsername(appUser.getUsername()) != null) {
            throw new RuntimeException("Gebruikersnaam bestaat al");
        }
        appUser.setPassword(passwordEncoder.generateHash(SALT + appUser.getPassword()));
        return new ResponseEntity<AppUser>(appUserRepository.save(appUser), HttpStatus.CREATED);
    }


    /**
     * Api endpoint om een user te updaten
     *
     * @param appUser een users
     * @return de geupdate user
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public AppUser updateUser(@RequestBody AppUser appUser) {
        if (appUserRepository.findOneByUsername(appUser.getUsername()) != null
                && appUserRepository.findOneByUsername(appUser.getUsername()).getId() != appUser.getId()) {
            throw new RuntimeException("Gebruikersnaam bestaat al");
        }
        appUser.setPassword(passwordEncoder.generateHash(SALT + appUser.getPassword()));
        return appUserRepository.save(appUser);
    }



}
