package com.se.many.to.many.demo2;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Evgeniy Skiba on 22.04.21
 */
@Entity(name = "EmailTemplate")
@Table(name = "email_template", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "template_name"
        })
})
public class EmailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @NotBlank(message = "Name cannot be null or empty ")
    @Column(name = "template_name", unique = true, nullable = false)
    @Size(max = 128, message = "The name of the message template. A template name must start with an alphanumeric character and can contain a maximum of 128 character")
    private String templateName;

    @Lob
    @Column(name = "html_content")
    private String htmlPart;

    @Column(name="background_url")
    private String background;

    @ManyToMany
    @JoinTable(name="email_attribute_email_template",
            joinColumns=@JoinColumn(name="email_template_id"),
            inverseJoinColumns=@JoinColumn(name="email_attribute_id"))
    private List<EmailAttribute> emailAttributes;


    public EmailTemplate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlPart() {
        return htmlPart;
    }

    public void setHtmlPart(String htmlPart) {
        this.htmlPart = htmlPart;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<EmailAttribute> getEmailAttributes() {
        return emailAttributes;
    }

    public void setEmailAttributes(List<EmailAttribute> emailAttributes) {
        this.emailAttributes = emailAttributes;
    }


    @Override
    public String toString() {
        return "EmailTemplate{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", templateName='" + templateName + '\'' +
                ", htmlPart='" + htmlPart + '\'' +
                ", background='" + background + '\'' +
                ", emailAttributes=" + emailAttributes +
                '}';
    }
}
