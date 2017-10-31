package com.crescendo.informat.models;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/** Course POJO
 * @author Groep 5
 */
public class Course {

    private String id;
    private String name;
    private int nrOfRegistrations;
    private int maxRegistrations;
    private Date startDate;
    private Date registrationDate;
    private Date endDate;
    private int semester;
    private float registrationCost;
    private float extraCosts;
    private String type;
    private List<String> exchangeCodes;
    private String departmentName;
    private Department department;
    private boolean hasRightToEducationalLeave;
    private int officialCourseDuration;
    private String thirdPartyRef;
    private String remarks;
    private MainTeacher mainTeacher;
    private List<MainTeacher> otherTeachers;
    private Location location;
    private Room room;
    private List<Module> modules;

    public Course() {
    }

    public Course(String id, String name, int nrOfRegistrations, int maxRegistrations, Date startDate, Date registrationDate, Date endDate, int semester, float registrationCost, float extraCosts, String type, List<String> exchangeCodes, String departmentName, Department department, boolean hasRightToEducationalLeave, int officialCourseDuration, String thirdPartyRef, String remarks, MainTeacher mainTeacher, List<MainTeacher> otherTeachers, Location location, Room room, List<Module> modules) {
        this.id = id;
        this.name = name;
        this.nrOfRegistrations = nrOfRegistrations;
        this.maxRegistrations = maxRegistrations;
        this.startDate = startDate;
        this.registrationDate = registrationDate;
        this.endDate = endDate;
        this.semester = semester;
        this.registrationCost = registrationCost;
        this.extraCosts = extraCosts;
        this.type = type;
        this.exchangeCodes = exchangeCodes;
        this.departmentName = departmentName;
        this.department = department;
        this.hasRightToEducationalLeave = hasRightToEducationalLeave;
        this.officialCourseDuration = officialCourseDuration;
        this.thirdPartyRef = thirdPartyRef;
        this.remarks = remarks;
        this.mainTeacher = mainTeacher;
        this.otherTeachers = otherTeachers;
        this.location = location;
        this.room = room;
        this.modules = modules;
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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public float getRegistrationCost() {
        return registrationCost;
    }

    public void setRegistrationCost(float registrationCost) {
        this.registrationCost = registrationCost;
    }

    public float getExtraCosts() {
        return extraCosts;
    }

    public void setExtraCosts(float extraCosts) {
        this.extraCosts = extraCosts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getExchangeCodes() {
        return exchangeCodes;
    }

    public void setExchangeCodes(List<String> exchangeCodes) {
        this.exchangeCodes = exchangeCodes;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isHasRightToEducationalLeave() {
        return hasRightToEducationalLeave;
    }

    public void setHasRightToEducationalLeave(boolean hasRightToEducationalLeave) {
        this.hasRightToEducationalLeave = hasRightToEducationalLeave;
    }

    public int getOfficialCourseDuration() {
        return officialCourseDuration;
    }

    public void setOfficialCourseDuration(int officialCourseDuration) {
        this.officialCourseDuration = officialCourseDuration;
    }

    public String getThirdPartyRef() {
        return thirdPartyRef;
    }

    public void setThirdPartyRef(String thirdPartyRef) {
        this.thirdPartyRef = thirdPartyRef;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public MainTeacher getMainTeacher() {
        return mainTeacher;
    }

    public void setMainTeacher(MainTeacher mainTeacher) {
        this.mainTeacher = mainTeacher;
    }

    public List<MainTeacher> getOtherTeachers() {
        return otherTeachers;
    }

    public void setOtherTeachers(List<MainTeacher> otherTeachers) {
        this.otherTeachers = otherTeachers;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nrOfRegistrations=" + nrOfRegistrations +
                ", maxRegistrations=" + maxRegistrations +
                ", startDate=" + startDate +
                ", registrationDate=" + registrationDate +
                ", endDate=" + endDate +
                ", semester=" + semester +
                ", registrationCost=" + registrationCost +
                ", extraCosts=" + extraCosts +
                ", type='" + type + '\'' +
                ", exchangeCodes=" + exchangeCodes +
                ", departmentName='" + departmentName + '\'' +
                ", department=" + department +
                ", hasRightToEducationalLeave=" + hasRightToEducationalLeave +
                ", officialCourseDuration=" + officialCourseDuration +
                ", thirdPartyRef='" + thirdPartyRef + '\'' +
                ", remarks='" + remarks + '\'' +
                ", mainTeacher=" + mainTeacher +
                ", otherTeachers=" + otherTeachers +
                ", location=" + location +
                ", room=" + room +
                ", modules=" + modules +
                '}';
    }
}
