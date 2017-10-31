package com.crescendo.sql.app.services;

import com.crescendo.CrescendoApplication;
import com.crescendo.smartschool.models.ChildrenGroup;
import com.crescendo.smartschool.models.Group;
import com.crescendo.smartschool.models.GroupList;
import com.crescendo.smartschool.services.SmartschoolService;
import com.crescendo.sql.informat.models.Courses;
import com.crescendo.sql.informat.repositories.CourseRepository;
import com.crescendo.sql.utils.Suffix;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Micky on 20/06/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrescendoApplication.class)
@WebAppConfiguration
public class SmartschoolGroupServiceTest {

    private Suffix suffix = new Suffix();
    //SUFFIX
    private String SUFFIX = suffix.getSUFFIX();

    @Autowired
    private SmartschoolGroupService smartschoolGroupService;

    @Mock
    private CourseRepository courseRepository;

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


    @Mock
    SmartschoolService smartschoolService;

    @Autowired
    SmartschoolService smartschoolService1;

    public SmartschoolGroupServiceTest() throws IOException {
    }

    @Test
    public void addAllGroupsFromInformatCourses() throws Exception {
        List<String> codes = smartschoolGroupService.getAllGroups();
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
        Assert.assertNotNull(smartschoolCourses);
    }

    @Test
    public void addSubGroups() throws Exception {
        List<String> codes = smartschoolGroupService.getAllGroups();
        if (!(codes.contains(uniekeCodeS1))){
            smartschoolService.saveGroup(semester1Naam, descSemester1, uniekeCodeS1, uniekeCode, "");
        }
        if (!(codes.contains(uniekeCodeS2))){
            smartschoolService.saveGroup(semester2Naam, descSemester2, uniekeCodeS2, uniekeCode, "");
        }
        if (!(codes.contains(uniekeCodeJaar))){
            smartschoolService.saveGroup(jaarNaam, descJaar, uniekeCodeJaar, uniekeCode, "");
        }

        Assert.assertNotNull(smartschoolService1.getAllGroupsAndClasses());
    }

    @Test
    public void addParentGroup() throws Exception {
        List<String> codes = smartschoolGroupService.getAllGroups();
        if (!(codes.contains(uniekeCode))){
            smartschoolService.saveGroup(groepNaam, desc, uniekeCode, parentGroup, "");
        }
    }

    @Test
    public void getAllGroups() throws Exception {

        GroupList groupList = smartschoolService1.getAllGroupsAndClasses();
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
Assert.assertNotNull(codes);
    }

}