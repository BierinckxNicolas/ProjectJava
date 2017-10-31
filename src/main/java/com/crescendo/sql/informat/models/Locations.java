package com.crescendo.sql.informat.models;

import javax.persistence.*;
import java.util.Set;

/** informatlocations Entity
 * @author Groep 5
 */
@Entity
@Table(name="informatlocations", schema="crescendo")
public class Locations {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    public Locations() {
    }

    public Locations(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Locations{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
