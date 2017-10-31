package com.crescendo.informat.services.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** StudentGeneral
 * @author Groep 5
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentGeneral {
    private String Id;
    private String firstName;
    private String lastName;
    private String stamNr;
    private String insz;

    public StudentGeneral() {
    }

    public StudentGeneral(String id, String firstName, String lastName, String stamNr, String insz) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stamNr = stamNr;
        this.insz = insz;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getStamNr() {
        return stamNr;
    }

    public void setStamNr(String stamNr) {
        this.stamNr = stamNr;
    }

    public String getInsz() {
        return insz;
    }

    public void setInsz(String insz) {
        this.insz = insz;
    }

    @Override
    public String toString() {
        return "StudentGeneral{" +
                "Id='" + Id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stamNr='" + stamNr + '\'' +
                ", insz='" + insz + '\'' +
                '}';
    }
}
