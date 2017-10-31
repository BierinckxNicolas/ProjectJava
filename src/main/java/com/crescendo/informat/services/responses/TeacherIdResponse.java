package com.crescendo.informat.services.responses;


import com.crescendo.informat.models.Teacher;

/** TeachterIdResponse
 * @author Groep 5
 * @version 0.1
 */
public class TeacherIdResponse {

    private Teacher teacher;

    public TeacherIdResponse() {
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
