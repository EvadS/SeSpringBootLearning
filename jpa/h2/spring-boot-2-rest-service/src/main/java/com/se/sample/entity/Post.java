package com.se.sample.entity;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Entity(name = "Post")
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private long score;

    /**
     * The updatable attribute instruct Hibernate to omit this column from the generated UPDATE SQL statemen
     */
    @Column(name = "AUTHOR", nullable = false, length = 75, updatable= false )
    private String autor;

    @Column(
            name = "created_on",
            nullable = false,
            updatable = false
    )
    private Timestamp createdOn;

    /**
     *  Hibernate is going to ignore this attribute when translating the entity state modifications into an SQL statement.
     */
    @Transient
    private String creationTimestamp;

    public Post() {
        this.createdOn = new Timestamp(System.currentTimeMillis());
    }

    public String getCreationTimestamp() {
        if(creationTimestamp == null) {
            creationTimestamp = DateTimeFormatter
                    .ISO_DATE_TIME.format(
                            createdOn.toLocalDateTime()
                    );
        }
        return creationTimestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "Post{\n" +
                        "  id=%d\n" +
                        "  title='%s'\n" +
                        "  score=%d\n" +
                        "  creationTimestamp='%s'\n" +
                        '}', id, title, score, getCreationTimestamp()
        );
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public void setCreationTimestamp(String creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }


    //Getters and setters omitted for brevity
}