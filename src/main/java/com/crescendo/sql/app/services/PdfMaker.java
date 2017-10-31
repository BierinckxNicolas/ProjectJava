package com.crescendo.sql.app.services;

import com.crescendo.sql.app.repositories.EmailRepository;

import com.crescendo.sql.informat.DTO.StudentHistoryDTO;
import com.crescendo.sql.informat.models.Studiegebied;

import com.crescendo.sql.informat.repositories.StudenthistoryRepositoryImpl;
import com.crescendo.sql.informat.repositories.StudieGebiedRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import java.io.*;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



/** PdfMakerService
 * @author Groep 5
 */
@SuppressWarnings("Duplicates")
@Service
public class PdfMaker {

    @Autowired
    EmailRepository emailRepository;
    @Autowired
    StudenthistoryRepositoryImpl studenthistoryRepository;
    @Autowired
    StudieGebiedRepository studieGebiedRepository;
    @Autowired
    SendMail mail;

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Methode om pdfs aan te maken voor de gegevens studenhistories, de pdfs worden opgeslagen in een directory op hoogste niveau,<br>
     * Deze methode gaat ook ineens checken of de cursist een email heeft, als dit niet het geval is wordt deze naar een file geschreven.<br>
     * Deze zal dan doorgestuurd worden naar de admin
     * @param grades de studenthistories met live grades
     * @throws IOException Wordt gethrowed als de config file niet gelezen kan worden
     * @throws DocumentException Wordt gethrowed als de xml mail file niet gelezen kan worden
     */
    public void createpdf(List<StudentHistoryDTO> grades) throws IOException, DocumentException {
        File fileExist = new File("files/geenEmailPuntenlijst.txt");
        if (fileExist.exists())
        {
            //delete if exists
            fileExist.delete();
        }
        File directory = new File("puntenPdfs");
        FileUtils.cleanDirectory(directory);
        List<String> cursistnamen = new ArrayList<>();
        for (int i = 0; i < grades.size(); i++) {

            List<StudentHistoryDTO> tempList = studenthistoryRepository.filteredName(grades, grades.get(i).getFirstName().toLowerCase() + " " + grades.get(i).getLastName().toLowerCase());
            String endContent = emailRepository.findOne(4).getEmailContent().toString();
            String pathname = "puntenPdfs/" + grades.get(i).getFirstName() + "_" + grades.get(i).getLastName() + ".pdf";
            Image img = Image.getInstance("mailImages/LogoCvo.jpg");
            img.scalePercent(25, 25);
            Image footerImg = Image.getInstance("mailImages/CvoFooter.jpg");
            footerImg.scalePercent(60,60);
            PdfPTable infoTable = new PdfPTable(4);
            PdfPCell imgCell = new PdfPCell(img);
            imgCell.setRowspan(4);
            imgCell.setColspan(2);
            imgCell.setBorder(Rectangle.NO_BORDER);
            infoTable.setWidthPercentage(100);
            infoTable.addCell(imgCell);
            PdfPTable pointsTable = new PdfPTable(3);
            pointsTable.setWidthPercentage(100);
            pointsTable.setSpacingBefore(30);
            pointsTable.setSpacingAfter(30);
            PdfPCell dateCellText = new PdfPCell(new Phrase("Datum van vezending: "));
            dateCellText.setBorder(Rectangle.NO_BORDER);
            PdfPCell dateCell = new PdfPCell(new Phrase(now().toString()));
            dateCell.setBorder(Rectangle.NO_BORDER);
            PdfPCell cursistCellText = new PdfPCell(new Phrase("Cursist:"));
            cursistCellText.setBorder(Rectangle.NO_BORDER);
            PdfPCell cursistCell = new PdfPCell(new Phrase(tempList.get(0).getFirstName() + " " + tempList.get(0).getLastName()));
            cursistCell.setBorder(Rectangle.NO_BORDER);
            PdfPCell adressCellText = new PdfPCell(new Phrase("Adres:"));
            adressCellText.setBorder(Rectangle.NO_BORDER);
            PdfPCell adressCell = new PdfPCell(new Phrase(tempList.get(0).getAdress()));
            adressCell.setBorder(Rectangle.NO_BORDER);
            PdfPCell schoolyearCellText = new PdfPCell(new Phrase("Schooljaar:"));
            schoolyearCellText.setBorder(Rectangle.NO_BORDER);
            PdfPCell schoolyearCell = new PdfPCell(new Phrase(tempList.get(0).getSchoolyear()));
            schoolyearCell.setBorder(Rectangle.NO_BORDER);

            infoTable.addCell(dateCellText);
            infoTable.addCell(dateCell);
            infoTable.addCell(cursistCellText);
            infoTable.addCell(cursistCell);
            infoTable.addCell(adressCellText);
            infoTable.addCell(adressCell);
            infoTable.addCell(schoolyearCellText);
            infoTable.addCell(schoolyearCell);

            List<Studiegebied> studiegebieden = studieGebiedRepository.findAll();
            String studieType = "";


            for (int j = 0; j < tempList.size(); j++) {
                File f = new File(pathname);
                double periodPoint1 = Double.parseDouble(tempList.get(j).getResultExPeriod1().toString());
                double maxPoint1 = Double.parseDouble(tempList.get(j).getMaxScorePeriod1().toString());
                double periodPoint2 = Double.parseDouble(tempList.get(j).getResultExPeriod2().toString());
                double maxPoint2 = Double.parseDouble(tempList.get(j).getMaxScorePeriod2().toString());
                double percentagePoint1 = (periodPoint1 / maxPoint1) * 100;
                double percentagePoint2 = (periodPoint2 / maxPoint2) * 100;
                Font font = new Font();
                font.setColor(BaseColor.RED);

                String moduleName = tempList.get(j).getModuleName();
                String points = ": " + periodPoint1 + " / " + maxPoint1;
                String points2 = ": " + periodPoint2 + " / " + maxPoint2;

                String geslaagd = "GESLAAGD";
                String nietGeslaagd = "NIET GESLAAGD";
                String vrijstelling = "VRIJSTELLING";
                String nietDeelgenomen = "NIET DEELGENOMEN";
                String deelgenomen = "DEELGENOMEN";

                String percentagePoint1Text = percentagePoint1 + "%";
                String percentagePoint2Text = percentagePoint2 + "%";
                PdfPCell moduleCell = new PdfPCell(new Phrase(moduleName));
                moduleCell.setPadding(5);


                for (int z = 0; z < studiegebieden.size(); z++) {
                    if (studiegebieden.get(z).getName().equals(tempList.get(j).getStudyArea())) {
                        studieType = studiegebieden.get(z).getType();
                    }
                }
                if (studieType.equals("SVWO")) {
                    if (tempList.get(j).getResult().equals("NG")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(nietGeslaagd));
                        pointsCell.setColspan(2);
                        pointsCell.setPadding(5);
                        pointsTable.addCell(pointsCell);

                    } else if (tempList.get(j).getResult().equals("NGU")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(nietGeslaagd));
                        pointsCell.setPadding(5);
                        pointsTable.addCell(pointsCell);


                    } else if (tempList.get(j).getResult().equals("G")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(geslaagd));
                        pointsCell.setPadding(5);
                        pointsTable.addCell(pointsCell);

                    } else if (tempList.get(j).getResult().equals("VR")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(vrijstelling));
                        pointsCell.setPadding(5);
                        pointsCell.setColspan(2);
                        pointsTable.addCell(pointsCell);

                    } else if (tempList.get(j).getResult().equals("GR")) {
                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase("GEEN RESULTAAT"));
                        pointsCell.setColspan(2);
                        pointsCell.setPadding(5);
                        pointsTable.addCell(pointsCell);
                    }

                } else if (studieType.equals("SLO") || studieType.equals("HBO5")) {

                    if (tempList.get(j).getPointCodePeriod1().equals("ND") && tempList.get(j).getResult().equals("NG")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(nietDeelgenomen));
                        pointsCell.setColspan(2);
                        pointsCell.setPadding(5);
                        pointsTable.addCell(pointsCell);

                    } else if (tempList.get(j).getPointCodePeriod1().equals("D") && tempList.get(j).getResult().equals("NG")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(nietGeslaagd + points));
                        PdfPCell percentageCell = new PdfPCell(new Phrase(percentagePoint1Text, font));
                        percentageCell.setHorizontalAlignment(2);
                        pointsCell.setPadding(5);
                        pointsTable.addCell(pointsCell);
                        pointsTable.addCell(percentageCell);


                    } else if (tempList.get(j).getPointCodePeriod1().equals("D") && tempList.get(j).getResult().equals("NGU")) {
                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(nietGeslaagd + " (Uitgesteld)" + points));
                        pointsCell.setPadding(5);
                        PdfPCell percentageCell = new PdfPCell(new Phrase(percentagePoint1Text, font));
                        percentageCell.setHorizontalAlignment(2);
                        pointsTable.addCell(pointsCell);
                        pointsTable.addCell(percentageCell);

                        if (studieType.equals("HBO5")) {
                            PdfPCell pointsCell2 = new PdfPCell(new Phrase(points2));
                            pointsCell2.setPadding(5);
                            PdfPCell percentageCell2 = new PdfPCell(new Phrase(percentagePoint2Text));
                            percentageCell.setHorizontalAlignment(2);
                            pointsTable.addCell(pointsCell2);
                            pointsTable.addCell(percentageCell2);
                        }

                    } else if (tempList.get(j).getPointCodePeriod1().equals("ND") && tempList.get(j).getResult().equals("NGU")) {
                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(nietGeslaagd + " (Uitgesteld)" + points));
                        pointsCell.setPadding(5);
                        PdfPCell percentageCell = new PdfPCell(new Phrase(percentagePoint1Text, font));
                        percentageCell.setHorizontalAlignment(2);
                        pointsTable.addCell(pointsCell);
                        pointsTable.addCell(percentageCell);

                    } else if (tempList.get(j).getPointCodePeriod1().equals("D") && tempList.get(j).getResult().equals("G")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(geslaagd + points));
                        pointsCell.setPadding(5);
                        pointsTable.addCell(pointsCell);
                        PdfPCell percentageCell = new PdfPCell(new Phrase(percentagePoint1Text));
                        percentageCell.setHorizontalAlignment(2);
                        pointsTable.addCell(percentageCell);


                    } else if (tempList.get(j).getResult().equals("VR")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(vrijstelling));
                        pointsCell.setPadding(5);
                        pointsCell.setColspan(2);
                        pointsTable.addCell(pointsCell);


                    } else if (tempList.get(j).getPointCodePeriod1().equals("STOP") && tempList.get(j).getResult().equals("GR") || tempList.get(j).getPointCodePeriod1().equals("STOP")) {

                        pointsTable.addCell(moduleCell);
                        PdfPCell pointsCell = new PdfPCell(new Phrase(nietDeelgenomen + "(GESTOPT)"));
                        pointsCell.setPadding(5);
                        pointsCell.setColspan(2);
                        pointsTable.addCell(pointsCell);
                    }
                }
                pointsTable.addCell(moduleCell);
                PdfPCell pointsCell = new PdfPCell(new Phrase("GEEN RESULTAAT"));
                pointsCell.setPadding(5);
                pointsCell.setColspan(2);
                pointsTable.addCell(pointsCell);
            }

            OutputStream file = new FileOutputStream(new File(pathname));

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();


            //  document.add(img);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(infoTable);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(pointsTable);

            InputStream is = new ByteArrayInputStream(endContent.getBytes());


            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
            document.add(footerImg);
            document.close();
            file.close();
            FileSystemResource puntenbijlage = new FileSystemResource(pathname);
            if (grades.get(i).getEmail().contains("@")) {
                if (!(cursistnamen.contains(grades.get(i).getFirstName() + " " + grades.get(i).getLastName()))) {
                    try {
                        mail.sendpuntenlijst(puntenbijlage, grades.get(i).getFirstName(), grades.get(i).getLastName(), grades.get(i).getEmail());
                        cursistnamen.add(grades.get(i).getFirstName() + " " + grades.get(i).getLastName());
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                makeNoEmailFile(grades.get(i));
            }
            tempList.clear();
        }
        try {
            sendAdminMail();
        } catch (MessagingException e) {
            logger.error("Probleem bij het versturen van de mail naar de admin");
        }
    }


    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    /**
     * File maken van alle cursisten die geen email hebben.
     * @param history studenthistorie
     * @throws IOException Wordt gethrowed als de methode de file niet kan aanmaken
     */
    private void makeNoEmailFile(StudentHistoryDTO history) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("files/geenEmailPuntenlijst.txt", true));
        try {
            String content = "Student " + history.getFirstName() + " " + history.getLastName() + " heeft geen puntenlijst aangekregen \n" +
                    "Informatie cursist: \n" +
                    "Voornaam: " + history.getFirstName() + "\n" +
                    "Achternaam: " + history.getLastName() + "\n" +
                    "Adres: " + history.getAdress() + "\n" +
                    "\n" +
                    "\n" +
                    "################################################" +
                    "\n" +
                    "\n";
            bw.write(content);
        } catch (IOException ioe) {
            logger.error("Er is een error opgetreden bij het aanmaken van de no email puntenlijst logfile" + ioe.getLocalizedMessage());
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
     * Mail vezenden naar de admin met de file waarin alle cursisten zonder email in staan.
     * @throws IOException Error bij schrijven naar de file
     * @throws MessagingException Wordt gethrowed als er iets fout gaat bij het versturen van de email.
     */
    private void sendAdminMail() throws IOException, MessagingException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("files/geenEmailPuntenlijst.txt"));
            if (!(br.readLine() == null)) {
                mail.sendPuntenlijstloggerMail();
                br.close();
            }
        } catch (FileNotFoundException ex) {
            logger.info("de geenEmailPuntenlijs.txt file is niet gevonden, er zijn dus geen cursisten zonder email adres waar een email naar verzonden moet worden");
        }
    }

}