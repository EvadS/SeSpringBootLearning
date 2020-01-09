package com.se.sample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


    @Entity(name="student")
    public class Student {
        @Id
        @GeneratedValue
        private Long id;

        @NotNull
        @Size(min=2, message="Name should have atleast 2 characters")
        private String name;

        @NotNull
        @Size(min=7, message="Passport should have atleast 2 characters")
        private String passportNumber;

    public Student() {
       // super();
    }

        public Student(@NotNull @Size(min = 2, message = "Name should have atleast 2 characters") String name, @NotNull @Size(min = 7, message = "Passport should have atleast 2 characters") String passportNumber) {
            this.name = name;
            this.passportNumber = passportNumber;
        }

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

}