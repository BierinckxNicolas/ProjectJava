package com.crescendo.sql.app.controllers;

import com.crescendo.CrescendoApplication;
import com.crescendo.sql.app.models.CheckBox;
import com.crescendo.sql.app.repositories.CheckboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by Gilles on 31/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrescendoApplication.class)
@WebAppConfiguration
public class CheckboxControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private CheckBox checkBox;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CheckboxRepository checkboxRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.checkBox = checkboxRepository.findOne(1);
    }





    @Test
    public void getCheckboxStateShouldReturnOneCheckbox() throws Exception {
        mockMvc.perform(get("/api/checkbox/" + this.checkBox.getCheckboxId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.checkboxId", is(this.checkBox.getCheckboxId())))
                .andExpect(jsonPath("$.checkboxType", is(this.checkBox.getCheckboxType())))
                .andExpect(jsonPath("$.checkboxState", is(this.checkBox.isCheckboxState())));
    }

    @Test
    public void getCheckboxWithNoIdShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/checkbox/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCheckboxWithIdNotInDBShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/checkbox/6"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void updateCheckboxShouldReturnOK() throws Exception {
        String CheckboxJson = mapper.writeValueAsString(checkBox);
        this.mockMvc.perform(post("/api/checkbox/" + this.checkBox.getCheckboxId())
                .contentType(contentType)
                .content(CheckboxJson))
                .andExpect(status().isOk());
    }


    @Test
    public void updateCheckboxWithoutBodyShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(post("/api/checkbox/" + this.checkBox.getCheckboxId())
                .contentType(contentType)
                .content(""))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateCheckboxIfIdNotInDbShouldReturnNotFound() throws Exception {
        String CheckboxJson = mapper.writeValueAsString(checkBox);
        mockMvc.perform(post("api/checkbox/6/")
                .content(CheckboxJson)
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateCheckboxWrongMediaType() throws Exception {
        String CheckboxJson = mapper.writeValueAsString(checkBox);
        this.mockMvc.perform(post("/api/checkbox/" + this.checkBox.getCheckboxId())
                .contentType(MediaType.TEXT_PLAIN)
                .content(CheckboxJson))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void updateCheckboxShouldReturnUpdatedCheckbox() throws Exception {
        String CheckboxJson = mapper.writeValueAsString(checkBox);
        mockMvc.perform(post("/api/checkbox/" + this.checkBox.getCheckboxId())
                .contentType(contentType)
                .content(CheckboxJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.checkboxId", is(this.checkBox.getCheckboxId())))
                .andExpect(jsonPath("$.checkboxType", is(this.checkBox.getCheckboxType())))
                .andExpect(jsonPath("$.checkboxState", is(this.checkBox.isCheckboxState())));
    }



}