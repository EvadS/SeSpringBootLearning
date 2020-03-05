package com.hellokoding.springboot.jpa.entity;

import com.hellokoding.springboot.jpa.book.BookPublisher;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostTag> postTags;

    public Post(String name, PostTag... bookPublishers) {
        this.title = name;
        for(PostTag bookPublisher : bookPublishers) {
            bookPublisher.setPost(this);
        }
        this.postTags = Stream.of(bookPublishers).collect(Collectors.toSet());
    }

}
