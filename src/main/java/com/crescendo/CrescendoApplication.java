package com.crescendo;


import com.crescendo.app.filldatabase.FillLocalDB;
import com.crescendo.app.scheduling.ScheduledTasks;
import com.crescendo.app.smartschoolsync.SmartschoolSync;

import com.crescendo.informat.models.StudentHistory;
import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.app.services.SmartschoolUserToGroupService;
import com.crescendo.sql.informat.services.InformatCourseModuleService;
import com.crescendo.sql.informat.services.InformatStudenthistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class CrescendoApplication {

    @Autowired
    private InformatStudenthistoryService informatStudenthistoryService;

    public static void main(String[] args) {
        Calendar today = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String logName = "logs/"+df.format(today.getTime())+" Application.log";
        System.setProperty("logging.file",logName);

        SpringApplication.run(CrescendoApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }


    @Bean
    CommandLineRunner runAtStartup() {
        return args -> {
            informatStudenthistoryService.pushAllStudentHistories();
        };
    }
}
