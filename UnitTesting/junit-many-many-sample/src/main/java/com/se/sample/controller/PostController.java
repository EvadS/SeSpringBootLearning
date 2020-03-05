package com.se.sample.controller;

import com.se.sample.dto.PostDto;
import com.se.sample.dto.response.PostDetails;
import com.se.sample.dto.response.PostResponse;
import com.se.sample.entity.Post;
import com.se.sample.entity.PostTag;
import com.se.sample.entity.Tag;
import com.se.sample.model.PostModel;
import com.se.sample.service.PostService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PostController {

    private ModelMapper modelMapper;
    private PostService postService;

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

                mappingContext.getSource().getTitle(),
                mappingContext.getSource().getDescription()
        );
    };


    // TODO: по хорошему- для тегов возвращать id/text
    Converter<Post, PostDetails> postDetailsConverter = mappingContext -> {

        Post post = mappingContext.getSource();
        Set<PostTag> postTags =  post.getPostTags();

        List<String> tagsList = postTags.stream().map(x-> x.getTag().getName()).collect(Collectors.toList());

        return new  PostDetails(post.getId(),post.getTitle(), post.getDescription(),
                post.getTitle(), tagsList);

    };




    public PostController(@Autowired ModelMapper modelMapper, @Autowired PostService postService) {

        this.postService = postService;

        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(PostDto.class, PostModel.class).setConverter(PostDtoConverter);
        this.modelMapper.createTypeMap(Post.class, PostResponse.class).setConverter(postResponseConverter);
        this.modelMapper.createTypeMap(Post.class, PostDetails.class).setConverter(postDetailsConverter);
    }

    @GetMapping("/posts")
    public Page<PostResponse> getAllPosts(Pageable pageable) {
        Page<Post> pagedResult = postService.getPosts(pageable);

        List<PostResponse> convertedPagedResult = pagedResult.get()
                .map(x -> modelMapper.map(x, PostResponse.class))
                .collect(Collectors.toList());

        Page<PostResponse> pages = new PageImpl<>(
                convertedPagedResult,
                pageable,
                convertedPagedResult.size());


        return pages;
    }

    @GetMapping("/post/{postId}")
    public PostDetails getPost(@PathVariable Long postId) {
        Post post =  postService.getPostDetails(postId);
        PostDetails postDetails =  modelMapper.map(post, PostDetails.class);
        return postDetails;
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostDto model) {
        PostModel postModel = modelMapper.map(model, PostModel.class);
        Post post = postService.create(postModel);
        PostResponse postResponse = modelMapper.map(post, PostResponse.class);
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponse>  updatePost(@PathVariable Long postId, @Valid @RequestBody PostDto postRequest) {
//        return postRepository.findById(postId).map(post -> {
//            post.setTitle(postRequest.getTitle());
//            post.setDescription(postRequest.getDescription());
//            post.setContent(postRequest.getContent());
//            return postRepository.save(post);
//        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));

        PostModel postModel = modelMapper.map(postRequest, PostModel.class);
        Post post =  postService.update(postId, postModel);

        PostResponse postResponse = modelMapper.map(post, PostResponse.class);
        return ResponseEntity.ok(postResponse);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }
}