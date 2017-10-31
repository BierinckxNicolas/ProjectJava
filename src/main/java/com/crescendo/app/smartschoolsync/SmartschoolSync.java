package com.crescendo.app.smartschoolsync;

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
import java.rmi.RemoteException;

/** Bevat de methode die alle onderliggende methodes gebruikt om met smartschool te syncen
 * @author Groep 5
 */
@Service
public class SmartschoolSync {

    @Autowired
    private SmartschoolUserToGroupService smartschoolUserToGroupService;

    @Autowired
    private SmartschoolGroupService smartschoolGroupService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * Voert alle onderliggende methodes uit om de sync met smartschool te voltooien
     * @throws IOException Wordt gethrowed bij het falen van het ophalen van data uit config file
     * @throws MessagingException Wordt gethrowed als de mail verzenden in {@link SmartschoolUserToGroupService#addUsersToParentGroup()} mislukt
     */
    public void ssSync() throws IOException, MessagingException {


        try {
            logger.info("Start addParentGroup push");
            smartschoolGroupService.addParentGroup();
            logger.info("End addParentGroup push");
            logger.info("Start addSubGroups push");
            smartschoolGroupService.addSubGroups();
            logger.info("End addParentGroup push");
            logger.info("Start addAllGroupsFromInformatCourses push");
            smartschoolGroupService.addAllGroupsFromInformatCourses();
            logger.info("End addAllGroupsFromInformatCourses push");
            logger.info("Start addUsersToParentGroup push");
            smartschoolUserToGroupService.addUsersToParentGroup();
            logger.info("End addUsersToParentGroup push");
            logger.info("Start putUsersInCorrectGroups push");
            smartschoolUserToGroupService.putUsersInCorrectGroups();
            logger.info("End putUsersInCorrectGroups push");
            logger.info("Start SetCorrectStatus push");
            smartschoolUserToGroupService.SetCorrectStatus();
            logger.info("End SetCorrectStatus push");
        } catch (ConfigurationException e) {
            logger.error("Er is een fout opgetreden tijdens het inladen van de config file " + e.getLocalizedMessage());
        }

    }

}
