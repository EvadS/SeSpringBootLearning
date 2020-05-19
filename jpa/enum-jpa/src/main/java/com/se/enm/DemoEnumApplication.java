package com.se.enm;

import com.se.enm.enums.Category;
import com.se.enm.enums.Priority;
import com.se.enm.enums.Type;
import com.se.enm.model.Article;
import com.se.enm.enums.Status;
import com.se.enm.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoEnumApplication  implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DemoEnumApplication.class);


    @Autowired
    public ArticleRepository articleRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoEnumApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Article article = new Article();
//        article.setId(1);
        article.setTitle("ordinal title");
        article.setStatus(Status.OPEN);
        article.setType(Type.EXTERNAL);

        article.setPriority(Priority.HIGH);

        article.setCategory(Category.MUSIC);

        articleRepository.save(article);
        articleRepository.save(article);

        List<Article> articlesList = articleRepository.findAll();

        logger.info("Current count {}", articlesList.size());
        int a =10;
    }
}
