package com.crescendo.informat.services.responses;

import com.crescendo.informat.models.Teacher;

import java.util.List;

/** TeacherResponse
 * @author Groep 5
 * @version 0.1
 */
public class TeacherResponse {

    private List<Teacher> teachers;
    private List<Teacher> teachersDetail;

    public TeacherResponse() {
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachersDetail() {
        return teachersDetail;
    }

    public void setTeachersDetail(List<Teacher> teachersDetail) {
        this.teachersDetail = teachersDetail;
    }

    @Override
    public String toString() {
        return "TeacherResponse{" +
                "teachers=" + teachers +
                ", teachersDetail=" + teachersDetail +
                '}';
    }
}
