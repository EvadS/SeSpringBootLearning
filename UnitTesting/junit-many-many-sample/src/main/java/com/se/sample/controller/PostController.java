package com.se.sample.controller;

import com.se.sample.dto.PostDto;
import com.se.sample.dto.response.PostResponse;
import com.se.sample.entity.Post;
import com.se.sample.entity.Tag;
import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.model.PostModel;
import com.se.sample.repository.PostRepository;
import com.se.sample.service.PostService;
import com.se.sample.service.PostServiceImpl;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PostController {

    Converter<PostDto, PostModel> PostDtoConverter = mappingContext -> {
        Post post = new Post();

        PostDto postDto = mappingContext.getSource();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());

        Set<Tag> tags = postDto.getTags().stream()
                .map(tag -> new Tag(tag))
                .collect(Collectors.toSet());

        return new PostModel(post, tags);
    };


    Converter<Post, PostResponse> postResponseConverter = mappingContext -> {

        return new PostResponse(mappingContext.getSource().getId(),
                mappingContext.getSource().getContent(),
                mappingContext.getSource().getTitle()
        );
    };


    // private PostTagRepository postTagRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;


    private PostService postService;

    @Autowired
    private PostServiceImpl postervice;

    public PostController(@Autowired PostRepository postRepository
            , @Autowired ModelMapper modelMapper, @Autowired PostService postService) {
        this.postRepository = postRepository;

        this.postService = postService;

        this.modelMapper = modelMapper;

        this.modelMapper.createTypeMap(PostDto.class, PostModel.class).setConverter(PostDtoConverter);
        this.modelMapper.createTypeMap(Post.class, PostResponse.class).setConverter(postResponseConverter);

    }

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postervice.getPosts(pageable);
    }

    @GetMapping("/post/{postId}")
    public void getPost(@PathVariable Long postId) {

    }

    @PostMapping("/post-base")
    public ResponseEntity<Post> create(@Valid @RequestBody Post post) {
        return ResponseEntity.ok(postRepository.save(post));
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostDto model) {

        PostModel postModel = modelMapper.map(model, PostModel.class);
        Post post = postService.create(postModel);
        PostResponse postResponse = modelMapper.map(post,PostResponse.class);
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}