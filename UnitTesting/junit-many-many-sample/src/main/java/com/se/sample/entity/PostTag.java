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
    private Post post;

    @Id
    @ManyToOne
    @JoinColumn
    private Tag tag;

    public PostTag() {
    }

    public PostTag(Tag tag) {
        this.tag = tag;
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
