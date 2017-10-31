package com.crescendo.informat.services.responses;

import com.crescendo.informat.models.StudentHistory;

import java.util.List;
/** StudentHistoryResponse
 * @author Groep 5
 * @version 0.1
 */
public class StudentHistoryResponse {

    private List<StudentHistory> studentHistory;

    public StudentHistoryResponse() {
    }

    public List<StudentHistory> getStudentHistory() {
        return studentHistory;
    }

    public void setStudentHistory(List<StudentHistory> studentHistory) {
        this.studentHistory = studentHistory;
    }
}
