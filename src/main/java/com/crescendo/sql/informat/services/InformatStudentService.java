package com.crescendo.sql.informat.services;


import com.crescendo.informat.models.Student;
import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.informat.models.FirstStudents;
import com.crescendo.sql.informat.models.Students;
import com.crescendo.sql.informat.repositories.FirstStudentRepository;
import com.crescendo.sql.smartschool.repositories.UserRepository;
import com.crescendo.sql.smartschool.services.SmartschoolUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import com.google.common.base.Stopwatch;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/** InformatStudentService
 * @author Groep 5
 */
@SuppressWarnings("Duplicates")
@Service
public class InformatStudentService {

        @Autowired
        private FirstStudentRepository firstStudentRepository;

        public List<String> users = new ArrayList<>();
        private String lastUsername = "";
        private int usernameCounter = 1;

        private int serverErrorCounter = 0;
        @Autowired
        private InformatService informatService;

        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private SmartschoolUserService smartschoolUserService;

    /**
     * Alle cursisten uit informat naar de databank pushen.
     * smartschoolStudents.setEmail("cvocrescendoreceiver@gmail.com"); voor development doeleinden
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushAllStudents() throws IOException {
            firstStudentRepository.deleteAll();
            List<Student> students = informatService.getStudentList();
            FirstStudents firstStudent = new FirstStudents();

            for (Student student : students){
                try {
                    //VOOR PRODUCTIE
                   // firstStudent.setEmail(student.getEmail().get(0));
                }catch(IndexOutOfBoundsException  | NullPointerException ex){
                    logger.error("Student " + student.getFirstName() + " " + student.getLastName() + " heeft geen emailadres");
                    firstStudent.setEmail("");
                }
                try {
                    firstStudent.setPhonenumber(student.getPhone().get(0));
                } catch (IndexOutOfBoundsException | NullPointerException ex) {
                    firstStudent.setPhonenumber("");
                }
                try {
                    firstStudent.setStreet(student.getAddress().getStreet());
                    firstStudent.setHouseNumber(student.getAddress().getNumber());
                    firstStudent.setPostalCode(student.getAddress().getPostalCode());
                    firstStudent.setCountry(student.getAddress().getCountry());
                    firstStudent.setCity(student.getAddress().getCity().toLowerCase());
                    firstStudent.setPoBox(student.getAddress().getPoBox());
                }catch (NullPointerException ex) {
                }

                //VOOR TESING & DEVELOPMENT CHANGE IN PRODUCTION
                Random random = new Random();
                int i = random.nextInt(40) + 1;
                if (i == 20){
                    firstStudent.setEmail("");
                } else{
                    firstStudent.setEmail("cvocrescendoreceiver@gmail.com");
                }

                firstStudent.setId(student.getId());
                firstStudent.setUsername(redundantInformatUser(student));
                firstStudent.setLastName(student.getLastName());
                firstStudent.setStamNr(student.getStamNr());
                firstStudent.setFirstName(student.getFirstName());

                firstStudent.setSex(student.getSex());
                firstStudentRepository.save(firstStudent);
            }
        }


    /**
     * Eerste controlere voor username, dit checkt of een cursist meerdere keren in informat zit, en past zodanig de username aan.
     * @param student de student dat moet gecheckt worden
     * @return de correcte username
     */
    private String redundantInformatUser(Student student){
        String newUser = student.getFirstName()+"."+student.getLastName();
        int eindGetal = 1;
        String endChar = String.valueOf(eindGetal);
        String username = newUser;

        if (firstStudentRepository.existsByUsername(newUser)) {
            while (firstStudentRepository.existsByUsername(username)) {
                username = newUser.concat(endChar);
                eindGetal++;
                endChar = String.valueOf(eindGetal);
            }
        }
        return username;
        }
}