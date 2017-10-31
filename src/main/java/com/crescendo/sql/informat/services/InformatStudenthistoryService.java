package com.crescendo.sql.informat.services;

import com.crescendo.informat.models.Student;
import com.crescendo.informat.models.StudentHistory;
import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.informat.models.Studenthistories;
import com.crescendo.sql.informat.repositories.FirstStudentRepository;
import com.crescendo.sql.informat.repositories.StudenthistoryRepository;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** InformatStudenthistoryService
 * @author Groep 5
 */
@Service
public class InformatStudenthistoryService {

    @Autowired
    private InformatService informatService;

    @Autowired
    private StudenthistoryRepository studenthistoryRepository;

    @Autowired
    private FirstStudentRepository firstStudentRepository;

    public List<String> users = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private PropertiesConfiguration conf = new PropertiesConfiguration("config.properties");
    private final String SCHOOLYEAR = conf.getProperty("SCHOOLYEAR").toString();

    public InformatStudenthistoryService() throws ConfigurationException {
    }

    /**
     * Pusht alle studenthistorie uit informat naar de databank.
     * heeft geen relatie met andere tabellen want hibernate deed moeilijk (Issue in onze hibernate version, nog herzien!)
     * (kan zware push worden!)
     * @throws IOException wordt gethrowed als de informat service niet kan aangesproken worden (kan url niet uit config halen)
     */
    @Transactional
    public void pushAllStudentHistories() throws IOException {
        studenthistoryRepository.deleteAll();
        List<Student> studentList = informatService.getStudents();
        List<String> studentIdList = new ArrayList<>();

        for (Student student : studentList) {
            studentIdList.add(student.getId());
        }
        for (String id : studentIdList) {
            List<StudentHistory> studentHistoryById = informatService.getStudentHistoryById(id);
            for (StudentHistory studentHistory : studentHistoryById) {
                if (studentHistory.getSchoolYear().equals(SCHOOLYEAR)){
                    Studenthistories studentHistories = new Studenthistories();
                    studentHistories.setId(studentHistory.getId());
                    studentHistories.setSchoolyear(studentHistory.getSchoolYear());
                    studentHistories.setResult(studentHistory.getResult());
                    studentHistories.setCourseId(studentHistory.getCourse().getId());
                    studentHistories.setStudentId(id);
                    studentHistories.setStamnr(firstStudentRepository.getStamNrById(id));
                    studenthistoryRepository.save(studentHistories);
                }
            }
        }
    }
}
