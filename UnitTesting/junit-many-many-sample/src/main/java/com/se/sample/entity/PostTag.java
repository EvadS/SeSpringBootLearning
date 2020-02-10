package com.se.sample.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "post_tag")
@IdClass(PostTagId.class)
public class PostTag implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Tag tag;

    @Id
    @ManyToOne
    @JoinColumn
  //  @Column(name = "Post")
    private Post post;


    public PostTag() {
    }

    public PostTag(Tag tag, Post post) {
        this.tag = tag;
        this.post = post;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
