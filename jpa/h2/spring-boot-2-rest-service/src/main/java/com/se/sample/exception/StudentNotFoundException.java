package com.se.sample.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String exception) {
        super(exception);
    }

}
