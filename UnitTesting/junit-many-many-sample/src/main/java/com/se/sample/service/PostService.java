package com.se.sample.service;

import com.se.sample.entity.Post;
import com.se.sample.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Post create(PostModel postModel);

    Page<Post> getPosts(Pageable pageable);

    Post update(Long postId,PostModel post);

    void delete(Long postId);

    Post getPostDetails(Long postId);
}
