package com.se.many.to.many.demo2;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by Evgeniy Skiba on 22.04.21
 */
@Entity(name = "EmailAttribute")
@Table(name = "email_attribute", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "attribute_name"
        })
})
public class EmailAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotBlank(message = "Attribute name cannot be null or empty ")
    @Column(name = "attribute_name", unique = true, nullable = false)
    private String attributeName;

    @ManyToMany
    @JoinTable(name = "email_attribute_email_template",
            joinColumns = @JoinColumn(name = "email_attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "email_template_id"))
    private List<EmailTemplate> emailTemplates;

    public EmailAttribute() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public List<EmailTemplate> getEmailTemplates() {
        return emailTemplates;
    }

    public void setEmailTemplates(List<EmailTemplate> emailTemplates) {
        this.emailTemplates = emailTemplates;
    }
}
