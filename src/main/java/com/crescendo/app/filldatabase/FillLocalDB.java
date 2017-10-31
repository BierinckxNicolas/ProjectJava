package com.crescendo.app.filldatabase;

import com.crescendo.informat.services.InformatService;
import com.crescendo.smartschool.services.SmartschoolService;
import com.crescendo.sql.app.repositories.EmailRepository;
import com.crescendo.sql.app.services.SendMail;
import com.crescendo.sql.app.services.SmartschoolGroupService;
import com.crescendo.sql.app.services.SmartschoolUserToGroupService;
import com.crescendo.sql.informat.repositories.CourseRepository;
import com.crescendo.sql.informat.repositories.FirstStudentRepository;
import com.crescendo.sql.informat.repositories.StudenthistoryRepository;
import com.crescendo.sql.informat.services.*;
import com.crescendo.sql.smartschool.services.SmartschoolUserService;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

/** Bevate de methode die alle onderliggende methodes gebruikt om de database te vullen
 * @author Groep 5
 */
@Service
public class FillLocalDB {


    @Autowired
    private InformatStudentService informatStudentService;
    @Autowired
    private SmartschoolStudentService smartschoolStudentService;
    @Autowired
    private InformatService informatService;
    @Autowired
    private NewStudentService newStudentService;
    @Autowired
    private InformatCourseModuleService informatCourseModuleService;
    @Autowired
    private InformatStudenthistoryService informatStudenthistoryService;
    @Autowired
    private InformatOtherTeachersService informatOtherTeachersService;
    @Autowired
    private SmartschoolUserService smartschoolUserService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Voert alle onderliggende methodes uit om de database op te vullen
     * @throws IOException Wordt gethrowed bij het falen van het ophalen van data uit config file
     * @throws MessagingException Wordt gethrowed als de mail verzenden in {@link NewStudentService#pushNewStudents()} mislukt
     */
    public void fillDb() throws IOException, MessagingException {
        logger.info("Start courseModule push");
        informatCourseModuleService.pushCourseModule();
        logger.info("End courseModule push");
        logger.info("Start MaintTeacher push");
        informatCourseModuleService.pushAllMainTeachers();
        logger.info("End MaintTeacher push");
        logger.info("Start Departments push");
        informatCourseModuleService.pushAllDepartments();
        logger.info("End Departments push");
        logger.info("Start Location push");
        informatCourseModuleService.pushAllLocations();
        logger.info("End Location push");
        logger.info("Start Studiegebied push");
        informatCourseModuleService.pushAllStudiegebieden();
        logger.info("End Studiegebied push");
        logger.info("Start OtherTeahcer push");
        informatOtherTeachersService.pushAlleOtherTeachers();
        logger.info("End OtherTeahcer push");
        logger.info("Start NewStudent push");
        newStudentService.pushNewStudents();
        logger.info("End NewStudent push");
        logger.info("Start StudentHistory push");
        informatStudenthistoryService.pushAllStudentHistories();
        logger.info("End StudentHistory push");
        logger.info("Start SmartschoolStudent push");
        smartschoolStudentService.pushSmartschoolStudents();
        logger.info("End SmartschoolStudent push");
        logger.info("Start SmartschoolUser push");
        try {
            smartschoolUserService.databaseFillSmartschoolUsers();
        } catch (ConfigurationException e) {
            logger.error("Er is een fout opgetreden tijdens het inladen van de config file " + e.getLocalizedMessage());
        }
        logger.info("End SmartschoolUser push");
        logger.info("Start Accountsync push");
        smartschoolUserService.accountSync();
        logger.info("End Accountsync push");

    }



}
