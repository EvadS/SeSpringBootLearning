package com.se.example.domain;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sys_user")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

//        @OneToMany(mappedBy = "user")
//        public List<Address> addresses;


}
