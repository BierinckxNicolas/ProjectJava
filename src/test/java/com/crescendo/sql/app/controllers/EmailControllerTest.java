package com.crescendo.sql.app.controllers;

import com.crescendo.CrescendoApplication;
import com.crescendo.sql.app.models.CheckBox;
import com.crescendo.sql.app.models.Email;
import com.crescendo.sql.app.repositories.CheckboxRepository;
import com.crescendo.sql.app.repositories.EmailRepository;
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

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Gilles on 31/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrescendoApplication.class)
@WebAppConfiguration
public class EmailControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private Email email;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private EmailRepository emailRepository;

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

        this.email = emailRepository.findOne(1);
    }


    @Test
    public void getEmailShouldReturnOneEmail() throws Exception {
        mockMvc.perform(get("/api/email/" + this.email.getEmailid()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.emailid", is(this.email.getEmailid())))
                .andExpect(jsonPath("$.emailType", is(this.email.getEmailType())))
                .andExpect(jsonPath("$.emailContent", is(this.email.getEmailContent())));
    }

    @Test
    public void getEmailWithNoIdShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/email/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEmailWithIdNotInDBShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/email/6"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void updateEmailShouldReturnOK() throws Exception {
        String CheckboxJson = mapper.writeValueAsString(email);
        this.mockMvc.perform(post("/api/email/" + this.email.getEmailid())
                .contentType(contentType)
                .content(CheckboxJson))
                .andExpect(status().isOk());
    }


    @Test
    public void updateEmailWithoutBodyShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(post("/api/email/" + this.email.getEmailid())
                .contentType(contentType)
                .content(""))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateEmailIfIdNotInDbShouldReturnNotFound() throws Exception {
        String CheckboxJson = mapper.writeValueAsString(email);
        mockMvc.perform(post("api/email/6/")
                .content(CheckboxJson)
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmailWrongMediaType() throws Exception {
        String CheckboxJson = mapper.writeValueAsString(email);
        this.mockMvc.perform(post("/api/email/" + this.email.getEmailid())
                .contentType(MediaType.TEXT_PLAIN)
                .content(CheckboxJson))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void updateEmailShouldReturnUpdatedEmail() throws Exception {
        String CheckboxJson = mapper.writeValueAsString(email);
        mockMvc.perform(post("/api/email/" + this.email.getEmailid())
                .contentType(contentType)
                .content(CheckboxJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.emailid", is(this.email.getEmailid())))
                .andExpect(jsonPath("$.emailType", is(this.email.getEmailType())))
                .andExpect(jsonPath("$.emailContent", is(this.email.getEmailContent())));
    }



}