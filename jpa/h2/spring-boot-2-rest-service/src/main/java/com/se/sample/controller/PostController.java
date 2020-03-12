package com.se.sample.controller;


import com.se.sample.entity.Post;
import com.se.sample.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    final private PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public List<Post> retrieveAllStudents() {

        return postRepository.findAll();
    }


    @PostMapping("/create")
    public ResponseEntity createPost(@Valid @RequestBody Post post) {
        // TODO:

        return ResponseEntity.ok(postRepository.save(post));
    }

    // TODO:
    @PutMapping("/update")
    public ResponseEntity updatePost(@Valid @RequestBody Post post) {
        Post item = postRepository.findById(post.getId()).orElseThrow(
                () -> new PostNotFoundException(String.format("Post with id %s not found ", post.getId())));

        item.setAutor(post.getAutor());
        item.setScore(post.getScore());
        item.setTitle(post.getTitle());

        return ResponseEntity.ok(postRepository.save(item));
    }


    @RequestMapping(value = "/", params = "id", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@NotNull @RequestParam("id") Long postId) {
        Post item = postRepository.findById(postId).orElseThrow(
                () -> new PostNotFoundException(String.format("Post with id %s not found ", postId)));
        postRepository.delete(item);
        return ResponseEntity.ok().build();
    }

}
