package com.se.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SpringDbCounterApplication {

    public static void main(String[] args) {

        LocalDateTime dateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy__HH:mm:ss");
        System.out.println(dateTime.format(formatter));

        SpringApplication.run(SpringDbCounterApplication.class, args);
    }

}
