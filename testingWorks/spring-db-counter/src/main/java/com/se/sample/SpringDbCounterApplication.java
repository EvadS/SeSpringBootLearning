package com.se.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SpringDbCounterApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringDbCounterApplication.class);


    @Autowired
    private Environment env;

    public static void main(String[] args) {


        SpringApplication.run(SpringDbCounterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("* DB_PASSWORD : {}", env.getProperty("DB_PASSWORD"));

        int a =0;
    }

}
