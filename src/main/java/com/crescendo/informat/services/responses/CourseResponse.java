package com.crescendo.informat.services.responses;

import com.crescendo.informat.models.Course;

import java.util.List;
/** CourseResponse model
 * @author Groep 5
 * @version 0.1
 */
public class CourseResponse {
    private List<Course> courses;

    public CourseResponse() {
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CourseResponse{" +
                "courses=" + courses +
                '}';
    }
}
