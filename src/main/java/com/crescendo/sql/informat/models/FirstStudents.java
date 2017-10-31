package com.crescendo.sql.informat.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
/** FirstStudents Entity
 * @author Groep 5
 */
@Entity
@Table(name="firstinformatstudents", schema = "crescendo")
public class FirstStudents extends Students {

    public FirstStudents() {
    }


}
