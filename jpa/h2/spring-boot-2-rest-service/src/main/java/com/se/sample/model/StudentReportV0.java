package com.se.sample.model;

import com.se.sample.controller.StudentResource;
import com.se.sample.entity.StudentReport;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class StudentReportV0 extends RepresentationModel<StudentReportV0> implements Serializable {


    private StudentReport studentReport;

    public StudentReportV0(StudentReport studentReport) {
        this.studentReport = studentReport;
        final long id = studentReport.getId();
     //   add(linkTo(PersonController.class).withRel("people"));
        add(linkTo(methodOn(StudentResource.class).findOne(id)).withSelfRel());
    }

}
