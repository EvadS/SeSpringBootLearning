package com.example.filedemo.validator;

public class ApiError {
    private String message;

    private int n1;

    public ApiError(String message,  int n1) {
        this.message = message;
        this.n1 = n1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }
}

