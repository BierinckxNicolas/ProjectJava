package com.crescendo.informat.services.responses;

import com.crescendo.informat.models.Course;
/** CourseIdResponse model
 * @author Groep 5
 * @version 0.1
 */
public class CourseIdResponse {

    private Course course;

    public CourseIdResponse() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
