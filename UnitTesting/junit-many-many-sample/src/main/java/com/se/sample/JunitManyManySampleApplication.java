package com.se.sample;

import com.se.sample.entity.Post;
import com.se.sample.entity.PostTag;
import com.se.sample.entity.Tag;
import com.se.sample.repository.PostRepository;
import com.se.sample.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class JunitManyManySampleApplication implements CommandLineRunner {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostRepository postRepository;


    public static void main(String[] args) {
        SpringApplication.run(JunitManyManySampleApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
//        Tag tag = new Tag(String.format("tag_%s", System.nanoTime()));
//        Tag tag2 = new Tag(String.format("tag_2_%s", System.nanoTime()));
//
//        tagRepository.saveAll(Arrays.asList(tag, tag2));
//
//       String title =  String.format("title_%s", System.nanoTime());
//       String description =  String.format("title_%s", System.nanoTime());
//
//       Post post = new Post(title,description,"content"
//               , new PostTag(tag)
//               , new PostTag(tag2));
//
//        postRepository.save(post);
//
//        title =  String.format("title_%s", System.nanoTime());
//        post = new Post(title,description,"content"
//                , new PostTag(tag)
//                , new PostTag(tag2));
//
//        postRepository.save(post);

    }


}
