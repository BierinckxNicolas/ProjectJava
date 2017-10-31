package com.crescendo.informat.services.responses;

import com.crescendo.informat.models.Location;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/** CourseGeneral model
 * @author Groep 5
 * @version 0.1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseGeneral {
    private String id;
    private String name;
    private int nrOfRegistrations;
    private int maxRegistrations;
    private int semester;
    private Date startDate;
    private Date registrationDate;
    private Date endDate;
    private String type;
    private String remarks;
    private TeacherGeneral mainTeacherGeneral;
    private Location location;

    public CourseGeneral() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNrOfRegistrations() {
        return nrOfRegistrations;
    }

    public void setNrOfRegistrations(int nrOfRegistrations) {
        this.nrOfRegistrations = nrOfRegistrations;
    }

    public int getMaxRegistrations() {
        return maxRegistrations;
    }

    public void setMaxRegistrations(int maxRegistrations) {
        this.maxRegistrations = maxRegistrations;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public TeacherGeneral getMainTeacherGeneral() {
        return mainTeacherGeneral;
    }

    public void setMainTeacherGeneral(TeacherGeneral mainTeacherGeneral) {
        this.mainTeacherGeneral = mainTeacherGeneral;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nrOfRegistrations=" + nrOfRegistrations +
                ", maxRegistrations=" + maxRegistrations +
                ", semester=" + semester +
                ", startDate=" + startDate +
                ", registrationDate=" + registrationDate +
                ", endDate=" + endDate +
                ", type='" + type + '\'' +
                ", remarks='" + remarks + '\'' +
                ", mainTeacherGeneral=" + mainTeacherGeneral +
                ", location=" + location +
                '}';
    }
}
