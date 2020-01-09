package com.se.sample.controller;

import com.se.sample.entity.Student;
import com.se.sample.entity.StudentReport;
import com.se.sample.exception.StudentNotFoundException;
import com.se.sample.model.*;
import com.se.sample.repository.StudentReportRepository;
import com.se.sample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class StudentResource {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentReportRepository studentReportRepository;


    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {

        return studentRepository.findAll();
    }

    @GetMapping("/students-h")
    public StudentsListV0 retrieveAllStudentsh() {
        StudentsListV0 list = new StudentsListV0();

        for (Student employee : studentRepository.findAll()) {

            //Adding self link employee 'singular' resource
            Link link = linkTo(StudentRepository.class)
                    .slash(employee.getId())
                    .withSelfRel();

            StudentsV0 studentV0 = new StudentsV0();
            studentV0.setId(employee.getId());
            studentV0.setName(employee.getName());
            studentV0.setPassportNumber(employee.getPassportNumber());

            studentV0.add(link);

            //Adding method link employee 'singular' resource
//            ResponseEntity methodLinkBuilder = WebMvcLinkBuilder
//                    .methodOn(StudentResource.class)
//                    .getReportByStudentById(employee.getId());


//            Link reportLink = linkTo(methodLinkBuilder)
//                    .withRel("students-report");

 //           studentV0.add(reportLink);
            list.getStudents().add(studentV0);
        }

        return list;
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentsV1> get(@PathVariable final long id) {
        return studentRepository
                .findById(id)
                .map(p -> ResponseEntity.ok(new StudentsV1(p)))
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(id)));
    }



    @GetMapping(value = "/students/{id}/report", produces = {"application/hal+json"})
    public ResponseEntity<StudentReportList> getReportByStudentById(@PathVariable(value = "id", required = true) Long id) {

        StudentReportList reportList = new StudentReportList();

        final List<StudentReport> collectionInput =
                studentReportRepository.findAllByStudentId(id);

        // TODO: use java 8
        for (StudentReport user : collectionInput) {
            StudentReportV0 studentReport = new StudentReportV0(user);

            studentReport.add(linkTo(methodOn(StudentResource.class).deleteUser(user.getId())).withRel("delete_user").withType("DELETE"));

            studentReport.add(linkTo(methodOn(StudentResource.class).findOne(user.getId()))
                    .withRel("get_stud")
                    .withType("GET")
                    .withTitle("GET_TITLE"));


            reportList.getList().add(studentReport);
        }


        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }



    @GetMapping("/students/{id}")
    public Student retrieveStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new StudentNotFoundException("id-" + id);

        return student.get();
    }


    @GetMapping("/students-h/{id}")
    public EntityModel<Student> findOne(@PathVariable Long id) {

        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new StudentNotFoundException("id-" + id);

        EntityModel<Student> resource = new EntityModel<Student>(student.get());

        Link selflink =  linkTo(StudentResource.class).slash(student.get().getId()).withSelfRel();

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());
        resource.add(linkTo.withRel("all-students").withSelfRel());//.withSelfRel(resource.add(selflink)));


        return resource;
    }


    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable(value = "id", required = true)Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()) {
            throw new StudentNotFoundException("User not found with id - " + id);
        }

        studentRepository.delete(student.get());
    }


    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/user/{id}", produces = {"application/hal+json"})
    public EntityModel<StudentsV0> deleteUser(@PathVariable(value = "id", required = true) Long id) {

        Optional<Student> user = studentRepository.findById(id);

        if (!user.isPresent()) {
            throw new StudentNotFoundException("User not found with id - " + id);
        }

        studentRepository.delete(user.get());

        StudentsV0 userNull = new StudentsV0();
        userNull.add(linkTo(methodOn(StudentResource.class).retrieveAllStudents()).withRel("get_users").withType("GET"));
        return new EntityModel<StudentsV0>(userNull);
    }

    // TODO:

    @GetMapping(value = "/users", produces = {"application/hal+json"})
    public EntityModel<Student> getUsers() {

        List<Student> users = studentRepository.findAll();
        for (Student user : users) {
            Long userId = user.getId();

        //    user.add(linkTo(methodOn(UserController.class).getUser(userId)).withRel("get_user").withType("GET"));
          //  user.add(linkTo(methodOn(UserController.class).deleteUser(userId)).withRel("delete_user").withType("DELETE"));
        }



        Link link = linkTo(methodOn(StudentResource.class).getUsers()).withSelfRel().withType("GET");
        return new EntityModel<Student>(users.get(0));
    }
}