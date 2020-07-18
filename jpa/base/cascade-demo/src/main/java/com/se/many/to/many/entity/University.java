package com.se.many.to.many.entity;


import javax.persistence.*;
import java.util.List;


@Entity
public class University
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name="student_university",
            joinColumns=@JoinColumn(name="university_id"),
            inverseJoinColumns=@JoinColumn(name="student_id"))
    private List<Student> students;

    // set/get не представлены


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "=== \tUniversity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}