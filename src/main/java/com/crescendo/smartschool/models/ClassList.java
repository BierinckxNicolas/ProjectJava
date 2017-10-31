package com.crescendo.smartschool.models;

/** ClassList POJO
 * @author Groep 5
 */
public class ClassList {
    private String id;

    private String isClass;

    private String desc;

    private String isGroup;

    private String name;

    private String isOfficial;

    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsClass() {
        return isClass;
    }

    public void setIsClass(String isClass) {
        this.isClass = isClass;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ClassList{" +
            "id='" + id + '\'' +
            ", isClass='" + isClass + '\'' +
            ", desc='" + desc + '\'' +
            ", isGroup='" + isGroup + '\'' +
            ", name='" + name + '\'' +
            ", isOfficial='" + isOfficial + '\'' +
            ", code='" + code + '\'' +
            '}';
    }
}
