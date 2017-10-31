package com.crescendo.informat.services;

import com.crescendo.informat.models.*;
import com.crescendo.informat.services.responses.*;
import com.crescendo.informat.services.utils.Hmac;
import com.crescendo.informat.services.utils.InformatKey;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Bevat alle calls naar informat<br>
 * Omwille van de vele internal error tijdens development hebben we een fix gedeployed, <br>
 * waarbij de call een (parameter) max aantal keer wordt uitgevoerd.
 * @author Groep 5
 * @version 0.1
 */
@SuppressWarnings("FieldCanBeLocal")
@Service
public class InformatService {

    private PropertiesConfiguration conf = new PropertiesConfiguration("config.properties");
    private final String SCHOOLYEAR = conf.getProperty("SCHOOLYEAR").toString();
    private final String BASEURL = conf.getProperty("URLINFORMAT").toString();
    private final static int MAX_AANTAL_EXCEPTIONS = 5;

    private String publicKey;
    private String privateKey;
    private String instituteNr;
    private ZonedDateTime utc;
    private String timeStampMessage;
    private String timestampHeader;
    private String jaar;
    private String schoolyear;

    private String startDate;
    private String message;
    private String hash;
    private String authentication;



    private Long totaalAantalServerErrors = 0L;

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public InformatService() throws IOException, ConfigurationException {
    }

    /*
    *
    * #################################################################
    * API CALL METHODS
    * #################################################################
    *
    */

    /**
     * Alle student uit informat opvragen via api call<br>
     *<br>
     * initialize the /student api endpoint<br>
     * restTemplate aanmaken <br>
     * call {@link #connection(String)} om de header aan te maken<br>
     * HttpEntity object maken op de call te initiaten <br>
     * postForObject callen met de parameters: url, Httpentity en mapping class. <br>
     *
     * @return Lijst van alle studenten gemapped of studenten klasse {@link Student}
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<Student> getStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        int errorTeller = 0;
        String url = BASEURL + "/student";
        HttpHeaders headers = connection("/student");
        HttpEntity<?> entity = new HttpEntity(schoolyear, headers);
        while (errorTeller < MAX_AANTAL_EXCEPTIONS) {
            try {
                students = restTemplate.postForObject(url, entity, StudentResponse.class).getStudents();
                errorTeller = MAX_AANTAL_EXCEPTIONS + 1;
            } catch (HttpStatusCodeException e) {
                errorTeller++;
                totaalAantalServerErrors++;
                logger.error("Internal servererror tijdens methode getStudents(): " + e.getStatusCode());
            }
        }
        return students;
    }

    /**
     * Alle details van een specifieke student uit informat opvragen via api call<br>
     *<br>
     * initialize the /student/id api endpoint<br>
     * restTemplate aanmaken <br>
     * call {@link #connection(String)} om de header aan te maken<br>
     * HttpEntity object maken op de call te initiaten <br>
     * postForObject callen met de parameters: url, Httpentity en mapping class. <br>
     *
     * @param id id van de student
     * @return de informate van 1 student mapped op {@link StudentIdResponse}
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public Student getStudentById(String id) throws IOException {
        Student student = new Student();
        int errorTeller = 0;
        String url = BASEURL + "/student/" + id;
        HttpHeaders headers = connection("/student/" + id);
        HttpEntity<?> entity = new HttpEntity(schoolyear, headers);

        while (errorTeller < MAX_AANTAL_EXCEPTIONS) {
            try {
                student = restTemplate.postForObject(url,entity, StudentIdResponse.class).getStudent();
                errorTeller = MAX_AANTAL_EXCEPTIONS + 1;
            } catch (HttpStatusCodeException e) {
                errorTeller++;
                totaalAantalServerErrors++;
                logger.error("Internal servererror tijdens methode getStudentById(id): " + e.getStatusCode());
            }
        }
        return student;

    }

    /**
     * Alle courses uit informat opvragen via api call<br>
     *<br>
     * initialize the /course api endpoint<br>
     * restTemplate aanmaken <br>
     * call {@link #connection(String)} om de header aan te maken<br>
     * HttpEntity object maken op de call te initiaten <br>
     * postForObject callen met de parameters: url, Httpentity en mapping class. <br>
     *
     * @return Een lijst van alle courses mapped op {@link Course}
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<Course> getCourses() throws IOException {
        String url = BASEURL + "/course";
        List<Course> courses = new ArrayList<>();
        int errorTeller = 0;
        HttpHeaders headers = connection("/course");
        HttpEntity<?> entity = new HttpEntity(schoolyear, headers);
        while (errorTeller < MAX_AANTAL_EXCEPTIONS) {
            try {
                courses = restTemplate.postForObject(url,entity,CourseResponse.class).getCourses();
                errorTeller = MAX_AANTAL_EXCEPTIONS + 1;
            } catch (HttpStatusCodeException e) {
                errorTeller++;
                totaalAantalServerErrors++;
                logger.error("Internal servererror tijdens methode getCourses: " + e.getStatusCode());
            }
        }
        return courses;
    }

    /**
     * Alle details van een specifieke courses uit informat opvragen via api call<br>
     *<br>
     * initialize the /course/id api endpoint<br>
     * restTemplate aanmaken <br>
     * call {@link #connection(String)} om de header aan te maken<br>
     * HttpEntity object maken op de call te initiaten <br>
     * postForObject callen met de parameters: url, Httpentity en mapping class. <br>
     * @param id id van de course
     * @return de informatie van 1 courses mapped op {@link CourseIdResponse}
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public Course getCourseById(String id) throws IOException {
        Course course = new Course();
        int errorTeller = 0;
        String url = BASEURL + "/course/" + id;
        HttpHeaders headers = connection("/course/" + id);
        HttpEntity<?> entity = new HttpEntity(schoolyear, headers);
        while (errorTeller < MAX_AANTAL_EXCEPTIONS) {
            try {
                course = restTemplate.postForObject(url, entity, CourseIdResponse.class).getCourse();
                errorTeller = MAX_AANTAL_EXCEPTIONS + 1;
            } catch (HttpStatusCodeException e) {
                errorTeller++;
                totaalAantalServerErrors++;
                logger.error("Internal servererror tijdens methode getCourseById(id): " + e.getStatusCode());
            }
        }
        return course;

    }

    /**
     * Alle student history uit informat opvragen via api call<br>
     *<br>
     * initialize the /student/id/histor api endpoint<br>
     * restTemplate aanmaken <br>
     * call {@link #connection(String)} om de header aan te maken<br>
     * HttpEntity object maken op de call te initiaten <br>
     * postForObject callen met de parameters: url, Httpentity en mapping class. <br>
     * @param id id van de student
     * @param time het tijdstip van wanneer je die informatie wilt
     * @return list van studentenhistories van de gegeven student en tijd
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<StudentHistory> getStudentHistoryById(String id, String time) throws IOException {
        String url = BASEURL + "/student/" + id + "/history";
        int errorTeller = 0;
        List<StudentHistory> studentHistories = new ArrayList<>();
        HttpHeaders headers = connection("/student/" + id + "/history");
        startDate = "{'startDate':'" + time + "'}";
        HttpEntity<?> entity = new HttpEntity(startDate, headers);
        while (errorTeller < MAX_AANTAL_EXCEPTIONS) {
            try {
                studentHistories = restTemplate.postForObject(url,entity, StudentHistoryResponse.class).getStudentHistory();
                errorTeller = MAX_AANTAL_EXCEPTIONS + 1;
            } catch (HttpStatusCodeException e) {
                errorTeller++;
                totaalAantalServerErrors++;
                logger.error("Internal servererror tijdens methode getStudentHistoryById(id, schoolyear): " + e.getStatusCode());
            }
        }
        return studentHistories;
    }

    /**
     * Alle student history uit informat opvragen via api call<br>
     *<br>
     * initialize the /student/id/histor api endpoint<br>
     * restTemplate aanmaken <br>
     * call {@link #connection(String)} om de header aan te maken<br>
     * HttpEntity object maken op de call te initiaten <br>
     * postForObject callen met de parameters: url, Httpentity en mapping class. <br>
     * @param id de id van de student
     * @return  Lijst van studenthistories van de gegeven student van heel zijn schoolcarriere(alle jaren)
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<StudentHistory> getStudentHistoryById(String id) throws IOException {
        List<StudentHistory> studentHistories = new ArrayList<>();
        int errorTeller = 0;
        String url = BASEURL + "/student/" + id + "/history";
        HttpHeaders headers = connection("/student/" + id + "/history");
        HttpEntity<?> entity = new HttpEntity(startDate, headers);
        while (errorTeller < MAX_AANTAL_EXCEPTIONS) {
            try {
                studentHistories = restTemplate.postForObject(url,entity, StudentHistoryResponse.class).getStudentHistory();
                errorTeller = MAX_AANTAL_EXCEPTIONS + 1;
            } catch (HttpStatusCodeException e) {
                errorTeller++;
                totaalAantalServerErrors++;
                logger.error("Internal servererror tijdens methode getStudentHistoryById(id): " + e.getStatusCode());
            }
        }
        return studentHistories;
    }

    /**
     * Alle teachers uit informat opvragen via api call<br>
     *<br>
     * initialize the /teacher api endpoint<br>
     * restTemplate aanmaken <br>
     * call {@link #connection(String)} om de header aan te maken<br>
     * HttpEntity object maken op de call te initiaten <br>
     * postForObject callen met de parameters: url, Httpentity en mapping class. <br>
     * @return Een lijst van alle teacher mapped op {@link Teacher}
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<Teacher> getMainTeacher() throws IOException {
        List<Teacher> teachers = new ArrayList<>();
        int errorTeller = 0;
        String url = BASEURL + "/teacher";
        HttpHeaders headers = connection("/teacher");
        HttpEntity<?> entity = new HttpEntity(schoolyear, headers);
        while (errorTeller < MAX_AANTAL_EXCEPTIONS) {
            try {
                teachers = restTemplate.postForObject(url, entity, TeacherResponse.class).getTeachers();
                errorTeller = MAX_AANTAL_EXCEPTIONS + 1;
            } catch (HttpStatusCodeException e) {
                errorTeller++;
                totaalAantalServerErrors++;
                logger.error("Internal servererror tijdens methode getStudents(): " + e.getStatusCode());
            }
        }
        return teachers;
    }


    /**
     * Alle specifiek informatie van een teacher uit informat opvragen via api call<br>
     *<br>
     * initialize the /teacher/id/ api endpoint<br>
     * restTemplate aanmaken <br>
     * call {@link #connection(String)} om de header aan te maken<br>
     * HttpEntity object maken op de call te initiaten <br>
     * postForObject callen met de parameters: url, Httpentity en mapping class. <br>
     * @param id de id van de teacher
     * @return informatie van 1 teacher mapped op {@link TeacherResponse}
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public Teacher getTeacherById(String id) throws IOException {
        Teacher teacher = new Teacher();
        int errorTeller = 0;
        String url = BASEURL + "/teacher/" + id;
        HttpHeaders headers = connection("/teacher/" + id);
        HttpEntity<?> entity = new HttpEntity(schoolyear, headers);

        while (errorTeller < MAX_AANTAL_EXCEPTIONS) {
            try {
                teacher = restTemplate.postForObject(url,entity, TeacherIdResponse.class).getTeacher();
                errorTeller = MAX_AANTAL_EXCEPTIONS + 1;
            } catch (HttpStatusCodeException e) {
                errorTeller++;
                totaalAantalServerErrors++;
                logger.error("Internal servererror tijdens methode getStudentById(id): " + e.getStatusCode());
            }
        }
        return teacher;
    }

    /*
    *
    * #################################################################
    * METHOD WHICH REFERENCE TO API CALL METHODS
    * #################################################################
    *
    */

    /**
     * gedailleerde informatie van alle informatcursisten ophalen <br>
     * <br>
     * lege lijst aanmaken <br>
     * een lijst aanmaken van alle id's aan de hand van {@link #studentIdList()} <br>
     * methode  {@link #getStudentById(String)} aanroepen voor elke id en deze aan de lege lijst toevoegen <br>
     * HEAVY CALL!!
     * @return Lijst van alle studenten en alle details
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<Student> getStudentList() throws IOException {
        List<Student> studentList = new ArrayList<>();
        List<String> studentdIds = studentIdList();
        for(String studendId : studentdIds){
            studentList.add(getStudentById(studendId));
        }
        return studentList;
    }

    /**
     * Filter de grades op student naam<br>
     * <br>
     * methode {@link #getStudents()} aanroepen om een lijst te maken<br>
     * checken of the lastname en firstname gelijk zijn aan een item, is dit het geval, pak dan de id hiervan<br>
     * de id als parameter vezenden naar {@link #getStudentHistoryById(String, String)}
     *
     * @param firstName firstname van de student
     * @param lastName lastname van de student
     * @param time fhet tijdstip van wanneer je die informatie wilt
     * @return een lijst van studenthistories met de grades voor student met firstname + lastname
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<StudentHistory> getStudentGradeByName(String firstName, String lastName, String time) throws IOException {
        List<Student> studentList = getStudents();
        String id = "";
        for (Student student : studentList)
        {
            if(student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)){
                id = student.getId();
            }
        }
        return getStudentHistoryById(id,time);

    }

    /**
     * Alle courses ophalen in 1 locatie)
     * <br>
     * {@link #getCourses()} aanroepen en in een lijst steken<br>
     * nieuwe lijst aanmaken<br>
     * Check of de gegeven naam gelijks is aan een item in the course lijst, zoja, voeg deze toe aan de nieuwe lijst.
     * @param name locatienaam (vestiging)
     * @return Een lijst van alle courses op de gegeven locatie
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<Course> getCoursesAtLocation(String name) throws IOException {
        List<Course> courseList = getCourses();
        List<Course> courseLocation = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getLocation().getName().equals(name)){
                courseLocation.add(course);
            }
        }
        return courseLocation;
    }

    /**
     * gedailleerde informatie van alle informatcourses ophalen <br>
     * <br>
     * lege lijst aanmaken <br>
     * een lijst aanmaken van alle id's aan de hand van {@link #courseIdList()} <br>
     * methode  {@link #getCourseById(String)} aanroepen voor elke id en deze aan de lege lijst toevoegen <br>
     * HEAVY CALL!!
     * @return Een lijst van alle detail informatie van alle courses
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<Course> getCourseList() throws IOException {
        List<Course> courseList = new ArrayList<>();
        List<String> ids = courseIdList();
        for(String id : ids){
            courseList.add(getCourseById(id));
        }

        return courseList;
    }

    /**
     * Alle histories van alle studenten ophalen
     * list aanmaken van alle studentenIds
     * /student/id/history aanroepen met al deze id's volgens de getStudentHistoryById call
     * @param schoolyear schooljaar
     * @return Een lijst van een lijst van studentenhistories
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    public List<List<StudentHistory>> getAllStudentHistories(String schoolyear) throws IOException {
        List<String> studentIds = studentIdList();
        List<List<StudentHistory>> studentHistories = new ArrayList<>();
        for (String studentId : studentIds) {
            studentHistories.add(getStudentHistoryById(studentId, schoolyear));
        }
        return studentHistories;
    }

  /*  public List<Module> getAlleModules() throws IOException {
        List<String> ids = new ArrayList<>();
        List<Course> courses = getCourseList();
        List<List<Module>> modulesList= new ArrayList<>();
        List<Module> moduleList = new ArrayList<>();
        for (Course course : courses) {
            modulesList.add(course.getModules());
            for (List<Module> modules : modulesList) {
                for (Module module : modules){
                    if(!ids.contains(module.getName())){
                        ids.add(module.getName());
                        moduleList.add(module);
                    }

                }
            }
        }

        return moduleList;
    }
    */

    /**
     * Alle studenten dat ingeschreven zijn in een course met _SS<br>
     * Lijst aanmaken waar alle studentenIDs inzitten<br>
     * Lege lijst van student aanmaken, deze wordt later gevuld met alle student die smartschool nodig hebben<br>
     * lijst van courses gevuld aan de hand van {@link #getAllCoursesByStudent(String, String)}
     * @param schoolyear schooljaar
     * @return Lijst van studenten die ingeschreven zijn voor een smartschool cursus
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */

    public List<Student> getAllSmartschoolStudents(String schoolyear) throws IOException {
        List<String> studentIds = studentIdList();
        List<Student> smartschoolStudents = new ArrayList<>();

        String lastStudentId = "";

        for(String id : studentIds){
            List<Course> studentCourses = getAllCoursesByStudent(id,schoolyear);

            for(Course c : studentCourses){
                if(c.getName().contains("_SS")){
                    if(!lastStudentId.equals(id))
                    {
                        smartschoolStudents.add(getStudentById(id));
                        lastStudentId = id;
                    }
                }
            }
        }
        return smartschoolStudents;
    }

/*
    public List<Course> getSSCourse(String id, String schoolyear) throws IOException {
        List<StudentHistory> studentHistories = getStudentHistoryById(id,schoolyear);
        List<Course> presentCourses = new ArrayList<>();
        for(StudentHistory studentHistory : studentHistories) {
            if(studentHistory.getCourse().getName().contains("_SS")){
                presentCourses.add(getCourseById(studentHistory.getCourse().getId()));
            }
        }
        return presentCourses;
    }

  */
  /*  public List<Course> getAllSSCourses() throws IOException {
        List<Course> courseList = new ArrayList<>();
        List<String> ids = ssCourseIdList();
        for(String id : ids){
            courseList.add(getCourseById(id));
        }
        return courseList;
    }
    */

   /* public List<Module> getAllSSModules() throws IOException {
        List<Student> ssStudent = getAllSmartschoolStudents(SCHOOLYEAR);
        List<List<Course>> ssCoursesHigh = new ArrayList<>();
        List<String> moduleId = new ArrayList<>();

        int count =0;
        for (Student s : ssStudent){
            ssCoursesHigh.add(getSSCourse(s.getId(),SCHOOLYEAR));
        }

        List<List<Module>> ssModulesHigh = new ArrayList<>();
        List<Module> ssModuleLow = new ArrayList<>();

        for(List<Course> courses : ssCoursesHigh){
            for(Course c : courses){
                ssModulesHigh.add(c.getModules());
                for(List<Module> modules : ssModulesHigh){
                    for (Module module : modules){

                        if(!moduleId.contains(module.getId().toUpperCase().trim())){
                            ssModuleLow.add(module);
                            moduleId.add(module.getId().trim().toUpperCase());
                        }
                    }
                }
            }}

        return ssModuleLow;
    }
    */

  /*  public Map<String, List<StudentHistory>> getStudentHistoryList() throws IOException {
        Map<String, List<StudentHistory>> map = new HashMap<>();
        List<Student> students = getStudents();

        for (Student student : students) {

            List<StudentHistory> list = getStudentHistoryById(student.getId());
            map.put(student.getId(), list);
        }

        return map;
    }*/

    /*
    *
    * #################################################################
    * PRIVATE METHODS
    * #################################################################
    *
    */

    /**
     * {@link #getStudents()} aanroepen om een lijst van alle studentenIDs te krijgen
     * @return Een lijst van alle studenten ids
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    private List<String> studentIdList() throws IOException {
        List<Student> students = getStudents();

        return students.stream().map(Student::getId).collect(Collectors.toList());
    }

    /**
     * {@link #getCourses()}  aanroepen om een lijst van alle courseIds te krijgen
     * @return Een lijst van alle course ids
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    private List<String> courseIdList() throws IOException {
        List<Course> courses = getCourses();

        return courses.stream().map(Course::getId).collect(Collectors.toList());
    }

  /*  private List<String> ssCourseIdList() throws IOException {
        List<Course> courses = getCourses();
        List<Course> ssCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getName().contains("_SS")){
                ssCourses.add(course);
            }
        }

        return ssCourses.stream().map(Course::getId).collect(Collectors.toList());
    }
    */


    /**
     * Alle courses voor een student vanaf een gegeven tijdstip<br>
     * Een lijst aanmaken van studenthistories die word gevuld met de resultaten van de methode {@link #getStudentHistoryById(String, String)} ()}<br>
     * Een andere lijst aanmaken die all courses van elke student bevat
     * @param id id van de studenten waarvan je alle courses wil weten
     * @param schoolyear tijdstip vanaf wanneer je de courses wil weten
     * @return Een lijst van alle courses waar de student met id, voor geregistreerd is.
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    private List<Course> getAllCoursesByStudent(String id, String schoolyear) throws IOException {
        List<StudentHistory> studentHistory = getStudentHistoryById(id, schoolyear);
        List<Course> presentCourses = new ArrayList<>();
        for(StudentHistory s : studentHistory) {
            presentCourses.add(s.getCourse());
        }
        return presentCourses;
    }

    /**
     * De header informatie opzetten voor de http call naar een api endpointw<br>
     * <br>
     * Alle relevant informatie toevoegen aan de header
     *
     * @param endPoint de endpoint die je wil callen
     * @return de headers als HttpHeaders object
     * @throws IOException Wordt gethrowed als er een probleem is bij het ophalen van de url uit config {@link InformatKey}
     */
    private HttpHeaders connection(String endPoint) throws IOException {
        InformatKey sleutel = new InformatKey();
        publicKey = sleutel.PUBLIC_KEY();
        privateKey = sleutel.PRIVATE_KEY();
        instituteNr = conf.getProperty("INSTITUTE_NR").toString();
        String url = BASEURL + endPoint;

        utc = ZonedDateTime.now(ZoneOffset.UTC);
        timeStampMessage = utc.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        timestampHeader  = utc.toString();
        jaar = SCHOOLYEAR;
        schoolyear = "{'schoolYear':'" + jaar + "'}";

        message = "verb=POST"+"&timestamp="+timeStampMessage+"&url="+url+"&instellingsnr="+instituteNr;
        hash = Hmac.getHash(message,privateKey, "HmacSHA256");
        authentication = publicKey + ":" + hash;
        HttpHeaders headers = new HttpHeaders();

        headers.add("InstellingsNr", "128521");
        headers.add("Timestamp", timestampHeader);
        headers.add("Authentication", authentication);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    public Long getTotaalAantalServerErrors() {
        return totaalAantalServerErrors;
    }

    public void setTotaalAantalServerErrors(Long totaalAantalServerErrors) {
        this.totaalAantalServerErrors = totaalAantalServerErrors;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
