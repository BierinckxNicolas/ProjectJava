package com.crescendo.sql.utils;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Get schoolyear voor smartschool van config file
 * @author Groep 5
 * @version 0.1
 */
@Service
public class Schoolyear {

    private String SCHOOLYEAR;

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
    public Schoolyear() throws IOException {
        controleInputStream();
        this.SCHOOLYEAR = prop.getProperty("SCHOOLYEAR");
    }

    public String getSCHOOLYEAR() {
        return SCHOOLYEAR;
    }

    public void setSCHOOLYEAR(String SCHOOLYEAR) {
        this.SCHOOLYEAR = prop.getProperty("SCHOOLYEAR");
    }
}
