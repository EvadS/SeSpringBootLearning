package com.se.sample.entity;


import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostTagId implements Serializable {
    @NotNull
    private Post post;

    @NotNull
    private Tag tag;

    public PostTagId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTagId postTagId = (PostTagId) o;
        return Objects.equals(tag, postTagId.tag) &&
                Objects.equals(post, postTagId.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, post);
    }
}
