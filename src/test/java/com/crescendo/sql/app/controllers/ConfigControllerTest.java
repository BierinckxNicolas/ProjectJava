package com.crescendo.sql.app.controllers;

import com.crescendo.CrescendoApplication;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Micky on 20/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrescendoApplication.class)
@WebAppConfiguration
public class ConfigControllerTest {

    @InjectMocks
    ConfigController configController;

    private PropertiesConfiguration conf = new PropertiesConfiguration("config.properties");

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
    @Autowired
    private WebApplicationContext webApplicationContext;



    public ConfigControllerTest() throws ConfigurationException {
    }


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void getSchoolyear() throws Exception {
        this.mockMvc.perform(post("/api/config/schoolyear/2017-18"))
                .andExpect(status().isOk());
    }

    @Test
    public void setSchoolyear() throws Exception {

        String[] output = "2017-18".split("-");
        String schooljaar1 = output[0];
        String schooljaar2 = output[1];
        int schooljaar1Int = Integer.parseInt(schooljaar1);
        int schooljaar2Int = Integer.parseInt(schooljaar2);

        if(schooljaar1Int >= 2000 && schooljaar1Int <= 3000 && schooljaar2Int == schooljaar1Int - 1999){
            conf.setProperty("SCHOOLYEAR", "2017-18");
            try {
                conf.save();
                this.mockMvc.perform(post("/api/config/schoolyear/2017-18")).andExpect(status().isOk());
            } catch (ConfigurationException e) {
                this.mockMvc.perform(post("/api/config/schoolyear/2017-18")).andExpect(status().isBadRequest());            }

        } else {
            this.mockMvc.perform(post("/api/config/schoolyear/2017-18")).andExpect(status().isBadRequest());        }
    }

    @Test
    public void getApiVersionInformat() throws Exception {
        this.mockMvc.perform(get("/api/config/apiversioninformat"))
                .andExpect(status().isOk());
    }


    @Test
    public void getApiVersionSS() throws Exception {
        this.mockMvc.perform(get("/api/config/apiversionss"))
                .andExpect(status().isOk());
    }


    @Test
    public void getSsKey() throws Exception {
        this.mockMvc.perform(get("/api/config/sskey"))
                .andExpect(status().isOk());
    }


    @Test
    public void getInstituteNr() throws Exception {
        this.mockMvc.perform(get("/api/config/institutenr"))
                .andExpect(status().isOk());
    }

    @Test
    public void testYearWithWrongValue() {
        try {
            ConfigController configController = new ConfigController();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        String jaar = "2015-2016";
        assertEquals(false, configController.validateSchoolyear(jaar));
    }

    @Test
    public void testYearWithNoFollowUpValue() {
        try {
            ConfigController configController = new ConfigController();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        String jaar = "2015-17";
        assertEquals(false, configController.validateSchoolyear(jaar));
    }

    @Test
    public void testYearWithCorrectValue() {
        try {
            ConfigController configController = new ConfigController();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        String jaar = "2016-17";
        assertEquals(true, configController.validateSchoolyear(jaar));
    }


}