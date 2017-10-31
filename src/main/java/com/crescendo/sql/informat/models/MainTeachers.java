package com.crescendo.sql.informat.models;

import javax.persistence.*;
import java.util.Set;

/** MainTeachers Entity
 * @author Groep 5
 */
@Entity
@Table(name="informatmainteachers", schema = "crescendo")
public class MainTeachers {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="lastName")
    private String lastName;

    @Column(name="firstName")
    private String firstName;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="phonenumber")
    private String phonenumber;

    @Column(name="street")
    private String street;

    @Column(name="city")
    private String city;

    @Column(name="postalCode")
    private String postalCode;

    @Column(name="poBox")
    private String poBox;

    @Column(name="country")
    private String country;

    @Column(name="sex")
    private String sex;

    @Column(name="stamNr")
    private String stamNr;

    @Column(name="houseNumber")
    private String houseNumber;

    public MainTeachers() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }


    @Override
    public String toString() {
        return "MainTeachers{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", poBox='" + poBox + '\'' +
                ", country='" + country + '\'' +
                ", sex='" + sex + '\'' +
                ", stamNr='" + stamNr + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
