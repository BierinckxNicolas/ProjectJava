package com.crescendo.sql.app.controllers;

import com.crescendo.CrescendoApplication;
import com.crescendo.sql.app.models.CheckBox;
import com.crescendo.sql.app.models.Statistic;
import com.crescendo.sql.app.repositories.CheckboxRepository;
import com.crescendo.sql.app.repositories.StatisticRepository;
import com.crescendo.sql.informat.models.SmartschoolStudents;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Gilles on 20-6-2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrescendoApplication.class)
@WebAppConfiguration
public class StatisticsControllerTest {

    private MockMvc mockMvc;


    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getServerErrorShouldReturnOk() throws Exception {
        Statistic statistic = statisticRepository.findOne(1);
        Long aantal = statistic.getAantal();
        mockMvc.perform(get("/api/servererrors"))
                .andExpect(content().json(aantal.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void getsyncDurationShouldReturnOk() throws Exception {
        Statistic statistic = statisticRepository.findOne(3);
        Long aantal = statistic.getAantal();
        mockMvc.perform(get("/api/syncduration"))
                .andExpect(content().json(aantal.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void getLastSyncShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/lastsync")
                .contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void getNewSSUserShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/newusers"))
                .andExpect(status().isOk());
    }
    @Test
    public void getNewCursistenShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/newcursisten"))
                .andExpect(status().isOk());
    }
    @Test
    public void getNumberOfSSCourses() throws Exception {
        mockMvc.perform(get("/api/aantalsscourses"))
                .andExpect(status().isOk());
    }
    @Test
    public void geAantalCoursesShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/aantalcourses"))
                .andExpect(status().isOk());
    }
    @Test
    public void getAantalCursistenShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/aantalcursisten"))
                .andExpect(status().isOk());
    }
    @Test
    public void getAantalFullCoursesShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/aantalvollecourses"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAantalSsUsersShouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/aantalssusers"))
                .andExpect(status().isOk());
    }









}