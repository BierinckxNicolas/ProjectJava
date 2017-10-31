package com.crescendo.sql.informat.models;


import javax.persistence.Entity;
import javax.persistence.Table;

/** NewStudents Entity
 * @author Groep 5
 */
@Entity
@Table(name="newinformatstudents", schema = "crescendo")
public class NewStudents extends Students {
    public NewStudents(){

    }
}
