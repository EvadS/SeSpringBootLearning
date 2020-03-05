package com.hellokoding.springboot.jpa.entity;

import com.hellokoding.springboot.jpa.book.BookPublisher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;



    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private Set<PostTag> posttags = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }

}
