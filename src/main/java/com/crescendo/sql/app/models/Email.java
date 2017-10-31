package com.crescendo.sql.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/** Email model
 * @author Groep 5
 */
@Entity
@Table(name = "emails", schema="crescendo")
public class Email {
    @Id
    @Column(name = "email_id")
    @Min(1)
    @Max(3)
    @NotNull
    private int emailid;

    @Column(name = "email_type")
    private String emailType;

    @Column(name = "email_content", columnDefinition = "text")
    @NotNull
    private String emailContent;

    public Email() {
    }

    public Email(int emailid, String emailType, String emailContent) {
        this.emailid = emailid;
        this.emailType = emailType;
        this.emailContent = emailContent;
    }

    public int getEmailid() {
        return emailid;
    }

    public void setEmailid(int emailid) {
        this.emailid = emailid;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
}
