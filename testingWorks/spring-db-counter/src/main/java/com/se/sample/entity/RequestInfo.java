package com.se.sample.entity;

import javax.persistence.*;


@Entity
@Table(name = "threadInfo")
public class RequestInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
