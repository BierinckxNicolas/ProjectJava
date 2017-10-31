package com.crescendo.sql.utils;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Get filepaths voor logging files
 * @author Groep 5
 * @version 0.1
 */
@Service
public class EmailLogger {

    private String SSNOEMAILLOGGERPATH;
    private String INFORMATNOEMAILLOGGERPATH;

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
    public EmailLogger() throws IOException {
        controleInputStream();
        this.SSNOEMAILLOGGERPATH = prop.getProperty("SSNOEMAILLOGGERPATH");
        this.INFORMATNOEMAILLOGGERPATH = prop.getProperty("INFORMATNOEMAILLOGGERPATH");
    }

    public String getSSNOEMAILLOGGERPATH() {
        return SSNOEMAILLOGGERPATH;
    }

    public void setSSNOEMAILLOGGERPATH(String SSNOEMAILLOGGERPATH) {
        this.SSNOEMAILLOGGERPATH = prop.getProperty("SSNOEMAILLOGGERPATH");
    }

    public String getINFORMATNOEMAILLOGGERPATH() {
        return INFORMATNOEMAILLOGGERPATH;
    }

    public void setINFORMATNOEMAILLOGGERPATH(String INFORMATNOEMAILLOGGERPATH) {
        this.INFORMATNOEMAILLOGGERPATH = prop.getProperty("INFORMATNOEMAILLOGGERPATH");
    }
}
