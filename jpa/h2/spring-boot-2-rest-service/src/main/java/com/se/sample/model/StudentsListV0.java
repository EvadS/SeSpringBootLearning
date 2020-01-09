package com.se.sample.model;

import org.springframework.hateoas.RepresentationModel;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="students")
public class StudentsListV0  extends RepresentationModel<StudentsListV0> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<StudentsV0> students = new ArrayList<StudentsV0>();

    public List<StudentsV0> getStudents() {
        return students;
    }

    public void setStudents(List<StudentsV0> students) {
        this.students = students;
    }
}
