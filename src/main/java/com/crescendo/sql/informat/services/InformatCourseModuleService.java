package com.crescendo.sql.informat.services;

import com.crescendo.informat.models.*;
import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.informat.models.*;
import com.crescendo.sql.informat.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** InformatCourseModuleService
 * @author Groep 5
 */
@Service
@SuppressWarnings("Duplicates")
public class InformatCourseModuleService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private MainTeacherRepository mainTeacherRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private StudieGebiedRepository studieGebiedRepository;

    @Autowired
    InformatService informatService;

    @Autowired
    InformatStudentService informatStudentService;


    private Modules module = new Modules();
    private List<String> ids = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //This throws the exception, put in method to remove exception


    public InformatCourseModuleService() throws IOException {
    }

    /**
     * Alle courses en modules uit informat naar de databank pushen<br>
     * Er wordt ook een join table aangemaakt aangezien dit een many-to-many relatie is
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushCourseModule() throws IOException {
        moduleRepository.deleteAll();
        List<Course> courses = informatService.getCourseList();
        for (Course course : courses) {
            try {
                int lengteModuleArray = course.getModules().size();
                    if (course.getName().toUpperCase().contains("_SS")) {
                        moduleRepository.save(new HashSet<Modules>() {{
                            for (int i = 0 ; i < lengteModuleArray ; i++) {
                            add(new Modules(course.getModules().get(i).getId(), course.getModules().get(i).getExchangeCode(), course.getModules().get(i).getCode(), course.getModules().get(i).getModuleCode(), course.getModules().get(i).getName(), true, new HashSet<Courses>() {{
                                Courses courses1 = new Courses();
                                courses1.setId(course.getId());
                                courses1.setName(course.getName());
                                courses1.setNrOfRegistrations(course.getNrOfRegistrations());
                                courses1.setMaxRegistrations(course.getMaxRegistrations());
                                courses1.setStartDate(course.getStartDate());
                                courses1.setEndDate(course.getEndDate());
                                courses1.setSemester(course.getSemester());
                                courses1.setThirdPartyRef(course.getThirdPartyRef());
                                try{
                                    courses1.setMainTeachers(course.getMainTeacher().getId());
                                } catch (NullPointerException ex){

                                }

                                courses1.setDepartments(course.getDepartment().getId());
                                courses1.setLocations(course.getLocation().getId());
                                courses1.setStudiegebied(course.getDepartment().getArea());
                                add(courses1);
                            }}));
                        }}});
                    } else {
                        moduleRepository.save(new HashSet<Modules>() {{
                            for (int i = 0 ; i < lengteModuleArray ; i++) {
                                add(new Modules(course.getModules().get(i).getId(), course.getModules().get(i).getExchangeCode(), course.getModules().get(i).getCode(), course.getModules().get(i).getModuleCode(), course.getModules().get(i).getName(), false, new HashSet<Courses>() {{
                                    Courses courses2 = new Courses();
                                    courses2.setId(course.getId());
                                    courses2.setName(course.getName());
                                    courses2.setNrOfRegistrations(course.getNrOfRegistrations());
                                    courses2.setMaxRegistrations(course.getMaxRegistrations());
                                    courses2.setStartDate(course.getStartDate());
                                    courses2.setEndDate(course.getEndDate());
                                    courses2.setSemester(course.getSemester());
                                    courses2.setThirdPartyRef(course.getThirdPartyRef());
                                    try {
                                        courses2.setMainTeachers(course.getMainTeacher().getId());
                                    }catch (NullPointerException ex) {

                                    }
                                    courses2.setDepartments(course.getDepartment().getId());
                                    courses2.setLocations(course.getLocation().getId());
                                    courses2.setStudiegebied(course.getDepartment().getArea());
                                    add(courses2);
                                }}));
                            }}});
                    }
            } catch (NullPointerException ex) {
                System.out.println("error: " + ex.getLocalizedMessage());
            }
        }
    }

    /**
     * Alle mainteachers uit informat naar de databank pushen<br>
     * Many-to-one met courses
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushAllMainTeachers() throws IOException {
        mainTeacherRepository.deleteAll();
        List<Course> courses = informatService.getCourseList();
        for (Course course : courses) {
            try {
                Teacher teacher = new Teacher();
                MainTeachers mainTeachers = new MainTeachers();
                try {
                    String teacherId = course.getMainTeacher().getId();
                    teacher = informatService.getTeacherById(teacherId);
                } catch (NullPointerException ex) {
                    continue;
                }
                try{
                    mainTeachers.setPhonenumber(teacher.getPhone().get(0));
                }catch (IndexOutOfBoundsException | NullPointerException ex) {
                }
                try {
                    mainTeachers.setEmail(teacher.getEmail().get(0));
                } catch (IndexOutOfBoundsException | NullPointerException ex) {
                }
                mainTeachers.setId(teacher.getId());
                mainTeachers.setLastName(teacher.getLastName());
                mainTeachers.setFirstName(teacher.getFirstName());
                mainTeachers.setUsername(teacher.getFirstName()+"."+teacher.getLastName());
                mainTeachers.setCity(teacher.getAddress().getCity().toLowerCase());
                mainTeachers.setCountry(teacher.getAddress().getCountry());
                mainTeachers.setPoBox(teacher.getAddress().getPoBox());
                mainTeachers.setHouseNumber(teacher.getAddress().getNumber());
                mainTeachers.setPostalCode(teacher.getAddress().getPostalCode());
                mainTeachers.setStamNr(teacher.getStamNr());
                mainTeachers.setStreet(teacher.getAddress().getStreet());
                mainTeachers.setSex(teacher.getSex());
                mainTeacherRepository.save(mainTeachers);
            } catch (NullPointerException ex) {

            }
        }
    }

    /**
     * Alle departementen uit informat naar de databank pushen<br>
     * Many-to-one met courses
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushAllDepartments() throws IOException {
        departmentRepository.deleteAll();
        List<Course> courses = informatService.getCourseList();
        for (Course course : courses) {
            try {
                Departments departments = new Departments(course.getDepartment().getId(), course.getDepartment().getCode(), course.getDepartment().getName(), course.getDepartment().getNrAdmgrp(), course.getDepartment().getAdmgrp(), course.getDepartment().getEduCode(), course.getDepartment().getEduName(), course.getDepartment().getArea());
                departmentRepository.save(departments);
            } catch (NullPointerException ex) {
            }
        }
    }

    /**
     * Alle locaties waar courses plaatsvinden naar de databank pushen<br>
     * Many-to-one met courses
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushAllLocations() throws IOException {
        locationRepository.deleteAll();
        List<Course> courses = informatService.getCourseList();
        for (Course course : courses) {
            try {
                Locations locations = new Locations(course.getLocation().getId(), course.getLocation().getName());
                locationRepository.save(locations);
            } catch (NullPointerException ex) {
            }
        }
    }

    /**
     * Alle studiegebieden naar de databank pushen<br>
     * Many-to-one met courses
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushAllStudiegebieden() throws IOException {
        studieGebiedRepository.deleteAll();
        List<Course> courses = informatService.getCourseList();

        for (Course course : courses) {
            try {
                    Studiegebied studiegebied = new Studiegebied(course.getDepartment().getArea(), course.getDepartment().getCode());
                    studieGebiedRepository.save(studiegebied);

            } catch (NullPointerException ex) {
            }
        }
    }
}
