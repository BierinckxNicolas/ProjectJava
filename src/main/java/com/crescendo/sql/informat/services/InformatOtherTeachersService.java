package com.crescendo.sql.informat.services;


import com.crescendo.informat.models.Course;
import com.crescendo.informat.models.MainTeacher;
import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.informat.models.OtherTeachers;
import com.crescendo.sql.informat.repositories.OtherTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** InformatOtherTeachersService
 * @author Groep 5
 */
@Service
public class InformatOtherTeachersService {

    @Autowired
    InformatService informatService;

    @Autowired
    OtherTeacherRepository otherTeacherRepository;

    /**
     * Alle otherTeachers naar de databank pushen,
     * Deze heeft geen relatie met courses (was buggy, nog te herzien)
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushAlleOtherTeachers() throws IOException {
        otherTeacherRepository.deleteAll();
        List<Course> courses = informatService.getCourseList();
        OtherTeachers otherTeachers = new OtherTeachers();

        for (Course course : courses){
           List<MainTeacher> otherTeacherList = course.getOtherTeachers();
           for (MainTeacher otherTeacher : otherTeacherList){
               otherTeachers.setId(otherTeacher.getId());
               otherTeachers.setFirstName(otherTeacher.getFirstName());
               otherTeachers.setLastName(otherTeacher.getLastName());
               otherTeachers.setCourseId(course.getId());

               otherTeacherRepository.save(otherTeachers);
           }
        }
    }

}
