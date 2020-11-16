package com.se.account.model;

import javax.persistence.*;

@Entity
public class Customer extends BaseEntity {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private com.se.user.service.model.Account account;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email,
                    com.se.user.service.model.Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public com.se.user.service.model.Account getAccount() {
        return account;
    }

    public void setAccount(com.se.user.service.model.Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\'' + ", email='" + email + '\''
                + ", account=" + account + "} " + super.toString();
    }
}