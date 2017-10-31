package com.crescendo.sql.app.controllers;

import com.crescendo.app.scheduling.ScheduledTasks;
import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.app.models.Statistic;
import com.crescendo.sql.app.repositories.StatisticRepository;
import com.crescendo.sql.informat.models.NewStudents;
import com.crescendo.sql.informat.models.SmartschoolStudents;
import com.crescendo.sql.informat.repositories.CourseRepository;
import com.crescendo.sql.informat.repositories.FirstStudentRepository;
import com.crescendo.sql.informat.repositories.NewStudentRepository;
import com.crescendo.sql.informat.repositories.SmartschoolStudentRepository;
import com.crescendo.sql.smartschool.models.Users;
import com.crescendo.sql.smartschool.repositories.UserRepository;
import com.crescendo.sql.smartschool.services.SmartschoolUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/** Statistic restcontroller
 * @author Groep 5
 */
@RestController
@RequestMapping("/api")
public class StatisticsController {
    @Autowired
    private SmartschoolStudentRepository smartschoolStudentRepository;
    @Autowired
    private NewStudentRepository newStudentRepository;
    @Autowired
    private ScheduledTasks scheduledTasks;
    @Autowired
    private StatisticRepository statisticRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private FirstStudentRepository firstStudentRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Api endpoint om het aantal server errors op te halen
     * @return aantal server errors + status code
     */
    @GetMapping(path = "/servererrors")
    public ResponseEntity<Long> getServerErrors(){
        Statistic statistic = statisticRepository.findOne(1);
        Long aantal = statistic.getAantal();
        return new ResponseEntity<Long>(aantal, HttpStatus.OK);
    }

    /**
     * Api endpoint om de sync duration op te halen
     * @return de sync duration + status code
     */
    @GetMapping(path = "/syncduration")
    public ResponseEntity<Long> getSyncDuration(){
        Statistic statistic = statisticRepository.findOne(3);
        Long aantal = statistic.getAantal();
        return new ResponseEntity<Long>(aantal, HttpStatus.OK);
    }

    /**
     * Api endpoint om het aantal nieuwe smartschooluser op te halen
     * @return aantal nieuwe smartschool users + status code
     */
    @GetMapping(path = "/newusers")
    public ResponseEntity<Integer> getNewSSUsers() {
        List<SmartschoolStudents> smartschoolStudents = smartschoolStudentRepository.findAll();
        int aantal = smartschoolStudents.size();
        return new ResponseEntity<Integer>(aantal, HttpStatus.OK);
    }

    /**
     * Api endpoint om het aantal nieuwe informatcursisten op te halen
     * @return aantal nieuwe informatcursisten + status code
     */
    @GetMapping(path = "/newcursisten")
    public ResponseEntity<Integer> getNewCursisten() {
        List<NewStudents> newStudents = newStudentRepository.findAll();
        int aantal = newStudents.size();
        return new ResponseEntity<Integer>(aantal,HttpStatus.OK);
    }

    /**
     * Api endpoint om het aantal smartschool courses op te halen
     * @return aantal smartschool groep + status code
     */
    @GetMapping(path = "/aantalsscourses")
    public ResponseEntity<Integer> getNumberOfSSCourses() {
        int aantal = courseRepository.getCourseWhereNameLikeSS();
        return new ResponseEntity<Integer>(aantal,HttpStatus.OK);
    }

    /**
     * Api endpoint om het tijdstip van laatste sync op te halen
     * @return tijdstip van laatste sync + status code
     */
    @GetMapping(path = "/lastsync", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getLastSync() {
        Statistic statistic = statisticRepository.findOne(4);
        String datum = statistic.getError();
        return new ResponseEntity<String>(datum,HttpStatus.OK);
    }

    /**
     * Api endpoint om het aantal informatcourses op te halen
     * @return aantal informat courses + status code
     */
    @GetMapping(path = "/aantalcourses")
    public ResponseEntity<Integer> getAantalCourses() {
        int aantal = courseRepository.getAantalCourses();
        return new ResponseEntity<Integer>(aantal,HttpStatus.OK);
    }

    /**
     * Api endpoint om het aantal cursisten in informat op te halen
     * @return aantal informatcursisten + status code
     */
    @GetMapping(path = "/aantalcursisten")
    public ResponseEntity<Integer> getAantalCursisten() {
        int aantal = firstStudentRepository.getAantalCursisten();
        return new ResponseEntity<Integer>(aantal,HttpStatus.OK);
    }

    /**
     * Api endpoint om het aantal smartschoolUsers op te halen
     * @return aantal smartschool users + status code
     */
    @GetMapping(path = "/aantalssusers")
    public ResponseEntity<Integer> getAantalSsUsers() {
        int aantalnotNew = userRepository.aantalSSUsers();
        int aantalNew = smartschoolStudentRepository.getAllNewSmartschoolUsers();
        int aantal = aantalnotNew + aantalNew;

        return new ResponseEntity<Integer>(aantal,HttpStatus.OK);
    }

    /**
     * Api endpoint om het aantal volle cursussen om te halen
     * @return aantal volle cursussen + status code
     */
    @GetMapping(path = "/aantalvollecourses")
    public ResponseEntity<Integer> getAantalFullCourses() {
        int aantal = courseRepository.getVolleCourses();
        return new ResponseEntity<Integer>(aantal,HttpStatus.OK);
    }

}
