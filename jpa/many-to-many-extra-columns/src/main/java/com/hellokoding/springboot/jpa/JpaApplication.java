package com.hellokoding.springboot.jpa;

import com.hellokoding.springboot.jpa.book.*;
import com.hellokoding.springboot.jpa.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@RequiredArgsConstructor
@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    @Autowired
    TagRepository tagRepository;

    @Autowired
    PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        // Create a couple of Book, Publisher and BookPublisher
//        Publisher publisherA = new Publisher("Publisher A");
//        Publisher publisherB = new Publisher("Publisher B");
//        publisherRepository.saveAll(Arrays.asList(publisherA, publisherB));
//
//        bookRepository.save(new Book("Book 1",
//                 new BookPublisher(publisherA, new Date()),
//                  new BookPublisher(publisherB, new Date())));
//
//        bookRepository.save(new Book("Book 2", new BookPublisher(publisherA, new Date())));
//        bookRepository.save(new Book("Book 2", new BookPublisher(publisherA, new Date())));
////----------------------------------------------------
//        Tag tag = new Tag(String.format("tag_%s", System.nanoTime()));
//        Tag tag2 = new Tag(String.format("tag_2_%s", System.nanoTime()));
//
//        tagRepository.saveAll(Arrays.asList(tag, tag2));
//
//        String title =  String.format("title_%s", System.nanoTime());
//
//        Post post = new Post(title
//                , new PostTag(tag)
//                , new PostTag(tag2));
//
//        postRepository.save(post);
    }
}
