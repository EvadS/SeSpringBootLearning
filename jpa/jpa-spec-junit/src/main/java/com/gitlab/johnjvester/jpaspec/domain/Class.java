package com.gitlab.johnjvester.jpaspec.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Class {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Class() { }
}
