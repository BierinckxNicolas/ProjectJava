package com.crescendo.sql.informat.repositories;

import com.crescendo.informat.models.Result;
import com.crescendo.informat.models.StudentHistory;
import com.crescendo.informat.services.InformatService;
import com.crescendo.sql.informat.DTO.StudentHistoryDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** StudenthistoryRepositoryImpl
 * Hierin wordt de query override voor de studenthistory repository,
 * Dit doen we omdat er problemen waren bij het gebruiken van @Query voor tabellen zonder relatie.
 * @author Groep 5
 */
@Repository
@SuppressWarnings("Duplicates")
public class StudenthistoryRepositoryImpl implements StudenthistoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<StudentHistoryDTO> findAllGrades(InformatService informatService) {
        String q = "SELECT s.first_name, s.last_name, s.id, h.schoolyear, c.name 'Coursename', d.admgrp, d.edu_name, CONCAT_WS(' ',t.first_name ,t.last_name) 'Teacher' , i.name 'Modulename', l.name 'Location', c.third_party_ref, c.semester, concat_ws(' ', s.street, s.house_number) 'Adress', CONCAT_WS(' ',s.postal_code, s.city) 'State', s.email, s.username 'username' \n" +
                "FROM informatstudenthistories h\n" +
                "INNER JOIN informatcourses c ON (c.id = h.informatcourses_id)\n" +
                "INNER JOIN informatlocations l ON (l.id = c.informatlocations_id)\n" +
                "JOIN firstinformatstudents s ON (s.id = h.informatstudents_id)\n" +
                "JOIN informatcourses_informatmodules ii ON (c.id = ii.course_id)\n" +
                "JOIN informatmodules i ON (ii.module_id = i.id)\n" +
                "JOIN informatdepartments d ON (c.informatdepartments_id = d.id)\n" +
                "LEFT JOIN informatmainteachers t ON (c.informatmainteachers_id = t.id)\n";
        Query query = entityManager.createNativeQuery(q);


        List<Object[]> resultList = query.getResultList();
        List<StudentHistoryDTO> studentHistoryDTOS = new ArrayList<StudentHistoryDTO>();

        // Punten live ophalen
        for (Object[] obj : resultList) {
            try {
                String result = "NB";
                Result resultExamPeriod1 = new Result();
                Result resultExamPeriod2 = new Result();
                String maxScorePeriod1 = "NB";
                String maxScorePeriod2 = "NB";


                for (StudentHistory h : informatService.getStudentHistoryById(obj[2].toString())) {

                    if (h.getCourse().getName().equals(obj[4].toString())) {
                        result = String.valueOf(h.getResult());
                        resultExamPeriod1 = h.getResultExPeriod1();
                        resultExamPeriod2 = h.getResultExPeriod2();
                    }
                }
                StudentHistoryDTO studentHistoryDTO = new StudentHistoryDTO();
                studentHistoryDTO.setMaxScorePeriod1(String.valueOf(resultExamPeriod1.getMaxScore()));
                studentHistoryDTO.setMaxScorePeriod2(String.valueOf(resultExamPeriod2.getMaxScore()));
                studentHistoryDTO.setPointCodePeriod1(String.valueOf(resultExamPeriod1.getPointcodes()));
                studentHistoryDTO.setPointCodePeriod2(String.valueOf(resultExamPeriod2.getPointcodes()));
                studentHistoryDTO.setFirstName(obj[0].toString());
                studentHistoryDTO.setLastName(obj[1].toString());
                studentHistoryDTO.setResult(result);
                studentHistoryDTO.setResultExPeriod1(String.valueOf(resultExamPeriod1.getScore()));
                studentHistoryDTO.setResultExPeriod2(String.valueOf(resultExamPeriod2.getScore()));
                studentHistoryDTO.setSchoolyear(obj[3].toString());
                studentHistoryDTO.setCoursname(obj[4].toString());
                studentHistoryDTO.setAdminGroup(obj[5].toString());
                studentHistoryDTO.setStudyArea(obj[6].toString());
                studentHistoryDTO.setLector(obj[7].toString());
                studentHistoryDTO.setModuleName(obj[8].toString());
                studentHistoryDTO.setLocation(obj[9].toString());
                studentHistoryDTO.setCursusCode(obj[10].toString());
                studentHistoryDTO.setSemester(obj[11].toString());
                studentHistoryDTO.setAdress(obj[12].toString());
                studentHistoryDTO.setState(obj[13].toString());
                studentHistoryDTO.setEmail(obj[14].toString());
                studentHistoryDTO.setUsername(obj[15].toString());
                studentHistoryDTOS.add(studentHistoryDTO);
            } catch (IOException e) {
                logger.error(obj[0].toString() + obj[1].toString() + " Is niet opgehaald");
            }
        }
        return studentHistoryDTOS;
    }

    public List<StudentHistoryDTO> findAllPuntenLijst(InformatService informatService) {
        String q = "SELECT s.first_name, s.last_name, h.result, h.schoolyear, c.name 'Coursename', d.admgrp, d.edu_name, CONCAT_WS(' ',t.first_name ,t.last_name) 'Teacher' , i.name 'Modulename', l.name 'Location', c.third_party_ref, c.semester, concat_ws(' ', s.street, s.house_number) 'Adress', CONCAT_WS(' ',s.postal_code, s.city) 'State', s.email, s.username 'username' \n" +
                "FROM informatstudenthistories h\n" +
                "INNER JOIN informatcourses c ON (c.id = h.informatcourses_id)\n" +
                "INNER JOIN informatlocations l ON (l.id = c.informatlocations_id)\n" +
                "JOIN firstinformatstudents s ON (s.id = h.informatstudents_id)\n" +
                "JOIN informatcourses_informatmodules ii ON (c.id = ii.course_id)\n" +
                "JOIN informatmodules i ON (ii.module_id = i.id)\n" +
                "JOIN informatdepartments d ON (c.informatdepartments_id = d.id)\n" +
                "LEFT JOIN informatmainteachers t ON (c.informatmainteachers_id = t.id)\n";
        Query query = entityManager.createNativeQuery(q);
        /*query.setParameter(1, firstname);
        query.setParameter(2, lastname);*/

        List<Object[]> resultList = query.getResultList();
        List<StudentHistoryDTO> studentHistoryDTOS = new ArrayList<StudentHistoryDTO>();

        // Punten live ophalen
        for (Object[] obj : resultList) {
            StudentHistoryDTO studentHistoryDTO = new StudentHistoryDTO();

            studentHistoryDTO.setFirstName(obj[0].toString());
            studentHistoryDTO.setLastName(obj[1].toString());
            studentHistoryDTO.setResult(obj[2].toString());
            studentHistoryDTO.setSchoolyear(obj[3].toString());
            studentHistoryDTO.setCoursname(obj[4].toString());
            studentHistoryDTO.setAdminGroup(obj[5].toString());
            studentHistoryDTO.setStudyArea(obj[6].toString());
            studentHistoryDTO.setLector(obj[7].toString());
            studentHistoryDTO.setModuleName(obj[8].toString());
            studentHistoryDTO.setLocation(obj[9].toString());
            studentHistoryDTO.setCursusCode(obj[10].toString());
            studentHistoryDTO.setSemester(obj[11].toString());
            studentHistoryDTO.setAdress(obj[12].toString());
            studentHistoryDTO.setState(obj[13].toString());
            studentHistoryDTO.setEmail(obj[14].toString());
            studentHistoryDTO.setUsername(obj[15].toString());
            studentHistoryDTOS.add(studentHistoryDTO);
        }
        return studentHistoryDTOS;
    }

    public List<StudentHistoryDTO> filteredCourse (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getCoursname().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredName (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            String name = s.getFirstName() + " " + s.getLastName();
            if(name.toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredSchoolyear (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getSchoolyear().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredAdminGroup (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            System.out.println("Admingroup :" + s.getAdminGroup() + " Filter :" + filter);
            if(s.getAdminGroup().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredStudyArea (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getStudyArea().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredLector (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getLector().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredModuleName (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getModuleName().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredLocation (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getLocation().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredThirdPartyRef (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getCursusCode().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredSemester (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getSemester().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    public List<StudentHistoryDTO> filteredUsername (List<StudentHistoryDTO> lijst, String filter){
        List<StudentHistoryDTO> newList = new ArrayList<>();
        for(StudentHistoryDTO s : lijst){
            if(s.getUsername().toLowerCase().contains(filter)){
                newList.add(s);
            }
        }
        return newList;
    }

    @Override
    public List<StudentHistoryDTO> findHistoriesByFirstNameAndLastName(String firstname, String lastname) {
        return null;
    }
}
