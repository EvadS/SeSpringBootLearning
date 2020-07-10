package com.se.one.to.many.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Evgeniy Skiba on 09.07.2020
 * @project cascade-demo
 */
@Entity
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;
    private String street;
    private String building;

    public Address() {
    }

    @OneToMany(mappedBy="address", fetch= FetchType.EAGER)
    private Collection<Person> tenants;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Collection<Person> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<Person> tenants) {
        this.tenants = tenants;
    }
}
