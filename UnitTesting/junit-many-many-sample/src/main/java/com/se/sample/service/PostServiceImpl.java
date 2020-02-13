package com.se.sample.service;


import com.se.sample.entity.Post;
import com.se.sample.entity.PostTag;
import com.se.sample.entity.Tag;
import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.model.PostModel;
import com.se.sample.repository.PostRepository;
import com.se.sample.repository.PostTagRepository;
import com.se.sample.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private TagRepository tagRepository;

//    @Autowired
//    private PostTagRepository postTagRepository;

    public PostServiceImpl(@Autowired PostRepository postRepository,
                           @Autowired TagRepository tagRepository
                           ) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post update(Long postId,PostModel postModel) {

       Post post  = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));


        //step 1 is exists



        return null;
    }


    @Override
    public void delete(Long postId) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {
            postRepository.delete(post.get());
        } else {
            new ResourceNotFoundException("PostId " + postId + " not found");
        }
    }

    @Override
    @Transactional//(вариант 1 )
    public Post getPostDetails(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }



    @Transactional
    @Override
    public Post create(PostModel postModel) {
        List<PostTag> postTags = new ArrayList<>();

        for (Tag tag : postModel.getTags()) {


            Tag currentTag = tagRepository.findByName(tag.getName());
            if (currentTag != null) {
                tag.setId(currentTag.getId());
            } else {
                tagRepository.save(tag);
            }

            postTags.add(new PostTag(tag));
        }

        Post post = new Post(postModel.getPost().getTitle(),
                postModel.getPost().getDescription(),
                postModel.getPost().getTitle(),
                postTags);

        return postRepository.save(post);
    }


}
