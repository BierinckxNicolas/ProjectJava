package com.crescendo.informat.models;

/** Module POJO
 * @author Groep 5
 */
public class Module {
    private String id;
    private String exchangeCode;
    private String code;
    private String name;
    private String moduleCode;

    public Module(){

    }

    public Module(String id, String exchangeCode, String code, String name, String moduleCode) {
        this.id = id;
        this.exchangeCode = exchangeCode;
        this.code = code;
        this.name = name;
        this.moduleCode = moduleCode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id='" + id + '\'' +
                ", exchangeCode='" + exchangeCode + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", moduleCode='" + moduleCode + '\'' +
                '}';
    }
}
