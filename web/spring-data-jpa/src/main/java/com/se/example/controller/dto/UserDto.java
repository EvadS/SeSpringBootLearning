package com.se.example.controller.dto;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}