package com.crescendo.sql.app.controllers;

import com.crescendo.CrescendoApplication;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Gilles on 20-6-2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrescendoApplication.class)
@WebAppConfiguration
public class DownloadControllerTest {
    @InjectMocks
    DownloadController downloadController;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getLogFileTest() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        String date = sdf.format(new Date());
        File file = new File("logs/" + date + " Application.log");
        if (file.exists()) {
            mockMvc.perform(get("/api/logfile/" + date))
                    .andExpect(status().isOk());
        } else {
            mockMvc.perform(get("/api/logfile/" + date))
                    .andExpect(status().isNotFound());
        }
    }

    @Test
    public void getNoEmailFile() throws Exception {
        File file = new File("files/SSUsers_No_Email.txt");
        if (file.exists()) {
            mockMvc.perform(get("/api/noemailfiles/SSUsers_No_Email"))
                    .andExpect(status().isOk());
        } else {
            mockMvc.perform(get("/api/noemailfiles/SSUsers_No_Email"))
                    .andExpect(status().isNotFound());
        }
    }
    @Test
    public void getPdfFile() throws Exception {
        String filenaam = "test_test";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("puntenPdfs/" + filenaam + ".pdf"));
        File file = new File("puntenPdfs/" + filenaam + ".pdf");
        if (file.exists()) {
            mockMvc.perform(get("/api/puntenpdf/test/test"))
                    .andExpect(status().isOk());
        } else {
            mockMvc.perform(get("/api/puntenpdf/test/test"))
                    .andExpect(status().isNotFound());
        }

    }


}