package com.se.sample.exception.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Evgeniy Skiba on 20.04.21
 */
@Data
public  class AwesomeException {
    private String message;
    private Date date = new Date ();

    public  AwesomeException(String message){this.message = message;}
}