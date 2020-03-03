package com.se.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "World!";
        }
        String response = format("Hello, %s", name);
        log.info("Created response '{}'", response);
        return response;
    }
}