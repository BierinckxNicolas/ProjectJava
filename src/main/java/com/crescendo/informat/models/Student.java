package com.crescendo.informat.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/** Students POJO
 * @author Groep 5
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String nickName;
    private String bankAccountNr;
    private Date dateOfBirth;
    private String sex;
    private String loginName;
    private String password;
    private List<String> email;
    private List<String> phone;
    private Address address;
    private String stamNr;
    private String insz;
    private List<Vv> vv;

    public Student() {
    }

    public Student(String id, String firstName, String lastName, String nickName, String bankAccountNr, Date dateOfBirth, String sex, String loginName, String password, List<String> email, List<String> phone, Address address, String stamNr, String insz, List<Vv> vv) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.bankAccountNr = bankAccountNr;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.loginName = loginName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.stamNr = stamNr;
        this.insz = insz;
        this.vv = vv;
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

    public String getBankAccountNr() {
        return bankAccountNr;
    }

    public void setBankAccountNr(String bankAccountNr) {
        this.bankAccountNr = bankAccountNr;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public List<Vv> getVv() {
        return vv;
    }

    public void setVv(List<Vv> vv) {
        this.vv = vv;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", bankAccountNr='" + bankAccountNr + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex='" + sex + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email +
                ", phone=" + phone +
                ", address=" + address +
                ", stamNr='" + stamNr + '\'' +
                ", insz='" + insz + '\'' +
                ", vv=" + vv +
                '}';
    }
}

