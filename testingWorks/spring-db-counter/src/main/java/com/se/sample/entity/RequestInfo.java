package com.se.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.sample.enums.TaskType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "threadInfo")
public class RequestInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private TaskType taskType;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_time")
    @JsonIgnore
    private Date postedAt = new Date();



}
