package com.crescendo.sql.informat.models;

import javax.persistence.*;
import java.util.Set;

/** Studiegebied Entity
 * @author Groep 5
 */
@Entity
@Table(name="informatstudiegebied", schema = "crescendo")
public class Studiegebied {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @Column(name="type")
    private String type;

    public Studiegebied() {
    }

    public Studiegebied(String name, String code) {
        this.name = name;
        this.code = code;
        if (code.contains("HBO")) {
            this.type = "HBO5";
        } else if (code.contains("SLO")){
            this.type = "SLO";
        } else {
            this.type = "SVWO";
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Studiegebied{" +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
