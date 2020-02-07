package com.se.sample.controller;

import com.se.sample.dto.PostDto;
import com.se.sample.entity.Post;
import com.se.sample.entity.Tag;
import com.se.sample.exception.ResourceNotFoundException;
import com.se.sample.repository.PostRepository;
import com.se.sample.repository.TagRepository;
import com.se.sample.service.PostService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {


    private ModelMapper modelMapper;



    private PostRepository postRepository;

    private TagRepository tagRepository;

    public PostController(@Autowired PostRepository postRepository,@Autowired TagRepository tagRepository,@Autowired ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;

        this.modelMapper = modelMapper;

        this.modelMapper.createTypeMap(PostDto.class,Post.class).setConverter(converter);
    }

    @Autowired
    private PostService postervice;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postervice.getPosts(pageable);
    }

    @GetMapping("/post/{postId}")
    public void  getPost(@PathVariable Long postId){

    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody PostDto  model) {

        Post post = modelMapper.map(model, Post.class);
        return postRepository.save(post);
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


    Converter<PostDto, Post> converter = new Converter<PostDto, Post>() {

        @Override
        public Post convert(MappingContext<PostDto, Post> mappingContext) {
            Post post = new Post();

            PostDto postDto = mappingContext.getSource();
            post.setContent(postDto.getContent());
            post.setDescription(postDto.getDescription());
            post.setTitle(postDto.getTitle());

            for (String item : postDto.getTags()) {
                Tag tagEntity = tagRepository.findByName(item);

                if (tagEntity == null) {
                    tagEntity = new Tag(item);
                }

                post.addTag(tagEntity);
            }

            return post;
        }
    };


//    private Post convertToEntity(PostDto postDto)  {
//        Post post = modelMapper.map(postDto, Post.class);
//
//
//
//
//        // ищем по тег нейм
//
//
//        // оставлено на потом
//
////        post.setTags(postDto.getTags().stream()
////                .map(tag -> new Tag(tag))
////                .collect(Collectors.toSet()));
//
//        return  post;
//
//    }

    private PostDto convertToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);

        return postDto;
    }

}