package com.crescendo.informat.services;

import com.crescendo.informat.models.Course;
import com.crescendo.informat.models.Student;
import com.crescendo.informat.models.StudentHistory;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mathias on 23-5-2017.
 */
public class InformatServiceTest {
    InformatService informatService = mock(InformatService.class);
    @Test
    public void getStudents() throws Exception {

        Student tester = new Student();
        tester.setFirstName("Testman");
        tester.setLastName("McTest");
        List<Student> students = Arrays.asList(tester);

        when(informatService.getStudents()).thenReturn(students);
        assertEquals(1,informatService.getStudents().size());
    }


    @Test
    public void getCourses() throws Exception {
        Course testCourse = new Course();
        testCourse.setName("test course");
        List<Course> courses = Arrays.asList(testCourse);

        when(informatService.getCourses()).thenReturn(courses);
        assertEquals(1,informatService.getCourses().size());
    }


    @Test
    public void getCourseById() throws Exception {
        Course testCourse = new Course();
        testCourse.setName("test course");

        when(informatService.getCourseById("0000")).thenReturn(testCourse);
        assertEquals(testCourse,informatService.getCourseById("0000"));
    }


    @Test
    public void getStudentList() throws Exception {
        Student tester = new Student();
        tester.setFirstName("Testman");
        tester.setLastName("get student met meer details");
        List<Student> students = Arrays.asList(tester);

        when(informatService.getStudents()).thenReturn(students);
        assertEquals(1,informatService.getStudents().size());

    }

    @Test
    public void getStudentGradeByName() throws Exception {
        Student tester = new Student();
        tester.setFirstName("Testman");
        tester.setLastName("McTest");
        tester.setId("0000");

        List<StudentHistory> studentHistory = informatService.getStudentHistoryById(tester.getId(),"2016/01/01");

        when(informatService.getStudentGradeByName(tester.getFirstName(),tester.getLastName(),"2016/01/01")).thenReturn(studentHistory);
        assertEquals(studentHistory, informatService.getStudentGradeByName(tester.getFirstName(), tester.getLastName(), "2016/01/01"));
    }

    @Test
    public void getCoursesAtLocation() throws Exception {
        Course testCourse = new Course();
        testCourse.setName("test course");
        List<Course> testCourseLocation = Arrays.asList(testCourse);
        when(informatService.getCoursesAtLocation("Test locatie")).thenReturn(testCourseLocation);
        assertEquals(testCourseLocation,informatService.getCoursesAtLocation("Test locatie"));
    }

    @Test
    public void getCourseList() throws Exception {
        Course testCourse = new Course();
        testCourse.setName("test course");
        List<Course> testCourseList = Arrays.asList(testCourse);
        when(informatService.getCourseList()).thenReturn(testCourseList);
        assertEquals(testCourseList,informatService.getCourseList());
    }

    @Test
    public void getStudentById() throws Exception {
        Student tester = new Student();
        tester.setFirstName("Testman");
        tester.setLastName("McTest");

        when(informatService.getStudentById("0000")).thenReturn(tester);
        assertEquals(tester,informatService.getStudentById("0000"));
    }


}
