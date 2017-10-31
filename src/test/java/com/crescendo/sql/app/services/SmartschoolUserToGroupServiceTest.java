package com.crescendo.sql.app.services;

import com.crescendo.CrescendoApplication;
import com.crescendo.smartschool.models.User;
import com.crescendo.smartschool.services.SmartschoolService;
import com.crescendo.sql.app.repositories.CheckboxRepository;
import com.crescendo.sql.app.repositories.EmailRepository;
import com.crescendo.sql.informat.models.SmartschoolStudents;
import com.crescendo.sql.informat.repositories.CourseRepository;
import com.crescendo.sql.informat.repositories.SmartschoolStudentRepository;
import com.crescendo.sql.informat.repositories.StudenthistoryRepository;
import com.crescendo.sql.smartschool.models.Users;
import com.crescendo.sql.smartschool.repositories.UserRepository;
import com.crescendo.sql.smartschool.services.SmartschoolUserService;
import com.crescendo.sql.utils.EmailLogger;
import com.crescendo.sql.utils.PasswordGenerator;
import com.crescendo.sql.utils.Schoolyear;
import com.crescendo.sql.utils.Suffix;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Micky on 20/06/2017.
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrescendoApplication.class)
@WebAppConfiguration
public class SmartschoolUserToGroupServiceTest {

    @InjectMocks
    SmartschoolUserToGroupService smartschoolUserToGroupService;

    @Mock
    private SmartschoolStudentRepository smartschoolStudentRepository;
    @Mock
    private SmartschoolUserService smartschoolUserService;
    @Mock
    private SmartschoolService smartschoolService;
    @Mock
    private StudenthistoryRepository studenthistoryRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CheckboxRepository checkboxRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EmailRepository emailRepository;
    @Mock
    private SendMail sendMail;

    private Suffix suffix = new Suffix();
    private EmailLogger emailLogger = new EmailLogger();
    private Schoolyear schoolyear = new Schoolyear();
    private PasswordGenerator passwordGenerator = new PasswordGenerator();
    private String SCHOOLYEAR = schoolyear.getSCHOOLYEAR();
    private String SUFFIX = suffix.getSUFFIX();
    private String FILEPATH = emailLogger.getSSNOEMAILLOGGERPATH();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private BufferedWriter bw = null;

    public SmartschoolUserToGroupServiceTest() throws IOException {
    }


    @Test
    public void addUsersToParentGroup() throws Exception {
        File file = new File(FILEPATH);
        if (file.exists())
        {
            //delete if exists
            file.delete();
        }
        List<SmartschoolStudents> ssStudents = smartschoolStudentRepository.findAll();
        List<String> courseCodes;
        String courseRef;
        List<String> courseRefList = new ArrayList<>();
        String csvList;
        for (SmartschoolStudents ssStudent : ssStudents) {
            //HEEFT EMAIL?
            if (ssStudent.getEmail().contains("@")){
                //AANMAKEN IN SS
                String pw = passwordGenerator.generatePassword();
                smartschoolService.saveUser(ssStudent.getStamNr() + SUFFIX, ssStudent.getUsername() + SUFFIX, pw, "","", ssStudent.getFirstName(), ssStudent.getLastName(), "",
                        "", ssStudent.getSex(),"", "","", ssStudent.getStreet() + " " +  ssStudent.getHouseNumber() + " " + "bus " + ssStudent.getPoBox(), ssStudent.getPostalCode(),
                        ssStudent.getCity(), ssStudent.getCountry(), "cvocrescendoreceiver@gmail.com", ssStudent.getPhonenumber(),
                        "", "","","", "leerling", "");

                courseCodes = studenthistoryRepository.getCoursesByStamnrAndYear(SCHOOLYEAR, ssStudent.getStamNr());

                for (String course : courseCodes) {
                    courseRef = courseRepository.getThirdpartyrefById(course) + SUFFIX;
                    courseRefList.add(courseRef);
                }
                csvList = courseRefList.toString().replace("[", "").replace("]", "").replace(", ", ",");
                courseRefList.clear();
                smartschoolService.saveUserToClassesAndGroups(ssStudent.getStamNr() + SUFFIX, csvList, 0);

                if (checkboxRepository.findOne(2).isCheckboxState()){
                    // MAIL VERZENDEN NAAR STUDENT
                    // sendMail.sendSmartschoolmail(ssStudent, pw, emailRepository);
                }
            } else {
                //NO EMAIL FILE OPSTELLEN
            }
        }
    }

    @Test
    public void putUsersInCorrectGroups() throws Exception {
        List<String> courseCodes;
        List<User> users = smartschoolUserService.getAlleUsersGroep5();
        String courseRef;
        List<String> courseRefList = new ArrayList<>();
        String csvList;

        for (User user : users){
            courseCodes = studenthistoryRepository.getCoursesByStamnrAndYear(SCHOOLYEAR, user.getInternnummer().replace(SUFFIX, ""));
            for (String course : courseCodes) {
                courseRef = courseRepository.getThirdpartyrefById(course) + SUFFIX;
                courseRefList.add(courseRef);
            }
            csvList = courseRefList.toString().replace("[", "").replace("]", "").replace(", ", ",");
            courseRefList.clear();
            Assert.assertNotNull(user.getInternnummer());
            Assert.assertNotNull(csvList);
        }
    }

    @Test
    public void setCorrectStatus() throws Exception {
        List<Users> users = userRepository.findAll();

        for (Users user : users) {
            smartschoolService.setAccountStatus(user.getInternnummer(), user.getStatus());
        }
    }

}