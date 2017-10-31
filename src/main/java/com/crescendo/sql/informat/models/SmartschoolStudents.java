package com.crescendo.sql.informat.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** informatssstudents Entity
 * @author Groep 5
 */
@Entity
@Table(name = "informatssstudents",schema = "crescendo")
public class SmartschoolStudents {


    @Column(name="id")
    private String id;

    @Column(name = "firstName")
    private String firstName; // Firstname

    @Column(name = "lastName")
    private String lastName; // lastname

    @Id
    @Column(name = "stamNr")
    private String stamNr; // stamnr = Smartschool id

    @Column(name = "username", length = 93)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "sex")
    private String sex;

    @Column (name="phonenumber")
    private String phonenumber;

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

    public SmartschoolStudents() {
    }

    public SmartschoolStudents(String id, String firstName, String lastName, String stamNr, String username, String email, String sex, String phonenumber, String country, String city, String street, String poBox, String postalCode, String houseNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stamNr = stamNr;
        this.username = username;
        this.email = email;
        this.sex = sex;
        this.phonenumber = phonenumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.poBox = poBox;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
        return "SmartschoolStudents{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stamNr='" + stamNr + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", poBox='" + poBox + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
