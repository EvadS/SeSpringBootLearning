package com.se.sample.model;

import com.se.sample.entity.Post;
import com.se.sample.entity.Tag;

import java.util.Set;

public class PostModel {

    private Post post;
    private Set<Tag> tags;

    public PostModel(Post post, Set<Tag> tags) {
        this.post = post;
        this.tags = tags;
    }

    public Post getPost() {
        return post;
    }

    public Set<Tag> getTags() {
        return tags;
    }
}
