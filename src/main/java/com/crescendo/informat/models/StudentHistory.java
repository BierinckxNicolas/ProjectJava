package com.crescendo.informat.models;

import java.util.Arrays;
import java.util.Date;

/** StudentHistory POJO
 * @author Groep 5
 */
public class StudentHistory {

    private String id;
    private Date registrationDate;
    private String schoolYear;
    private String result;
    private String remarks;
    private Course course;
    private Department departement;
    private Location location;
    private MainTeacher leerkracht;
    private Result resultExPeriod1;
    private Result resultExPeriod2;
    private Vv[]  vv;

    public StudentHistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Department getDepartement() {
        return departement;
    }

    public void setDepartement(Department departement) {
        this.departement = departement;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public MainTeacher getLeerkracht() {
        return leerkracht;
    }

    public void setLeerkracht(MainTeacher leerkracht) {
        this.leerkracht = leerkracht;
    }

    public Result getResultExPeriod1() {
        return resultExPeriod1;
    }

    public void setResultExPeriod1(Result resultExPeriod1) {
        this.resultExPeriod1 = resultExPeriod1;
    }

    public Result getResultExPeriod2() {
        return resultExPeriod2;
    }

    public void setResultExPeriod2(Result resultExPeriod2) {
        this.resultExPeriod2 = resultExPeriod2;
    }

    public Vv[] getVv() {
        return vv;
    }

    public void setVv(Vv[] vv) {
        this.vv = vv;
    }

    @Override
    public String toString() {
        return "StudentHistory{" +
                "id='" + id + '\'' +
                ", registrationDate=" + registrationDate +
                ", schoolYear='" + schoolYear + '\'' +
                ", result='" + result + '\'' +
                ", remarks='" + remarks + '\'' +
                ", course=" + course +
                ", departement=" + departement +
                ", location=" + location +
                ", leerkracht=" + leerkracht +
                ", resultExPeriod1=" + resultExPeriod1 +
                ", resultExPeriod2=" + resultExPeriod2 +
                ", vv=" + Arrays.toString(vv) +
                '}';
    }
}
