package com.se.sample.model;

import com.se.sample.controller.StudentResource;
import com.se.sample.entity.Student;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public class StudentsV1 extends RepresentationModel<StudentsV1> {

    private final Student student;

    public StudentsV1(Student student) {

        this.student = student;
        final long id = student.getId();

        add(WebMvcLinkBuilder.linkTo(methodOn(StudentResource.class).get(id)).withSelfRel());
    }
}
