package com.crescendo.sql.informat.models;

import com.crescendo.informat.models.Course;
import com.crescendo.informat.models.Module;
import com.crescendo.informat.models.StudentHistory;
import com.crescendo.informat.services.InformatService;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/** informatmodules Entity
 * @author Groep 5
 */
@Entity
@Table(name="informatmodules", schema = "crescendo")
public class Modules {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="exchangeCode")
    private String exchangeCode;

    @Column(name="code")
    private String code;

    @Column(name="moduleCode")
    private String moduleCode;

    @Column(name="name")
    private String name;

    @Column(name="smartschoolModule")
    private boolean smartschoolModule;


    @ManyToMany(targetEntity = Courses.class, cascade ={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "informatcourses_informatmodules",
            joinColumns = @JoinColumn(name = "module_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Courses> coursesSet;

    public Modules() {
    }

    public Modules(String id, String exchangeCode, String code, String moduleCode, String name, boolean smartschoolModule, Set<Courses> coursesSet) {
        this.id = id;
        this.exchangeCode = exchangeCode;
        this.code = code;
        this.moduleCode = moduleCode;
        this.name = name;
        this.smartschoolModule = smartschoolModule;
        this.coursesSet = coursesSet;
    }

    public Modules(String id, String exchangeCode, String code, String moduleCode, String name, InformatService informatService, Set<Courses> coursesSet) throws IOException {
        this.id = id;
        this.exchangeCode = exchangeCode;
        this.code = code;
        this.moduleCode = moduleCode;
        this.name = name;
        this.coursesSet = coursesSet;

        List<List<StudentHistory>> histories = informatService.getAllStudentHistories("2017-2018");
        for (List<StudentHistory> sh : histories) {
            for (StudentHistory studentHistory : sh) {
                List<Module> modules = studentHistory.getCourse().getModules();
                for (Module module : modules) {
                    if (module.getName().contains(id) && studentHistory.getCourse().getName().toUpperCase().contains("_SS")) {
                        this.smartschoolModule = true;
                    }else {
                        this.smartschoolModule = false;
                    }
                }
            }
        }
    }

    public Modules(String id, String exchangeCode, String code, String moduleCode, String name, InformatService informatService) throws IOException {
        this.id = id;
        this.exchangeCode = exchangeCode;
        this.code = code;
        this.moduleCode = moduleCode;
        this.name = name;

        List<List<StudentHistory>> histories = informatService.getAllStudentHistories("2017-2018");
        for (List<StudentHistory> sh : histories) {
            for (StudentHistory studentHistory : sh) {
                List<Module> modules = studentHistory.getCourse().getModules();
                for (Module module : modules) {
                    if (module.getName().contains(id) && studentHistory.getCourse().getName().toUpperCase().contains("_SS")) {
                        this.smartschoolModule = true;
                    }else {
                        this.smartschoolModule = false;
                    }
                }
            }
        }
    }

    public Modules(String id, String exchangeCode, String code, String moduleCode, String name, boolean smartschoolModule) {
        this.id = id;
        this.exchangeCode = exchangeCode;
        this.code = code;
        this.moduleCode = moduleCode;
        this.name = name;
        this.smartschoolModule = smartschoolModule;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSmartschoolModule() {
        return smartschoolModule;
    }

    public void setSmartschoolModule(boolean smartschoolModule) {
        this.smartschoolModule = smartschoolModule;
    }

    public Set<Courses> getCoursesSet() {
        return coursesSet;
    }

    public void setCoursesSet(Set<Courses> coursesSet) {
        this.coursesSet = coursesSet;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "id='" + id + '\'' +
                ", exchangeCode='" + exchangeCode + '\'' +
                ", code='" + code + '\'' +
                ", moduleCode='" + moduleCode + '\'' +
                ", name='" + name + '\'' +
                ", smartschoolModule=" + smartschoolModule +
                ", coursesSet=" + coursesSet +
                '}';
    }
}
