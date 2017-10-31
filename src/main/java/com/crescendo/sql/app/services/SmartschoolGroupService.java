package com.crescendo.sql.app.services;

import com.crescendo.smartschool.models.ChildrenGroup;
import com.crescendo.smartschool.models.Group;
import com.crescendo.smartschool.models.GroupList;
import com.crescendo.smartschool.services.SmartschoolService;
import com.crescendo.sql.informat.models.Courses;
import com.crescendo.sql.informat.repositories.CourseRepository;
import com.crescendo.sql.utils.Suffix;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** SmartschoolGroupService
 * @author Groep 5
 */
@Service
@SuppressWarnings("Duplicates")
public class SmartschoolGroupService {
    @Autowired
    private SmartschoolService smartschoolService;
    @Autowired
    private CourseRepository courseRepository;

    private Suffix suffix = new Suffix();
    //SUFFIX
    private String SUFFIX = suffix.getSUFFIX();

    //JAAR VOOR GROEPN
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private int yearPlus = year + 1;
    private String stringYear = String.valueOf(year) + "-" + String.valueOf(yearPlus);

    //OVERKOEPELENDE JAAR+GROEP5 GROEP

    private String groepNaam = stringYear + "_" + SUFFIX;
    private String uniekeCode = groepNaam;
    private String desc = "leerlingen van groep 5";
    private String parentGroup = "001";

    //SEMESTER 1 GROEP
    private String semester1 = "Semester1";
    private String semester1Naam = stringYear + "_" + semester1;
    private String uniekeCodeS1 = semester1Naam;
    private String descSemester1 = "Groepen van 1e semester";
    private String parentCodeS1 = stringYear + "_" + semester1;

    //SEMESTER 2 GROEP
    private String semester2 = "Semester2";
    private String semester2Naam = stringYear + "_" + semester2;
    private String uniekeCodeS2 = semester2Naam;
    private String descSemester2 = "Groepen van 2e semester";
    private String parentCodeS2 = stringYear + "_" + semester2;

    //JAAR GROEP
    private String jaarGroep = "Jaar";
    private String jaarNaam = stringYear + "_" + jaarGroep;
    private String uniekeCodeJaar = jaarNaam;
    private String descJaar = "Groepen van het volledige jaar";
    private String parenCodeJaar = stringYear + "_" + jaarGroep;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SmartschoolGroupService() throws IOException {
    }


    /**
     * Alle specifiek groepen aanmaken in smartschool, deze groepen komen uit informat (waar eindigt op _SS)
     * Er wordt gecheckt of de naam in informat sem1, sem2 of iets anders bevate, en zet deze groep dan in de juiste parentgroep
     * De methode checkt ook of de groep al bestaat of niet.
     * @throws RemoteException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    public void addAllGroupsFromInformatCourses() throws RemoteException, ConfigurationException {
        List<String> codes = getAllGroups();
        List<Courses> smartschoolCourses = courseRepository.findByNameEndingWith("_SS");
        for (Courses course : smartschoolCourses) {
            if (course.getName().toLowerCase().contains("sem1") && !codes.contains(course.getThirdPartyRef())) {
                smartschoolService.saveGroup(course.getThirdPartyRef()+SUFFIX, course.getName().replace("_SS", ""), course.getThirdPartyRef()+SUFFIX, parentCodeS1, "");
            } else if (course.getName().toLowerCase().contains("sem2") && !codes.contains(course.getThirdPartyRef())) {
                smartschoolService.saveGroup(course.getThirdPartyRef()+SUFFIX, course.getName().replace("_SS", ""), course.getThirdPartyRef()+SUFFIX, parentCodeS2, "");
            } else if (!codes.contains(course.getThirdPartyRef())){
                smartschoolService.saveGroup(course.getThirdPartyRef()+SUFFIX, course.getName().replace("_SS", ""), course.getThirdPartyRef()+SUFFIX, parenCodeJaar, "");
            }
        }
    }

    /**
     * Eerst subgroepen aanmaken in smartschool, dewelke zijn: semester 1, semester 2 en jaar
     * checken of ze al bestaan.
     * @throws RemoteException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    public void addSubGroups() throws RemoteException, ConfigurationException {
        List<String> codes = getAllGroups();
        if (!(codes.contains(uniekeCodeS1))){
            smartschoolService.saveGroup(semester1Naam, descSemester1, uniekeCodeS1, uniekeCode, "");
        } else {
            logger.info(semester1Naam + " bestaat al.");
        }

        if (!(codes.contains(uniekeCodeS2))){
            smartschoolService.saveGroup(semester2Naam, descSemester2, uniekeCodeS2, uniekeCode, "");
        }else {
            logger.info(semester2Naam + " bestaat al.");
        }

        if (!(codes.contains(uniekeCodeJaar))){
            smartschoolService.saveGroup(jaarNaam, descJaar, uniekeCodeJaar, uniekeCode, "");
        } else {
            logger.info(jaarNaam + " bestaat al.");
        }
    }

    /**
     * De parentgroup in smartschool aanmaken met naam schooljaar_Groepnaam
     * checken of ze al bestaan.
     * @throws RemoteException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    public void addParentGroup() throws RemoteException, ConfigurationException {
        List<String> codes = getAllGroups();
        if (!(codes.contains(uniekeCode))){
            smartschoolService.saveGroup(groepNaam, desc, uniekeCode, parentGroup, "");
        } else {
            logger.info(groepNaam + " bestaat al.");
        }
    }

    /**
     * De methode Begint bij het hoogste niveau groepen en gaat kijken of er subgroepen voor bestaan,
     * Voor deze subgroepen gaat de methode dan weer kijken of er nog meer subgroepen bestaan enzoverder.
     * Alle codes van alle groepen worden opgeslagen in een lijst.
     * @return een lijst van all unieke codes van alle groepen.
     * @throws RemoteException als er iets misgaat met de getAllGroupsAndClasses call naar smartschool wordt deze gethrowed.
     * @throws ConfigurationException Wordt gethrowed als de config.propertie file niet gevonden kan worden
     */
    public List<String> getAllGroups() throws RemoteException, ConfigurationException {
        GroupList groupList = smartschoolService.getAllGroupsAndClasses();
        List<Group> groups = new ArrayList<>();
        List<ArrayList<ChildrenGroup>> subGroup = new ArrayList<>();
        List<ArrayList<ChildrenGroup>> subSubGroup = new ArrayList<>();
        List<ArrayList<ChildrenGroup>> specificGroup = new ArrayList<>();
        List<String> codes = new ArrayList<>();

        groups.addAll(groupList.getGroup());
        for (Group group : groups){
            codes.add(group.getCode());
            if (!(group.getChildren() == null)){
                subGroup.add(group.getChildren().getGroup());
            }
        }
        for (List<ChildrenGroup> chilgroups: subGroup) {
            for (ChildrenGroup child : chilgroups) {
                codes.add(child.getCode());
                if (!(child.getChildren() == null)){
                    subSubGroup.add(child.getChildren().getGroup());
                }
            }
        }
        for (List<ChildrenGroup> subgroups : subSubGroup) {
            for (ChildrenGroup sub : subgroups) {
                codes.add(sub.getCode());
                if (!(sub.getChildren() == null)){
                    specificGroup.add(sub.getChildren().getGroup());
                }
            }
        }
        for (List<ChildrenGroup> c : specificGroup) {
            for (ChildrenGroup c2 : c) {
                codes.add(c2.getCode());
            }
        }
        return codes;
    }


}
