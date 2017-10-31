package com.crescendo.informat.services.utils;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/** Alle getters en setter voor de informatkey
 * @author Groep 5
 * @version 0.1
 */
@Service
public class InformatKey {

    private String PRIVATE_KEY;
    private String PUBLIC_KEY;
    private String INSTITUTE_NR;

    // Properties object aanmaken om de config file uit te lezen
    private static Properties prop = new Properties();

    // Inputstream maken vanuit de config.properties file
    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

    public void controleInputStream() throws IOException {
        if(inputStream != null)
        {
            prop.load(inputStream);

        }else
        {
            throw new FileNotFoundException("Property file niet gevonden");
        }
    }


    public InformatKey() throws IOException {
        controleInputStream();
        this.PRIVATE_KEY = prop.getProperty("PRIVATE_KEY");
        this.PUBLIC_KEY = prop.getProperty("PUBLIC_KEY");
        this.INSTITUTE_NR = prop.getProperty("INSTITUTE_NR");
    }

    public String PRIVATE_KEY() {
        return PRIVATE_KEY;
    }

    private void setPrivateKey() {
        PRIVATE_KEY = prop.getProperty("PRIVATE_KEY");
    }

    public String PUBLIC_KEY() {
        return PUBLIC_KEY;
    }

    private void setPublicKey() {
        PUBLIC_KEY = prop.getProperty("PUBLIC_KEY");
    }

    public String INSTITUTE_NR() {
        return INSTITUTE_NR;
    }

    private void setInstituteNr() {
        INSTITUTE_NR = prop.getProperty("INSTITUTE_NR");
    }

}
