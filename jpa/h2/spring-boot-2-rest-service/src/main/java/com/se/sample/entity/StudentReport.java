package com.se.sample.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name="student_report")
public class StudentReport {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY,optional = false)
//    private Student student;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;


    public StudentReport() {
    }

    public StudentReport(@NotEmpty String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
