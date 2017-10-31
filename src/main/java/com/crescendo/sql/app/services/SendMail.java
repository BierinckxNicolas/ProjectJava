package com.crescendo.sql.app.services;

import com.crescendo.app.mailing.Mailing;
import com.crescendo.sql.app.repositories.EmailRepository;
import com.crescendo.sql.informat.models.NewStudents;
import com.crescendo.sql.informat.models.SmartschoolStudents;
import com.crescendo.sql.utils.EmailLogger;
import com.crescendo.sql.utils.Suffix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


/** SendMailService
 * @author Groep 5
 */
@Service
public class SendMail {

    @Autowired
    private EmailRepository emailRepository;

    private EmailLogger emailLogger = new EmailLogger();
    private Suffix suffix = new Suffix();


    private final String ADMINEMAIL = "cvocrescendoreceiver@gmail.com";
    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    private final String mailXml = "Spring-Mail.xml";
    private final String mailBean = "mailMail";


    private final String SUFFIX = suffix.getSUFFIX();
    private String FILENAMESS = emailLogger.getSSNOEMAILLOGGERPATH();
    private String FILENAMEINFORMAT = emailLogger.getINFORMATNOEMAILLOGGERPATH();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ApplicationContext CONTEXT = new ClassPathXmlApplicationContext(mailXml);
    private final Mailing MM = (Mailing) CONTEXT.getBean(mailBean);


    public SendMail() throws IOException {
    }


    /**
     * Welkomstmail versturen, de content van mail wordt opgehaald uit de databank,
     * De Firstname in de content zal vervangen worden door de firstname van de student die wordt doorgegeven.
     * @param newStudents student naar waar gemaild moet worden
     * @param emailRepository emailrepository
     * @throws MessagingException wordt gethrowed bij het mislukken van het vezenden van de mail
     */
    public void sendWelkommail(NewStudents newStudents,  EmailRepository emailRepository) throws MessagingException {
        String welkomMailContent = emailRepository.findOne(1).getEmailContent();
        welkomMailContent = welkomMailContent.replace("[FIRSTNAME]", newStudents.getFirstName());
        welkomMailContent = welkomMailContent.replace("[DATE]",now());

        MM.sendMail("Welkomstmail", welkomMailContent, newStudents.getEmail());
    }

    /**
     * Logfile verzenden met informatcursisten zonder email naar de amdmin
     * @throws FileNotFoundException wordt gehtrowed als de logfile niet gevonden is
     * @throws MessagingException wordt gethrowed bij het mislukken van het vezenden van de mail
     */
    public void sendInformatLoggerMail() throws FileNotFoundException, MessagingException {
        String mailContent = "Beste, \n " +
                "In bijlage de lijst van alle cursisten die geen welkomstmail hebben kunnen ontvangen omdat zij geen emailadres hebben.";
        File file = getFileInformat();
        FileSystemResource  attachment = new FileSystemResource(file);
        MM.sendMailWithattachment("Cursisten zonder email", mailContent, ADMINEMAIL, attachment);
    }
    /**
     * Methode om een mail te sturen naar een student als hij is aangemaakt is in smartshool
     * @param smartschoolStudent het studenten obbject met alle info
     * @param password het passwoord voor deze student
     * @param emailRepository emailrepository
     * @throws MessagingException wordt gethrowed bij het mislukken van het vezenden van de mail
     * @throws IOException Wordt gethrowd als het inlezen van de file mislukt
     */
    public void sendSmartschoolmail(SmartschoolStudents smartschoolStudent, String password, EmailRepository emailRepository) throws MessagingException, IOException {
            String ssMailContent = emailRepository.findOne(2).getEmailContent();
            ssMailContent = ssMailContent.replace("[FIRSTNAME]", smartschoolStudent.getFirstName());
            ssMailContent = ssMailContent.replace("[PASSWORD]", password);
            ssMailContent = ssMailContent.replace("[USERNAME]", smartschoolStudent.getUsername() + SUFFIX);
            ssMailContent = ssMailContent.replace("[DATE]",now());

        MM.sendMail("Smartschoolmail", ssMailContent, smartschoolStudent.getEmail());
    }

    /**
     * Methode om mail te verzenden naar de admin met alle studenten die in smartschool moeten maar geen email hebben.
     * @throws MessagingException wordt gethrowed bij het mislukken van het vezenden van de mail
     * @throws IOException Wordt gethrowd als het inlezen van de file mislukt
     */
    public void sendSmartschoolLoggerMail() throws MessagingException, IOException {
        String mailContent = "Beste, \n " +
                "In bijlage de lijst van alle cursisten die in smartschool moeten aangemaakt worden, maar die geen email adres hebben.";
        File file = getFileSS();
        FileSystemResource  attachment = new FileSystemResource(file);
        MM.sendMailWithattachment("Smartschool users zonder email", mailContent, ADMINEMAIL, attachment);
    }


    /**
     * Methode om mail te versturen naar cursisten die geselecteerd zijn in de filter, met puntenlijst pdf al attachement
     * @param attachment pdf attachement
     * @param firstname firstname van cursist
     * @param lastname lastname van cursist
     * @param email email van cursist
     * @throws MessagingException wordt gethrowed bij het mislukken van het vezenden van de mail
     * @throws IOException Wordt gethrowd als het inlezen van de file mislukt
     */
    public void sendpuntenlijst(FileSystemResource attachment, String firstname, String lastname, String email) throws MessagingException, IOException {
        String mailcontent = emailRepository.findOne(3).getEmailContent().toString();
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");


        mailcontent = mailcontent.replace("[FIRSTNAME]", firstname);
        mailcontent = mailcontent.replace("[DATE]",now());
        MM.sendMailWithattachment("Puntenlijst", mailcontent,email, attachment);
    }

    /**
     * Methode om mail te verzenden naar de admin met alle studenten die in smartschool moeten maar geen email hebben.
     * @throws MessagingException wordt gethrowed bij het mislukken van het vezenden van de mail
     * @throws IOException Wordt gethrowd als het inlezen van de file mislukt
     */
    public void sendPuntenlijstloggerMail() throws MessagingException, IOException {
        String mailContent = "Beste, \n " +
                "In bijlage de lijst van alle cursisten waarvan de puntenlijst niet naar kon verzonden worden worden, Deze cursisten hebben geen email adres.";
        File file = getFilePuntenlijst();
        FileSystemResource  attachment = new FileSystemResource(file);
        MM.sendMailWithattachment("Cursisten zonder email", mailContent, ADMINEMAIL, attachment);
    }


    /**
     * Private method to get no smartschool email logger file
     * @return de no email ss students file
     * @throws FileNotFoundException Wordt gethrowed als er geen file staat in het filepath
     */
    private File getFileSS() throws FileNotFoundException {
        File file = new File(FILENAMESS);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + FILENAMESS + " was not found.");
        }
        return file;
    }

    /**
     * Private method to get no informat email logger file
     * @return de no email ss students file
     * @throws FileNotFoundException Wordt gethrowed als er geen file staat in het filepath
     */
    private File getFileInformat() throws FileNotFoundException {
        File file = new File(FILENAMEINFORMAT);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + FILENAMEINFORMAT + " was not found.");
        }
        return file;
    }

    /**
     * Private method to get no informat email logger file
     * @return de no email ss students file
     * @throws FileNotFoundException Wordt gethrowed als er geen file staat in het filepath
     */
    private File getFilePuntenlijst() throws FileNotFoundException {
        File file = new File("files/geenEmailPuntenlijst.txt");
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + FILENAMEINFORMAT + " was not found.");
        }
        return file;
    }



    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
}





