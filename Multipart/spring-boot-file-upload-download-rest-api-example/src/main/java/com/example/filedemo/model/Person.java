package com.example.filedemo.model;

import com.example.filedemo.validator.ValidImage;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class Person {

    @Size(min=2, max=50)
    private String Name;

    @Digits(integer=3, fraction=0, message = "Не более 3-х знаков")
    @PersonAgeConstraint
    private Integer age;

    @ValidImage
    private MultipartFile file;

    public Person(String name, Integer age) {
        Name = name;
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}