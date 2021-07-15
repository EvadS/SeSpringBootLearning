package com.se.prooduct.service.domain;

import com.se.prooduct.service.validation.annotation.NullOrNotBlank;

import javax.persistence.*;

public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "PRODUCT_NAME", unique = true)
    @NullOrNotBlank(message = "Product name can not be blank")
    private String name;

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
}
