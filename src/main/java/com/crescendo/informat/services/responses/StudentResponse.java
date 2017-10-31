package com.crescendo.informat.services.responses;

import com.crescendo.informat.models.Student;

import java.util.List;
/** StudentResponse
 * @author Groep 5
 * @version 0.1
 */
public class StudentResponse {

    private List<Student> students;
    private List<Student> studentsDetail;

    public StudentResponse() {
    }

    public List<Student> getStudentsDetails(){
        return studentsDetail;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "students=" + students +
                '}';
    }
}
