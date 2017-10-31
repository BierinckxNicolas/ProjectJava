package com.crescendo.sql.informat.models;

import javax.persistence.*;
import java.util.Set;

/** Departments Entity
 * @author Groep 5
 */
@Entity
@Table(name="informatdepartments", schema = "crescendo")
public class Departments {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="nrAdmgrp")
    private String nrAdmgrp;

    @Column(name="admgrp")
    private String admgrp;

    @Column(name="eduCode")
    private String eduCode;

    @Column(name="eduName")
    private String eduName;

    @Column(name="area")
    private String area;

    public Departments() {
    }

    public Departments(String id, String code, String name, String nrAdmgrp, String admgrp, String eduCode, String eduName, String area) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.nrAdmgrp = nrAdmgrp;
        this.admgrp = admgrp;
        this.eduCode = eduCode;
        this.eduName = eduName;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNrAdmgrp() {
        return nrAdmgrp;
    }

    public void setNrAdmgrp(String nrAdmgrp) {
        this.nrAdmgrp = nrAdmgrp;
    }

    public String getAdmgrp() {
        return admgrp;
    }

    public void setAdmgrp(String admgrp) {
        this.admgrp = admgrp;
    }

    public String getEduCode() {
        return eduCode;
    }

    public void setEduCode(String eduCode) {
        this.eduCode = eduCode;
    }

    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", nrAdmgrp='" + nrAdmgrp + '\'' +
                ", admgrp='" + admgrp + '\'' +
                ", eduCode='" + eduCode + '\'' +
                ", eduName='" + eduName + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
