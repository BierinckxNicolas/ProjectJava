package com.crescendo.sql.informat.services;

import com.crescendo.sql.app.repositories.CheckboxRepository;
import com.crescendo.sql.app.repositories.EmailRepository;
import com.crescendo.sql.app.services.SendMail;
import com.crescendo.sql.informat.models.*;
import com.crescendo.sql.informat.repositories.DeletedStudentsRepository;
import com.crescendo.sql.informat.repositories.FirstStudentRepository;
import com.crescendo.sql.informat.repositories.NewStudentRepository;
import com.crescendo.sql.utils.EmailLogger;
import com.sun.org.apache.xml.internal.utils.CharKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** NewStudentService
 * @author Groep 5
 */
@SuppressWarnings("Duplicates")
@Service
public class NewStudentService {
    @Autowired
    private FirstStudentRepository firstStudentRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private NewStudentRepository newStudentRepository;
    @Autowired
    private DeletedStudentsRepository deletedStudentsRepository;
    @Autowired
    private InformatStudentService informatStudentService;
    @Autowired
    private CheckboxRepository checkboxRepository;
    @Autowired
    private SendMail sendMail;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private EmailLogger emailLogger = new EmailLogger();
    private String FILEPATH = emailLogger.getINFORMATNOEMAILLOGGERPATH();
    private BufferedWriter bw = null;

    public NewStudentService() throws IOException {
    }


    /**
     * Gaat alle cursisten ophalen uit de cursisten tabel.<br>
     * Dan pushen we nog een keer alle cursisten. en halen we deze opnieuw op in een lijst.<br>
     * De 2 lijsten worden vergeleken, is de eerste lijst groter als de 2e, dan zijn er cursisten verwijderd<br>
     * is de 2e lijst groter als de 1ste dan zijn er cursisten bijgekomen<br>
     * Naar deze nieuwe cursisten zal dan een mail verstuurd worden, Als de cursist geen mail heeft zal deze worden opgeslagen in een file<br>
     * Deze file wordt dan opgestuurd naar de admin
     * @throws IOException Error bij schrijven naar de file
     * @throws MessagingException Wordt gethrowed als er iets fout gaat bij het versturen van de email.
     */
    @Transactional
    public void pushNewStudents() throws IOException, MessagingException {
        File file = new File(FILEPATH);
        if (file.exists())
        {
            //delete if exists
            file.delete();
        }
        newStudentRepository.deleteAll();
        List<FirstStudents> firstStudents = firstStudentRepository.findAll();
        informatStudentService.pushAllStudents();
        List<FirstStudents> secondStudents = firstStudentRepository.findAll();
        List<Students> firstStudentsList = new ArrayList<>();
        List<Students> secondStudentList = new ArrayList<>();
        List<Students> noEmailStudentList = new ArrayList<>();

        firstStudentsList.addAll(firstStudents);
        secondStudentList.addAll(secondStudents);

        NewStudents newStudent = new NewStudents();
        DeletedStudents deletedStudent = new DeletedStudents();

        //Als studenten toegevoegd zijn
       if (firstStudentsList.size() < secondStudentList.size()){
           secondStudentList.removeAll(firstStudentsList);
           logger.info("Er zijn " + secondStudentList.size() + " studenten toegevoegd in informat sinds laatste sync");
           //add to newStudents table
           for (Students student : secondStudentList) {
               newStudent.setEmail(student.getEmail());
               newStudent.setFirstName(student.getFirstName());
               newStudent.setLastName(student.getLastName());
               newStudent.setId(student.getId());
               newStudent.setStamNr(student.getStamNr());
               newStudent.setUsername(student.getUsername());
               newStudent.setCity(student.getCity());
               newStudent.setCountry(student.getCountry());
               newStudent.setHouseNumber(student.getHouseNumber());
               newStudent.setPoBox(student.getPoBox());
               newStudent.setPostalCode(student.getPostalCode());
               newStudent.setSex(student.getSex());
               newStudent.setStreet(student.getStreet());
               newStudent.setPhonenumber(student.getPhonenumber());
               newStudentRepository.save(newStudent);
               sendEmail(newStudent);
           }
           sendAdminMail();

       }
       //Als studenten verwijdered zijn
       else if (firstStudentsList.size() > secondStudentList.size()){
           firstStudents.removeAll(secondStudentList);
           logger.info("Er zijn " + firstStudents.size() + " studenten verwijderd uit informat sinds laatste sync");
            //fill deleteStudents table
          for (Students student : firstStudents) {
              deletedStudent.setEmail(student.getEmail());
              deletedStudent.setFirstName(student.getFirstName());
              deletedStudent.setLastName(student.getLastName());
              deletedStudent.setId(student.getId());
              deletedStudent.setStamNr(student.getStamNr());
              deletedStudent.setUsername(student.getUsername());
              deletedStudent.setCity(student.getCity());
              deletedStudent.setCountry(student.getCountry());
              deletedStudent.setHouseNumber(student.getHouseNumber());
              deletedStudent.setPoBox(student.getPoBox());
              deletedStudent.setPostalCode(student.getPostalCode());
              deletedStudent.setSex(student.getSex());
              deletedStudent.setStreet(student.getStreet());
              deletedStudent.setPhonenumber(student.getPhonenumber());
              deletedStudentsRepository.save(deletedStudent);
           }
       }
       //Er is niets veranderd
       else {
           logger.info("Er zijn geen studenten in informat toegevoegd of verwijderd sinds laatste sync");
       }
    }

    /**
     * Email verzenden naar een nieuwe cursist indien deze een email heeft<br>
     * Als de cursist geen email heeft worden zijn gegevens naar een logfile geschreven
     * @param newStudent een nieuwe student
     * @throws IOException Error bij schrijven naar de file
     * @throws MessagingException Wordt gethrowed als er iets fout gaat bij het versturen van de email.
     */
    private void sendEmail(NewStudents newStudent) throws MessagingException, IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILEPATH, true));
        if (newStudent.getEmail().contains("@")){
            if (checkboxRepository.findOne(3).isCheckboxState()){
                //GMAIL DOESN't ALLOW BULK MAILING (900++)
                Random random = new Random();
                int i = random.nextInt(40) + 1;
                if (i == 20){
                    sendMail.sendWelkommail(newStudent, emailRepository);
                }
            }
        }else {
            try {
                String content = "Student " + newStudent.getFirstName() + " " + newStudent.getLastName() + " en id: " + newStudent.getId() + " heeft geen welkomst email kunnen ontvangen, hij/zij heeft geen email adres. \n" +
                    "Informatie van deze student: \n" +
                    "Voornaam: " + newStudent.getFirstName() + "\n" +
                    "Achternaam: " + newStudent.getLastName() + "\n" +
                    "Geslacht: " + newStudent.getSex() + "\n" +
                    "Stamnummer: " + newStudent.getStamNr() + "\n" +
                    "Adres: " + newStudent.getStreet() + " " + newStudent.getHouseNumber() + " Pobox: " + newStudent.getPoBox() + "\n" +
                    "Stad: " + newStudent.getCity() + " " + newStudent.getPostalCode() + "\n" +
                    "Land: " + newStudent.getCountry() + "\n" +
                    "TelfoonNr: " + newStudent.getPhonenumber() +
                    "\n" +
                    "\n" +
                    "################################################" +
                    "\n" +
                    "\n";
                bw.write(content);
            } catch (IOException ioe) {
                logger.error("Er is een error opgetreden bij het aanmaken van de no email informat users logfile" + ioe.getLocalizedMessage());
            }finally
            {
                try{
                    if(bw!=null){
                        bw.close();
                    }
                }catch(Exception ex){
                    System.out.println("Error in bij het sluiten van de bufferedwriter" + ex.getLocalizedMessage());
                }
            }
        }
    }

    /**
     * Mail vezenden naar de admin met de file waarin alle cursisten zonder email in staan.
     * @throws IOException Error bij schrijven naar de file
     * @throws MessagingException Wordt gethrowed als er iets fout gaat bij het versturen van de email.
     */
    private void sendAdminMail() throws IOException, MessagingException {
        //SEND NO EMAIL IN INFORMAT TO ADMIN
        BufferedReader br = new BufferedReader(new FileReader(FILEPATH));
        if (!(br.readLine() == null)) {
            //MAIL VERZENDEN NAAR IWAN MET FOUTENLIJST ALS FILE NIET EMPTY IS
            sendMail.sendInformatLoggerMail();
            br.close();
        }
    }
}
