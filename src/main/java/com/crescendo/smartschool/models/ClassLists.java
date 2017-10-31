package com.crescendo.smartschool.models;

import java.util.ArrayList;

/** ClassLists POJO
 * @author Groep 5
 */
public class ClassLists {
    private ArrayList<ClassList> classlist;

    public void setClasslist(ArrayList<ClassList> classlist){
        this.classlist = classlist;
    }
    public ArrayList<ClassList> getClasslist(){
        return this.classlist;
    }
}
