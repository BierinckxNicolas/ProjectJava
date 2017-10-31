package com.crescendo.sql.informat.models;

import com.crescendo.informat.models.*;
import com.crescendo.informat.services.InformatService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import sun.applet.Main;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/** Coures Entity
 * @author Groep 5
 */
@Entity
@Table(name="informatcourses", schema="crescendo")
public class Courses {

    @Id
    @Column (name = "id")
    private String id;

    @Column (name = "name")
    private String name;

    @Column (name ="nrOfRegistrations")
    private int nrOfRegistrations;

    @Column (name = "maxRegistrations")
    private int maxRegistrations;

    @Column (name = "startDate")
    private Date startDate;

    @Column (name = "endDate")
    private Date endDate;

    @Column (name = "semester")
    private int semester;

    @Column (name = "thirdPartyRef")
    private String thirdPartyRef;

    @Column(name = "informatmainteachers_id")
    private String mainTeachers;

    @Column(name = "informatdepartments_id")
    private String departments;

    @Column(name= "informatlocations_id")
    private String locations;

    @Column(name= "informatstudiegebied_name")
    private String studiegebied;

    @ManyToMany(mappedBy = "coursesSet", cascade = CascadeType.ALL)
    private Set<Modules> modulesSet;


    public Courses(String id, String name, int nrOfRegistrations, int maxRegistrations, Date startDate, Date endDate, int semester, String thirdPartyRef, String mainTeachers, String departments, String locations, String studiegebied) {
        this.id = id;
        this.name = name;
        this.nrOfRegistrations = nrOfRegistrations;
        this.maxRegistrations = maxRegistrations;
        this.startDate = startDate;
        this.endDate = endDate;
        this.semester = semester;
        this.thirdPartyRef = thirdPartyRef;
        this.mainTeachers = mainTeachers;
        this.departments = departments;
        this.locations = locations;
        this.studiegebied = studiegebied;
    }

    public Courses() {
    }

    public Courses(String id, String name, int nrOfRegistrations, int maxRegistrations, Date startDate, Date endDate, int semester, String thirdPartyRef, InformatService informatService, Set<Modules> modulesSet) throws IOException {
        this.id = id;
        this.name = name;
        this.nrOfRegistrations = nrOfRegistrations;
        this.maxRegistrations = maxRegistrations;
        this.startDate = startDate;
        this.endDate = endDate;
        this.semester = semester;
        this.thirdPartyRef = thirdPartyRef;
        this.modulesSet = modulesSet;
    }

    public Courses(String id, String name, int nrOfRegistrations, int maxRegistrations, Date startDate, Date endDate, int semester, String thirdPartyRef) {
        this.id = id;
        this.name = name;
        this.nrOfRegistrations = nrOfRegistrations;
        this.maxRegistrations = maxRegistrations;
        this.startDate = startDate;
        this.endDate = endDate;
        this.semester = semester;
        this.thirdPartyRef = thirdPartyRef;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getThirdPartyRef() {
        return thirdPartyRef;
    }

    public void setThirdPartyRef(String thirdPartyRef) {
        this.thirdPartyRef = thirdPartyRef;
    }

    public String getMainTeachers() {
        return mainTeachers;
    }

    public void setMainTeachers(String mainTeachers) {
        this.mainTeachers = mainTeachers;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getStudiegebied() {
        return studiegebied;
    }

    public void setStudiegebied(String studiegebied) {
        this.studiegebied = studiegebied;
    }

    public Set<Modules> getModulesSet() {
        return modulesSet;
    }

    public void setModulesSet(Set<Modules> modulesSet) {
        this.modulesSet = modulesSet;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nrOfRegistrations=" + nrOfRegistrations +
                ", maxRegistrations=" + maxRegistrations +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", semester=" + semester +
                ", thirdPartyRef='" + thirdPartyRef + '\'' +
                ", mainTeachers=" + mainTeachers +
                ", departments=" + departments +
                ", locations=" + locations +
                ", studiegebied=" + studiegebied +
                ", modulesSet=" + modulesSet +
                '}';
    }

}