package com.crescendo.sql.app.models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** Checkbox model
 * @author Groep 5
 */
@Entity
@Table(name="checkboxstates", schema = "crescendo")
public class CheckBox {

    @Id
    @Column(name = "checkbox_id")
    @Min(1)
    @Max(4)
    @NotNull
    private int checkboxId;

    @Column(name = "checkbox_type")
    private String checkboxType;

    @Column(name = "checkbox_state")
    @NotNull
    private boolean checkboxState;

    public CheckBox() {
    }

    public CheckBox(int checkboxId, String checkboxType, boolean checkboxState) {
        this.checkboxId = checkboxId;
        this.checkboxType = checkboxType;
        this.checkboxState = checkboxState;
    }

    public CheckBox(int checkboxId) {
        this.checkboxId = checkboxId;
    }

    public int getCheckboxId() {
        return checkboxId;
    }

    public void setCheckboxId(int checkboxId) {
        this.checkboxId = checkboxId;
    }

    public String getCheckboxType() {
        return checkboxType;
    }

    public void setCheckboxType(String checkboxType) {
        this.checkboxType = checkboxType;
    }

    public boolean isCheckboxState() {
        return checkboxState;
    }

    public void setCheckboxState(boolean checkboxState) {
        this.checkboxState = checkboxState;
    }
}
