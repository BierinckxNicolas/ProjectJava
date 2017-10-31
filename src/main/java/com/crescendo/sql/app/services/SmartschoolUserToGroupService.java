package com.crescendo.sql.app.services;


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
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.*;
import java.nio.file.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/** SmartschoolUserToGroupService
 * @author Groep 5
 */
@Service
public class SmartschoolUserToGroupService {

    @Autowired
    private SmartschoolStudentRepository smartschoolStudentRepository;
    @Autowired
    private SmartschoolUserService smartschoolUserService;
    @Autowired
    private SmartschoolService smartschoolService;
    @Autowired
    private StudenthistoryRepository studenthistoryRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CheckboxRepository checkboxRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
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

    public SmartschoolUserToGroupService() throws IOException {
    }

    /**
     * push alle studenten van de informatssstudents tabel naar smartschool, en zet all users in correcte groep<br>
     * Verstuur een mail naar de cursist als hij is aangemaakt in smartschool ALS checkboxstate op 1 staat<br>
     * Als de student geen email heeft wordt deze gelogd en doorgestuurd naar admin.
     * @throws IOException wordt gethrowed als er iets misgaat bij de call naar smartschool
     * @throws MessagingException Wordt gethrowed als er iets misgaat bij het versturen van de email
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    public void addUsersToParentGroup() throws IOException, MessagingException, ConfigurationException {
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
                        sendMail.sendSmartschoolmail(ssStudent, pw, emailRepository);
                  }
            } else {
                //NO EMAIL FILE OPSTELLEN
                makeNoEmailFile(ssStudent);
            }
        }
        sendAdminMail();
    }


    /**
     * Zet all users in de correcte groep in smartschool.<br>
     * Haal alle users uit smartschool, pak hiervan de intern nummer.<br>
     * Met deze internummer zoeken we de student ID<br>
     * Met deze id zoeken we de studenthistory en hieruit halen we de courses die de student volgt.
     * @throws IOException wordt gethrowed als er iets misgaat bij de call naar smartschool
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    public void putUsersInCorrectGroups() throws IOException, ConfigurationException {
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
            smartschoolService.saveUserToClassesAndGroups(user.getInternnummer(), csvList, 0);

        }
    }

    /**
     * Zet alle statussen van alle smartschool account correct
     * @throws RemoteException wordt gethrowed als er iets misgaat bij de call naar smartschool
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    public void SetCorrectStatus() throws RemoteException, ConfigurationException {
        List<Users> users = userRepository.findAll();

        for (Users user : users) {
            smartschoolService.setAccountStatus(user.getInternnummer(), user.getStatus());
        }
    }

    /**
     * Email verzenden naar een nieuwe smartschool user indien deze een email heeft<br>
     * Als de cursist geen email heeft worden zijn gegevens naar een logfile geschreven
     * @param ssStudent nieuse smartschool users
     * @param bw BufferedWriter
     * @throws IOException Wordt gethrowed als de file niet kan geschreven worden
     */
    private void makeNoEmailFile(SmartschoolStudents ssStudent) throws IOException {
        bw = new BufferedWriter(new FileWriter(FILEPATH, true));
        try {
            String content = "Student " + ssStudent.getFirstName() + " " + ssStudent.getLastName() + " en id: " + ssStudent.getId() + "is niet aangemaakt in smartschool, hij/zij heeft geen email adres. \n" +
                    "Informatie nodig voor smartschool: \n" +
                    "Voornaam: " + ssStudent.getFirstName() + "\n" +
                    "Achternaam: " + ssStudent.getLastName() + "\n" +
                    "Username voor in smartschool: " + ssStudent.getUsername() + "\n" +
                    "Geslacht: " + ssStudent.getSex() + "\n" +
                    "Stam nummer (internummer in smartschool): " +ssStudent.getStamNr() + "\n" +
                    "Adres: " + ssStudent.getStreet() + " " + ssStudent.getHouseNumber() + " Pobox: " + ssStudent.getPoBox() + "\n" +
                    "Stad: " + ssStudent.getCity() + " " + ssStudent.getPostalCode() + "\n" +
                    "Land: " + ssStudent.getCountry() +
                    "TelefoonNr: " + ssStudent.getPhonenumber() +
                    "\n" +
                    "\n" +
                    "################################################" +
                    "\n" +
                    "\n";
            bw.write(content);
        } catch (IOException ioe) {
            logger.error("Er is een error opgetreden bij het aanmaken van de no email smartschool users logfile" + ioe.getLocalizedMessage());
        }finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                logger.error("Error in bij het sluiten van de bufferedwriter" + ex.getLocalizedMessage());
            }
        }
    }

    /**
     * Mail vezenden naar de admin met de file waarin alle smartschool users zonder email in staan.
     * @throws IOException Error bij schrijven naar de file
     * @throws MessagingException Wordt gethrowed als er iets fout gaat bij het versturen van de email.
     */
    private void sendAdminMail() throws IOException, MessagingException {
        BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
        if (!(br.readLine() == null)) {
            //MAIL VERZENDEN NAAR IWAN MET FOUTENLIJST ALS FILE NIET EMPTY IS
            sendMail.sendSmartschoolLoggerMail();
            br.close();
        }
    }

}

