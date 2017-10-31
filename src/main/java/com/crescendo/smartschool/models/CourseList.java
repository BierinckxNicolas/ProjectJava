package com.crescendo.smartschool.models;

import java.util.ArrayList;
/** CourseList POJO
 * @author Groep 5
 */
public class CourseList {
    private ArrayList<Course> course;

    public ArrayList<Course> getCourse() {
        return this.course;
    }

    public void setCourse(ArrayList<Course> course) {
        this.course = course;
    }
}
