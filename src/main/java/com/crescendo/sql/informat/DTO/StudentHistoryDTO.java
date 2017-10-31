package com.crescendo.sql.informat.DTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/** StudentHistory DTO
 * @author Groep 5
 */
public class StudentHistoryDTO implements Serializable {


    private String firstName;
    private String lastName;
    private String email;
    private String result;
    private String resultExPeriod1;
    private String resultExPeriod2;
    private String schoolyear;
    private String coursname;
    private String adminGroup;
    private String studyArea;
    private String lector;
    private String moduleName;
    private String location;
    private String cursusCode; // Thirdparty ref
    private String semester;
    private String adress;
    private String state;
    private String username;
    private String maxScorePeriod1;
    private String maxScorePeriod2;
    private String pointCodePeriod1;
    private String pointCodePeriod2;

    public StudentHistoryDTO(String firstName, String lastName,String email, String result, String resultExPeriod1, String resultExPeriod2, String schoolyear, String coursname, String adminGroup, String studyArea, String lector, String moduleName, String location, String cursusCode, String semester, String adress,  String state, String username, String maxScorePeriod1, String maxScorePeriod2, String pointCodePeriod1, String pointCodePeriod2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.result = result;
        this.resultExPeriod1 = resultExPeriod1;
        this.resultExPeriod2 = resultExPeriod2;
        this.schoolyear = schoolyear;
        this.coursname = coursname;
        this.adminGroup = adminGroup;
        this.studyArea = studyArea;
        this.lector = lector;
        this.moduleName = moduleName;
        this.location = location;
        this.cursusCode = cursusCode;
        this.semester = semester;
        this.adress = adress;
        this.state = state;
        this.username = username;
        this.maxScorePeriod1=maxScorePeriod1;
        this.maxScorePeriod2=maxScorePeriod2;
        this.pointCodePeriod1=pointCodePeriod1;
        this.pointCodePeriod2=pointCodePeriod2;
    }


    public StudentHistoryDTO() {
    }

    public String getPointCodePeriod1() {
        return pointCodePeriod1;
    }

    public void setPointCodePeriod1(String pointCodePeriod1) {
        this.pointCodePeriod1 = pointCodePeriod1;
    }

    public String getPointCodePeriod2() {
        return pointCodePeriod2;
    }

    public void setPointCodePeriod2(String pointCodePeriod2) {
        this.pointCodePeriod2 = pointCodePeriod2;
    }

    public String getMaxScorePeriod1() {
        return maxScorePeriod1;
    }

    public void setMaxScorePeriod1(String maxScorePeriod1) {
        this.maxScorePeriod1 = maxScorePeriod1;
    }

    public String getMaxScorePeriod2() {
        return maxScorePeriod2;
    }

    public void setMaxScorePeriod2(String maxScorePeriod2) {
        this.maxScorePeriod2 = maxScorePeriod2;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResultExPeriod1() {
        return resultExPeriod1;
    }

    public void setResultExPeriod1(String resultExPeriod1) {
        this.resultExPeriod1 = resultExPeriod1;
    }

    public String getResultExPeriod2() {
        return resultExPeriod2;
    }

    public void setResultExPeriod2(String resultExPeriod2) {
        this.resultExPeriod2 = resultExPeriod2;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(String schoolyear) {
        this.schoolyear = schoolyear;
    }

    public String getCoursname() {
        return coursname;
    }

    public void setCoursname(String coursname) {
        this.coursname = coursname;
    }

    public String getAdminGroup() {
        return adminGroup;
    }

    public void setAdminGroup(String adminGroup) {
        this.adminGroup = adminGroup;
    }

    public String getStudyArea() {
        return studyArea;
    }

    public void setStudyArea(String studyArea) {
        this.studyArea = studyArea;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCursusCode() {
        return cursusCode;
    }

    public void setCursusCode(String cursusCode) {
        this.cursusCode = cursusCode;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\",\"email\":\""+ email +"\",\"result\":\""+result+"\",\"resultExPeriod1\":\""+resultExPeriod1+"\",\"resultExPeriod2\":\""+resultExPeriod2+"\",\"schoolyear\":\""+schoolyear+"\",\"courseName\":\""+coursname+"\",\"adminGroup\":\""+adminGroup+"\",\"studyArea\":\""+studyArea+"\",\"lector\":\""+lector+"\",\"moduleName\":\""+moduleName+"\",\"location\":\""+location+"\",\"cursusCode\":\""+cursusCode+"\",\"semester\":\""+semester+"\",\"adress\":\""+adress+"\",\"state\":\""+state+"\"}";

    }
/* @Override
    public String toString(){
        return "[\"firstName:\""+firstName+",\"lastName:\""+lastName+",\"result:\""+result+",\"schoolyear:\""+schoolyear+",\"courseId:\""+courseId+"]";
    }
    */
}
