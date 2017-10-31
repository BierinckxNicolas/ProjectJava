package com.crescendo.smartschool.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** Course POJO
 * @author Groep 5
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course
{
    private String name;

 //   private String mainTeacher;

 //   private String studentGroups;

    private String description;

    private String active;

 //   private String coTeachers;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
   /* public void setMainTeacher(String mainTeacher){
        this.mainTeacher = mainTeacher;
    }
    public String getMainTeacher(){
        return this.mainTeacher;
    }*/
 /*   public void setStudentGroups(String studentGroups){
        this.studentGroups = studentGroups;
    }
    public String getStudentGroups(){
        return this.studentGroups;
    }*/
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setActive(String active){
        this.active = active;
    }
    public String getActive(){
        return this.active;
    }
  /*  public void setCoTeachers(String coTeachers){
        this.coTeachers = coTeachers;
    }
    public String getCoTeachers(){
        return this.coTeachers;
    }*/

    @Override
    public String toString() {
        return "Course{" +
            "name='" + name + '\'' +
            ", mainTeacher='"  + '\'' +
            ", studentGroups='"  + '\'' +
            ", description='" + description + '\'' +
            ", active='" + active + '\'' +
            ", coTeachers='"  + '\'' +
            '}';
    }
}

