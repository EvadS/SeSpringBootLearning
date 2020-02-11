package com.se.sample.controller;

import com.se.sample.dto.PostDto;
import com.se.sample.entity.Post;
import com.se.sample.entity.PostTag;
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
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class PostController {

    Converter<PostDto, Post> converter = new Converter<PostDto, Post>() {

        @Override
        public Post convert(MappingContext<PostDto, Post> mappingContext) {
            Post post = new Post();

            PostDto postDto = mappingContext.getSource();
            post.setContent(postDto.getContent());
            post.setDescription(postDto.getDescription());
            post.setTitle(postDto.getTitle());


            for (String item : postDto.getTags()) {
                Tag tag = new Tag(item);

                PostTag postTag = new PostTag(tag);
                post.buildPostTags(postTag);

            }
//
//                Tag existsTag = tagRepository.findByName(item);
//
//                if(existsTag  != null){
//
//                    PostTag postTag = postTagRepository.findPostTagByTag(existsTag.getId());
//                    post.addTag(postTag);
//                }
//
//                else {
//                   Tag tag = new Tag(item);
//                   tagRepository.save(tag);
//                   PostTag postTag = new PostTag();
//                   postTag.setTag(tag);
//
//                   post.addTag(postTag);
//                }
//            }

            return post;
        }
    };

    // private PostTagRepository postTagRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;
    private TagRepository tagRepository;

    @Autowired
    private PostService postervice;

    public PostController(@Autowired PostRepository postRepository, @Autowired TagRepository tagRepository
            , @Autowired ModelMapper modelMapper/*, @Autowired PostTagRepository postTagRepository*/) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        // this.postTagRepository = postTagRepository;

        this.modelMapper = modelMapper;

        this.modelMapper.createTypeMap(PostDto.class, Post.class).setConverter(converter);
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
    public boolean createPost(@Valid @RequestBody PostDto model) {

        //    Post post = modelMapper.map(model, Post.class);
//        Tag tag = new Tag(String.format("tag_%s", System.nanoTime()));
//        Tag tag2 = new Tag(String.format("tag_2_%s", System.nanoTime()));
//
//        tagRepository.saveAll(Arrays.asList(tag, tag2));
//
        String title = String.format("title_%s", System.nanoTime());
        String description = String.format("title_%s", System.nanoTime());
//
//        Post post2 = new Post(title, description, "content"
//                , new PostTag(tag)
//                , new PostTag(tag2));

       Collection<Tag> collection =  model.getTags().stream()
                .map(tag -> new Tag(tag))
                .collect(Collectors.toSet());

       List<PostTag> postTags = new ArrayList<>();
       for(Tag tag: collection){
           tagRepository.save(tag);

           postTags.add(new PostTag(tag));
       }

        Post post2 = new Post(title, description, "content",  postTags);
        postRepository.save(post2);

        return true;
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

    private Post convertToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);


        // ищем по тег нейм


        // оставлено на потом

//        post.setTags(postDto.getTags().stream()
//                .map(tag -> new Tag(tag))
//                .collect(Collectors.toSet()));

        return post;

    }

    private PostDto convertToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);

        return postDto;
    }
}