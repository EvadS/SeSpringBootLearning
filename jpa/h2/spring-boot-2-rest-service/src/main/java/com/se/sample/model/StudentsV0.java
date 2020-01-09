package com.se.sample.model;

import com.se.sample.entity.StudentReport;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentsV0 extends RepresentationModel<StudentsV0> implements Serializable {

    private Long id;
    private String name;
    private String passportNumber;
    private List<StudentReport> studentsReport = new ArrayList<>();

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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public List<StudentReport> getStudentsReport() {
        return studentsReport;
    }

    public void setStudentsReport(List<StudentReport> studentsReport) {
        this.studentsReport = studentsReport;
    }
}


