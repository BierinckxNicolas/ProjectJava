package com.crescendo.smartschool.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** ChildrenGroup POJO
 * @author Groep 5
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChildrenGroup {

    private String untis;
    private String code;
    private String visible;
    private String instituteNumber;
    private String name;
    private String adminNumber;
    private String coAccountLabel;
    private String type;
    private String desc;
    private String isOfficial;
    private Children children;


    public String getUntis() {
        return this.untis;
    }

    public void setUntis(String untis) {
        this.untis = untis;
    }

    public String getInstituteNumber()
    {
        return this.instituteNumber;
    }

    public void setInstituteNumber(String instituteNumber) {
        this.instituteNumber = instituteNumber;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getVisible() {
        return this.visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }



    public String getName() {

        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAdminNumber() {
        return this.adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }


    public String getCoAccountLabel() {
        return this.coAccountLabel;
    }

    public void setCoAccountLabel(String coAccountLabel) {
        this.coAccountLabel = coAccountLabel;
    }


    public String getType(){
    return this.type;
    }

    public void setType(String type) {
    this.type = type;
    }


    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getIsOfficial() {
    return this.isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
    this.isOfficial = isOfficial;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ChildrenGroup{" +
                "untis='" + untis + '\'' +
                ", code='" + code + '\'' +
                ", visible='" + visible + '\'' +
                ", instituteNumber='" + instituteNumber + '\'' +
                ", name='" + name + '\'' +
                ", adminNumber='" + adminNumber + '\'' +
                ", coAccountLabel='" + coAccountLabel + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                ", isOfficial='" + isOfficial + '\'' +
                ", children=" + children +
                '}';
    }
}
