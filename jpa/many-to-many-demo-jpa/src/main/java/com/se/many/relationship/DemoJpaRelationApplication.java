package com.se.many.relationship;

import com.se.many.relationship.model.Post;
import com.se.many.relationship.model.Tag;
import com.se.many.relationship.repository.PostRepository;
import com.se.many.relationship.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class DemoJpaRelationApplication implements CommandLineRunner {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;


    public static void main(String[] args) {
        SpringApplication.run(DemoJpaRelationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Cleanup the tables
        postRepository.deleteAllInBatch();
        tagRepository.deleteAllInBatch();

        // =======================================

        // Create a Post
        Post post = new Post("Hibernate Many to Many Example with Spring Boot",
                "Learn how to map a many to many relationship using hibernate",
                "Entire Post content with Sample code");

        // Create two tags
        Tag tag1 = new Tag("Spring Boot");
        Tag tag2 = new Tag("Hibernate");


        // Add tag references in the post
        post.getTags().add(tag1);
        post.getTags().add(tag2);

        // Add post reference in the tags
        tag1.getPosts().add(post);
        tag2.getPosts().add(post);

        postRepository.save(post);

        List<Post> postList = postRepository.findAll();


        List<Tag> tagsList = tagRepository.findAll();
        tagRepository.delete(tag2);

        tagsList = tagRepository.findAll();



        postList = postRepository.findAll();

        int a =10;
        // =======================================
    }
}
