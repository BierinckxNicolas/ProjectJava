package com.crescendo.sql.informat.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Dit is een mapped supper class zodat alle tabellen die deze structuur nodig hebben ervan kunnen extenden
 * @author Groep 5
 */
@MappedSuperclass
public class Students {

    @Id
    @Column(name = "id")
    private String id; // Informat ID

    @Column(name="firstName")
    private String firstName; // Firstname

    @Column (name="lastName")
    private String lastName; // lastname

    @Column (name="stamNr")
    private String stamNr; // stamnr = Smartschool id

    @Column (name="username", length = 93)
    private String username;

    @Column (name="email")
    private String email;

    @Column (name="phonenumber")
    private String phonenumber;

    @Column (name="sex")
    private String sex;

    @Column (name="country")
    private String country;

    @Column (name="city")
    private String city;

    @Column (name="street")
    private String street;

    @Column (name="poBox")
    private String poBox;

    @Column (name="postalCode")
    private String postalCode;

    @Column (name="houseNumber")
    private String houseNumber;


    public Students(String id, String firstName, String lastName, String stamNr, String username, String email, String phonenumber, String sex, String country, String city, String street, String poBox, String postalCode, String houseNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stamNr = stamNr;
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.street = street;
        this.poBox = poBox;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
    }

    public Students() {
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

    public String getStamNr() {
        return stamNr;
    }

    public void setStamNr(String stamNr) {
        this.stamNr = stamNr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stamnr='" + stamNr + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", poBox='" + poBox + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Students)) return false;

        Students students = (Students) o;

        if (id != null ? !id.equals(students.id) : students.id != null) return false;
        if (firstName != null ? !firstName.equals(students.firstName) : students.firstName != null) return false;
        if (lastName != null ? !lastName.equals(students.lastName) : students.lastName != null) return false;
        if (stamNr != null ? !stamNr.equals(students.stamNr) : students.stamNr != null) return false;
        if (username != null ? !username.equals(students.username) : students.username != null) return false;
        return email != null ? email.equals(students.email) : students.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (stamNr != null ? stamNr.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
