package com.se.sample.service;


import com.se.sample.entity.Post;
import com.se.sample.entity.PostTag;
import com.se.sample.entity.Tag;
import com.se.sample.model.PostModel;
import com.se.sample.repository.PostRepository;
import com.se.sample.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private TagRepository tagRepository;

    public PostServiceImpl(@Autowired PostRepository postRepository, @Autowired TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post getPostById(Long postId) {
        return postRepository.getOne(postId);
    }


    @Override
    public Post create(PostModel postModel) {
        List<PostTag> postTags = new ArrayList<>();

        for (Tag tag : postModel.getTags()) {
            tagRepository.save(tag);

            postTags.add(new PostTag(tag));
        }

        Post post = new Post(postModel.getPost().getTitle(),
                postModel.getPost().getDescription(),
                postModel.getPost().getTitle(),
                postTags);

        return postRepository.save(post);
    }
}
