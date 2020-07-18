package com.se.many.to.many;

import com.se.many.to.many.entity.Post;
import com.se.many.to.many.entity.Student;
import com.se.many.to.many.entity.Tag;
import com.se.many.to.many.entity.University;
import com.se.many.to.many.repo.PostRepository;
import com.se.many.to.many.repo.StudentRepository;
import com.se.many.to.many.repo.TagRepository;
import com.se.many.to.many.repo.UniversityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class ManyToManyApplication implements CommandLineRunner {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(ManyToManyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      //  testStudentUniversity();


        // Cleanup the tables
        postRepository.deleteAllInBatch();
        tagRepository.deleteAllInBatch();

        // Create a Post
        Post post = new Post("Hibernate Many to Many Example with Spring Boot");

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

        //смотрим в бд
        int a =10;
        System.out.println("*** Current tags ****************");
        List<Tag> tagList = tagRepository.findAll();
        tagList.stream().forEach(System.out::println);


        System.out.println("*** Deleted tag  " + tag1.getId() );


        // нихера не удалется
        postRepository.delete(post);

        System.out.println("*** Current tags ****************");
        tagList = tagRepository.findAll();
        tagList.stream().forEach(System.out::println);


        // Post является базовой    @JoinTable
        // поэтому для удаления связей с Tag в промежуточной таблице
        post.getTags().clear();
        postRepository.delete(post);

        // Дубль 2


        int aa =10;
    }

    private void testStudentUniversity() {
        // Clean up database tables
        universityRepository.deleteAllInBatch();
        studentRepository.deleteAllInBatch();


        University university = new University();
        university.setName("KPI");
        universityRepository.save(university);

        Student student = new Student();
        student.setName("Student name.");
        student.setUniversities(Arrays.asList(university));
        studentRepository.save(student);

        Student student2 = new Student();
        student2.setName("Student name 2.");
        student2.setUniversities(Arrays.asList(university));
        studentRepository.save(student2);

        System.out.println("=== Current university");
        universityRepository.findAll().stream().forEach(System.out::println);

        System.out.println("=== Current students ");
        studentRepository.findAll().stream().forEach(System.out::println);

        studentRepository.delete(student2);
        System.out.println("=== removed (2) Current students ");
        studentRepository.findAll().stream().forEach(System.out::println);

        student2 = new Student();
        student2.setName("Student name 3.");
        student2.setUniversities(Arrays.asList(university));
        studentRepository.save(student2);

        System.out.println("=== added (3)Current students ");
        studentRepository.findAll().stream().forEach(System.out::println);
    }
}
