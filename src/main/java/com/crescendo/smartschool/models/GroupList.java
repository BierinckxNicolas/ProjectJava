package com.crescendo.smartschool.models;
import java.util.ArrayList;

/** GroupList POJO
 * @author Groep 5
 */
public class GroupList {
    private ArrayList<Group> group;

    public void setGroup(ArrayList<Group> group) {
        this.group = group;
    }

    public ArrayList<Group> getGroup() {
        return this.group;
    }
}

