package com.se.sample.controller;

import com.se.sample.dto.PostDto;
import com.se.sample.entity.Post;
import com.se.sample.entity.PostTag;
import com.se.sample.entity.PostTagRepository;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

@RestController
public class PostController {

    private ModelMapper modelMapper;

    private PostTagRepository postTagRepository;

    private PostRepository postRepository;

    private TagRepository tagRepository;

    public PostController(@Autowired PostRepository postRepository,@Autowired TagRepository tagRepository
            ,@Autowired ModelMapper modelMapper, @Autowired PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
       // this.postTagRepository = postTagRepository;

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

        post.setTitle(String.format("%s", System.nanoTime()));
        Set<PostTag> postTagSet = post.getPostTags();

        //   post = postRepository.save(post);


            Tag tag = tagRepository.findById(1L).get();
            PostTag pt = new PostTag(tag, post);
            //   postTagRepository.save(pt);

//     tag = tagRepository.findById(2L).get();
            //    pt = new PostTag(tag,post);
//    postTagRepository.save(pt);


            post.addTag(pt);

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



//            for(String item : postDto.getTags()){
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


    private Post convertToEntity(PostDto postDto)  {
        Post post = modelMapper.map(postDto, Post.class);




        // ищем по тег нейм


        // оставлено на потом

//        post.setTags(postDto.getTags().stream()
//                .map(tag -> new Tag(tag))
//                .collect(Collectors.toSet()));

        return  post;

    }

    private PostDto convertToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);

        return postDto;
    }
}