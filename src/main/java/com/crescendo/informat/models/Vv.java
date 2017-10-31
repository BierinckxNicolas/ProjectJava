package com.crescendo.informat.models;

/** VV POJO
 * @author Groep 5
 */
public class Vv {
    private String description;
    private String value;

    public Vv() {
    }

    public Vv(String description, String value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Vv{" +
                "description='" + description + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
