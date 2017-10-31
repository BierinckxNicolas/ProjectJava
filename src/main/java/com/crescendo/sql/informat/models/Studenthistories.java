package com.crescendo.sql.informat.models;


import javax.persistence.*;

/**
 * We gaan gene relatie met andere entiteiten leggen met de tabel, we hebben geprobeer een OneToMany te gebruiken samen met een hashmap<br>
 * We kregen telkens een hibernate error, wanneer we dit opzochten op het internet, vonden we veel post die zeiden dat het een bug was in the 5.x versie van hibernate<br>
 * Deze versie heeft veel open issues<br>
 * Dus om tijd te sparen hebben we gewoon de tabel zonder relaties aangemaakt, en we voeren de link gewoon uit in de java code in de business logic
 * @author Groep 5
 */
@Entity
@Table(name="informatstudenthistories", schema = "crescendo")
public class Studenthistories {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="schoolyear")
    private String schoolyear;

    @Column(name="result")
    private String result;

    @Column(name="informatstudents_id")
    private String studentId;

    @Column(name="informatcourses_id")
    private String courseId;

    @Column(name="informatstudents_stamnr")
    private String stamnr;


    public Studenthistories() {
    }


    public Studenthistories(String id, String schoolyear, String result, String studentId, String courseId, String stamnr) {
        this.id = id;
        this.schoolyear = schoolyear;
        this.result = result;
        this.studentId = studentId;
        this.courseId = courseId;
        this.stamnr = stamnr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(String schoolyear) {
        this.schoolyear = schoolyear;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStamnr() {
        return stamnr;
    }

    public void setStamnr(String stamnr) {
        this.stamnr = stamnr;
    }

    @Override
    public String toString() {
        return "Studenthistories{" +
                "id='" + id + '\'' +
                ", schoolyear='" + schoolyear + '\'' +
                ", result='" + result + '\'' +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", stamnr='" + stamnr + '\'' +
                '}';
    }
}
