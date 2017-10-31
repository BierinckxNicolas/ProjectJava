package com.crescendo.sql.utils;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Get prefix voor smartschool van config file
 * @author Groep 5
 * @version 0.1
 */
@Service
public class Suffix {

    private String SUFFIX;

    // Properties object aanmaken om de config file uit te lezen
    private static Properties prop = new Properties();

    // Inputstream maken vanuit de config.properties file
    private InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

    public void controleInputStream() throws IOException {
        if(inputStream != null) {
            prop.load(inputStream);
        }else {
            throw new FileNotFoundException("Property file niet gevonden");
        }
    }
    public Suffix() throws IOException {
        controleInputStream();
        this.SUFFIX = prop.getProperty("SUFFIX");
    }

    public String getSUFFIX() {
        return SUFFIX;
    }

    public void setSUFFIX(String SUFFIX) {
        this.SUFFIX = prop.getProperty("SUFFIX");
    }
}
