package com.se.sample.model;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentReportList extends RepresentationModel<StudentReportList> implements Serializable {

    private List<StudentReportV0> list = new ArrayList<>();

    public List<StudentReportV0> getList() {
        return list;
    }

    public void setList(List<StudentReportV0> list) {
        this.list = list;
    }
}
