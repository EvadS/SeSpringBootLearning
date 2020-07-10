package com.se.one.to.many.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Evgeniy Skiba on 09.07.2020
 * @project cascade-demo
 */
//the child entity
@Entity
@Table(name="users")
public class Person
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address address;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
