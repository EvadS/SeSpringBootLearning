package com.se.date.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@ApiModel(description = "Minimal details about the User. ")
public class User {
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    @ApiModelProperty(notes = "The database generated employee ID")
    private  int id ;

    @ApiModelProperty(notes = "The end of estimation", value = "19.12.2010 04:23:17.452")

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime estimation;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getEstimation() {
        return estimation;
    }

    public void setEstimation(LocalDateTime estimation) {
        this.estimation = estimation;
    }
}

