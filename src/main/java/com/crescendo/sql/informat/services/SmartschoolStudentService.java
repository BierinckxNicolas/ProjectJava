package com.crescendo.sql.informat.services;
import com.crescendo.informat.models.Student;
import com.crescendo.informat.services.InformatService;

import com.crescendo.smartschool.models.User;
import com.crescendo.smartschool.services.SmartschoolService;
import com.crescendo.sql.informat.models.SmartschoolStudents;
import com.crescendo.sql.informat.repositories.SmartschoolStudentRepository;
import com.crescendo.sql.smartschool.models.Users;
import com.crescendo.sql.smartschool.repositories.UserRepository;
import com.crescendo.sql.smartschool.services.SmartschoolUserService;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Random;

/** SmartschoolStudentService
 * @author Groep 5
 */
@Service
@SuppressWarnings("Duplicates")
public class SmartschoolStudentService {
    @Autowired
    InformatService informatService;

    @Autowired
    SmartschoolStudentRepository smartschoolStudentRepository;

    @Autowired
    InformatStudentService informatStudentService;

    @Autowired
    UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Pusht de studenten die smartschool nodig hebben naar de databank,
     * Deze checkt nog niet of ze al in smartschool zitten of niet. Dit gaat enkel uit van de informat cursisten.
     * smartschoolStudents.setEmail("cvocrescendoreceiver@gmail.com"); voor development doeleinden
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushSmartschoolStudents() throws IOException {
        smartschoolStudentRepository.deleteAll();
        SmartschoolStudents smartschoolStudents = new SmartschoolStudents();
        List<Student> students = informatService.getAllSmartschoolStudents("2017-18");


        for (Student student : students) {
            try
            {
                //VOOR PRODUCTIE
                //smartschoolStudents.setEmail(student.getEmail().get(0));

            }catch (IndexOutOfBoundsException | NullPointerException ex)
            {
                logger.error("Students : " + student.getFirstName() + " " + student.getLastName() + " heeft geen email adres");
                smartschoolStudents.setEmail("");
            }
            try {
                smartschoolStudents.setPhonenumber(student.getPhone().get(0));
            }catch (IndexOutOfBoundsException | NullPointerException ex) {
                logger.error("Students : " + student.getFirstName() + " " + student.getLastName() + " heeft geen telefoonnr");
                smartschoolStudents.setPhonenumber("");
            }
            //VOOR DEVELOPMENT
            //VOOR TESING & DEVELOPMENT CHANGE IN PRODUCTION
            Random random = new Random();
            int i = random.nextInt(10) + 1;
            if (i == 7){
                smartschoolStudents.setEmail("");
            } else{
                smartschoolStudents.setEmail("cvocrescendoreceiver@gmail.com");
            }

            smartschoolStudents.setId(student.getId());
            smartschoolStudents.setFirstName(student.getFirstName());
            smartschoolStudents.setLastName(student.getLastName());
            smartschoolStudents.setUsername(redundantSmartschoolUser(student));
            smartschoolStudents.setStamNr(student.getStamNr());
            smartschoolStudents.setSex(student.getSex());
            smartschoolStudents.setCity(student.getAddress().getCity().toLowerCase());
            smartschoolStudents.setStreet(student.getAddress().getStreet());
            smartschoolStudents.setHouseNumber(student.getAddress().getNumber());
            smartschoolStudents.setPoBox(student.getAddress().getPoBox());
            smartschoolStudents.setStreet(student.getAddress().getStreet());
            smartschoolStudents.setPostalCode(student.getAddress().getPostalCode());
            smartschoolStudents.setCountry(student.getAddress().getCountry());

            smartschoolStudentRepository.save(smartschoolStudents);
        }
    }

    /**
     * Checken of de username al bestaan en indien dit het geval is de username met 1 verhogen.
     * Dit zal gecheckt worden aan de hand van informat EN smartschool.
     * @param student de student die moet gecheckt worden
     * @return de correcte username
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    private String redundantSmartschoolUser(Student student) throws IOException {
       // List<Users> smartschoolUsers = userRepository.findAll();
        String newUser = student.getFirstName()+"."+student.getLastName();
        int eindGetal = 1;
        String endChar = String.valueOf(eindGetal);
        String username = newUser;

        if (smartschoolStudentRepository.existsByUsername(username) || userRepository.existsByUsername(username)) {
            while (smartschoolStudentRepository.existsByUsername(username) || userRepository.existsByUsername(username)) {
                username = newUser.concat(endChar);
                eindGetal++;
                endChar = String.valueOf(eindGetal);
            }
        }
        return username;
    }
}
