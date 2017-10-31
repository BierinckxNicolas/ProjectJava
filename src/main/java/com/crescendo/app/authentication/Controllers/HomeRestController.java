package com.crescendo.app.authentication.Controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletResponse;


import com.crescendo.app.authentication.models.AppUser;
import com.crescendo.app.authentication.repositories.AppUserRepository;
import com.crescendo.app.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * Webservices in deze controller zullen beschikbaar zijn voor alle users
 * public API
 * @author Groep 5
 */
@RestController
public class HomeRestController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //temp fix
    private static final String SALT = "AXPEOEL304953";

    /**
     * Deze methode zal de logged user returnen
     *
     * @param principal de principal
     * @return Principal java security principal object
     */
    @RequestMapping("/api")
    public AppUser user(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        return appUserRepository.findOneByUsername(loggedUsername);
    }


    /**
     * @param username Gebruikersnaam
     * @param password Wachtwoord
     * @param response Response
     * @return JSON bevat token en user na authenticatie
     * @throws IOException Wordt Gethrowed bij falen van aanmaken van token
     */
    @PostMapping(value = "/authenticate")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
                                                     HttpServletResponse response) throws IOException {
        String token = null;
        AppUser appUser = appUserRepository.findOneByUsername(username);
        Map<String, Object> tokenMap = new HashMap<String, Object>();
        if (appUser != null && appUser.getPassword().equals(passwordEncoder.generateHash(SALT + password))) {
            token = Jwts.builder().setSubject(username).claim("roles", appUser.getRoles()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            tokenMap.put("token", token);
            tokenMap.put("user", appUser);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
        } else {
            tokenMap.put("token", null);
            return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
        }
    }

}
