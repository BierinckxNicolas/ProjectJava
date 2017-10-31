package com.crescendo.sql.informat.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/** DeletedStudents Entity
 * @author Groep 5
 */
@Entity
@Table(name="deletedinformatstudents", schema = "crescendo")
public class DeletedStudents extends Students {
    public DeletedStudents(){

    }
}
