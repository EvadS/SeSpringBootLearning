package com.example.jparelationdemo;

import com.example.jparelationdemo.entity.Comment;
import com.example.jparelationdemo.entity.Post;
import com.example.jparelationdemo.entity.Task;
import com.example.jparelationdemo.entity.User;
import com.example.jparelationdemo.repository.PostRepository;
import com.example.jparelationdemo.repository.TaskRepository;
import com.example.jparelationdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;


/**
 * keep working
 * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
 */
@EnableJpaAuditing
@SpringBootApplication
public class JpaRelationDemoApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(JpaRelationDemoApplication.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    public static void main(String[] args) {
        SpringApplication.run(JpaRelationDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        testTaskDoubleDirection();
        //TODO: comment to test
        //testPostOneDirection();
    }

    private void testTaskDoubleDirection() {
        User user = new User("login", "name");

        Task task = new Task("task 1 ");
        task.setUser(user);

        Task task2 = new Task("task 2");
        task2.setUser(user);

        Task task3 = new Task("task 3");
        task3.setUser(user);

        user.addTask(task);
        user.addTask(task2);
        user.addTask(task3);

        userRepository.save(user);

        taskRepository.delete(task);
        taskRepository.delete(task2);
        taskRepository.delete(task3);

        userRepository.delete(user);

        //--------------------------------
        Task task4 = new Task("task 4");
        // данный код не пройдет поскольку есть  зависимости между сущностями
        try {
            user = new User("login2", "name2");
            userRepository.save(user);

            task4.setUser(user);
            user.addTask(task4);

            taskRepository.delete(task4);
            userRepository.save(user);

            userRepository.delete(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        List<Task> tasksList = taskRepository.findByUserUserId(user.getUserId());

        for (Task item : tasksList) {
        //    user.removeTask(item);
            taskRepository.delete(item);
        }

        userRepository.delete(user);

        //-----------------
    }

    private void testPostOneDirection() {
        Post post = new Post("post title2");
        Comment comment1 = new Comment("comment1 ");
        Comment comment2 = new Comment("comment2 ");


        post.getComments().add(comment1);
        post.getComments().add(comment2);

        postRepository.save(post);
        List<Post> postList = postRepository.findAll();

        logger.info("Added all.");
        for (Post item : postList) {
            logger.info(String.format("item %s: ", item));
        }

        post.getComments().remove(comment2);
        postRepository.save(post);

        logger.info("Removed comment 2 ");
        for (Post item : postList) {
            logger.info(String.format("item %s: ", item));
        }

        Post currentPost = postRepository.findById(post.getId()).get();
        Comment comment3 = new Comment("comment3 ");
        currentPost.getComments().add(comment3);
        postRepository.save(currentPost);

        logger.info("Added  comment 3");
        for (Post item : postList) {
            logger.info(String.format("item %s: ", item));
        }
    }
}
