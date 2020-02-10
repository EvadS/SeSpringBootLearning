package com.se.sample;

import com.se.sample.entity.Tag;
import com.se.sample.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JunitManyManySampleApplication implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(JunitManyManySampleApplication.class, args);
    }

    @Autowired
    TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
    Tag tag = new Tag("1112111111111");

    tagRepository.save(tag);
    }
}
