package com.crescendo.informat.models;

import java.util.Date;
import java.util.List;

/**
 * Created by Gilles on 3/06/2017.
 */
public class Teacher {
    private String id;
    private String firstName;
    private String lastName;
    private String nickName;
    private Date dateOfBirth;
    private String sex;
    private String stamNr;
    private String rijksRegisterNr;
    private Address address;
    private List<String> email;
    private List<String> phone;

    public Teacher(String id, String firstName, String lastName, String nickName, Date dateOfBirth, String sex, String stamNr, String rijksRegisterNr, Address address, List<String> email, List<String> phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.stamNr = stamNr;
        this.rijksRegisterNr = rijksRegisterNr;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Teacher() {
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStamNr() {
        return stamNr;
    }

    public void setStamNr(String stamNr) {
        this.stamNr = stamNr;
    }

    public String getRijksRegisterNr() {
        return rijksRegisterNr;
    }

    public void setRijksRegisterNr(String rijksRegisterNr) {
        this.rijksRegisterNr = rijksRegisterNr;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex='" + sex + '\'' +
                ", stamNr='" + stamNr + '\'' +
                ", rijksRegisterNr='" + rijksRegisterNr + '\'' +
                ", address=" + address +
                ", email=" + email +
                ", phone=" + phone +
                '}';
    }
}
