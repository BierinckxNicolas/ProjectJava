package com.crescendo.sql.app.controllers;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/** Config restcontroller
 * @author Groep 5
 */
@RestController
@RequestMapping("/api")
public class ConfigController {


    private PropertiesConfiguration conf = new PropertiesConfiguration("config.properties");

    public ConfigController() throws IOException, ConfigurationException {
    }

    /**
     * Api endpoint om schooljaar propery op te halen
     * @return schooljaar property + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/config/schoolyear",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getSchoolyear() {
        String schooljaar = String.valueOf(conf.getProperty("SCHOOLYEAR"));

        return new ResponseEntity<String>(schooljaar,HttpStatus.OK);
    }

    /**
     * Api endpoint om schooljaar property up te daten
     * @param schooljaar schooljaar
     * @return schooljaar property + statuscode
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "config/schoolyear/{schooljaar}",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> setSchoolyear(@PathVariable("schooljaar") String schooljaar){
     if(validateSchoolyear(schooljaar)){
         conf.setProperty("SCHOOLYEAR", schooljaar);
         try {
             conf.save();
             return new ResponseEntity<>(schooljaar, HttpStatus.OK);
         } catch (ConfigurationException e) {
             return new ResponseEntity<>(schooljaar, HttpStatus.BAD_REQUEST);
         }

     } else {
         return new ResponseEntity<>(schooljaar, HttpStatus.BAD_REQUEST);
     }
    }

    /**
     * Api endpoint om informat api versie propery op te halen
     * @return informat api versie propery property + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/config/apiversioninformat",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getApiVersionInformat() {
        String baseUrl = String.valueOf(conf.getProperty("URLINFORMAT"));
        baseUrl = baseUrl.replace("https://testservices.informatsoftware.be/icursisten/", "");
        return new ResponseEntity<String>(baseUrl,HttpStatus.OK);
    }

    /**
     * Api endpoint om informat api versie property up te daten
     * @param apiversion informat api versie
     * @return informat api versie + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "config/apiversioninformat/{apiversion}")
    public ResponseEntity<?> setApiVersionInformat(@PathVariable("apiversion") String apiversion){
        String baseUrl = "https://testservices.informatsoftware.be/icursisten/" + apiversion;
        conf.setProperty("URLINFORMAT", baseUrl);
        try {
            conf.save();
            return new ResponseEntity<>(apiversion, HttpStatus.OK);
        } catch (ConfigurationException e) {
            return new ResponseEntity<>(apiversion, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Api endpoint om smartschool api versie propery op te halen
     * @return smartschool api versie  property + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/config/apiversionss",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getApiVersionSS() {
        String baseUrl = String.valueOf(conf.getProperty("URLSS"));
        baseUrl = baseUrl.replace("https://cvo-crescendo-slo.smartschool.be/Webservices/", "");
        return new ResponseEntity<String>(baseUrl,HttpStatus.OK);
    }

    /**
     * Api endpoint om smartschool api versie property up te daten
     * @param apiversion smartschool api versie
     * @return smartschool api versie + statuscode
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "config/apiversionss/{apiversion}",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> setApiVersionSS(@PathVariable("apiversion") String apiversion){
            String baseUrl = "https://cvo-crescendo-slo.smartschool.be/Webservices/" + apiversion;
            conf.setProperty("URLSS", baseUrl);
            try {
                conf.save();
                return new ResponseEntity<>(apiversion, HttpStatus.OK);
            } catch (ConfigurationException e) {
                return new ResponseEntity<>(apiversion, HttpStatus.BAD_REQUEST);
            }
    }

    /**
     * Api endpoint om smartschool key propery op te halen
     * @return martschool key property + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/config/sskey",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getSsKey() {
        String key = String.valueOf(conf.getProperty("SSKEY"));
        return new ResponseEntity<String>(key,HttpStatus.OK);
    }

    /**
     * Api endpoint om smartschool key property up te daten
     * @param sskey smartschool key
     * @return smartschool key
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "config/sskey/{sskey}",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> setSsKey(@PathVariable("sskey") String sskey){

            conf.setProperty("SSKEY", sskey);
            try {
                conf.save();
                return new ResponseEntity<>(sskey, HttpStatus.OK);
            } catch (ConfigurationException e) {
                return new ResponseEntity<>(sskey, HttpStatus.BAD_REQUEST);
            }
    }

    /**
     * Api endpoint om instituurnummer propery op te halen
     * @return instituurnummer property + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/config/institutenr",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getInstituteNr() {
        String institute_nr = String.valueOf(conf.getProperty("INSTITUTE_NR"));
        return new ResponseEntity<String>(institute_nr,HttpStatus.OK);
    }

    /**
     * Api endpoint om instituutnummer property up te daten
     * @param institutenr instituutnummer
     * @return instituutnummer + status code
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "config/institutenr/{institutenr}",  produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> setInstituteNr(@PathVariable("institutenr") String institutenr) {
        conf.setProperty("INSTITUTE_NR", institutenr);
        try {
            conf.save();
            return new ResponseEntity<>(institutenr, HttpStatus.OK);
        } catch (ConfigurationException e) {
            return new ResponseEntity<>(institutenr, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * private methode om de validated of de input wel een correct shcoljaar is
     * @param schooljaar schooljaar
     * @return Boolean aan de han of het schooljaar correct is of niet
     */
    public boolean validateSchoolyear(String schooljaar){
        try {
            String[] output = schooljaar.split("-");
            String schooljaar1 = output[0];
            String schooljaar2 = output[1];
            int schooljaar1Int = Integer.parseInt(schooljaar1);
            int schooljaar2Int = Integer.parseInt(schooljaar2);
            return schooljaar1Int >= 2000 && schooljaar1Int <= 3000 && schooljaar2Int == schooljaar1Int - 1999;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException ex2) {
        }
        return false;
    }


}
