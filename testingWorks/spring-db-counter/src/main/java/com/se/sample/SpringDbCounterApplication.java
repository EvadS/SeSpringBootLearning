package com.se.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SpringDbCounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDbCounterApplication.class, args);
    }

}
