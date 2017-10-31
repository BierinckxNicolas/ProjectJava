package com.crescendo.sql.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Statistic model
 * @author Groep 5
 */
@Entity
@Table(name = "statistics", schema="crescendo")
public class Statistic {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "error")
    private String error;
    @Column(name = "aantal")
    private Long aantal;

    public Statistic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getAantal() {
        return aantal;
    }

    public void setAantal(Long aantal) {
        this.aantal = aantal;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", error='" + error + '\'' +
                ", aantal=" + aantal +
                '}';
    }
}
