package com.crescendo.sql.informat.models;

import com.crescendo.informat.models.MainTeacher;

import javax.persistence.*;

/**
 * We gaan gene relatie met andere entiteiten leggen met de tabel, we hebben geprobeer een OneToMany te gebruiken samen met een hashmap<br>
 * We kregen telkens een hibernate error, wanneer we dit opzochten op het internet, vonden we veel post die zeiden dat het een bug was in the 5.x versie van hibernate<br>
 * Deze versie heeft veel open issues<br>
 * Dus om tijd te sparen hebben we gewoon de tabel zonder relaties aangemaakt, en we voeren de link gewoon uit in de java code in de business logic
 * @author Groep 5
 */
@Entity
@Table(name="informatotherteachers", schema = "crescendo")
public class OtherTeachers {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="courseId")
    private String courseId;

    public OtherTeachers() {
    }

    public OtherTeachers(String id, String firstName, String lastName, String courseId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseId = courseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "OtherTeachers{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
