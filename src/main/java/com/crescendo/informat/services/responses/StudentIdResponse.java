package com.crescendo.informat.services.responses;

import com.crescendo.informat.models.Student;
/** StudentIdResponse
 * @author Groep 5
 * @version 0.1
 */
public class StudentIdResponse {

    private Student student;

    public StudentIdResponse() {
    }

    public StudentIdResponse(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}
