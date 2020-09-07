package com.se.demoauthinmemory.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @GetMapping("/")
    public String getAdmin() {
        logger.info("test() in public  controller");
        return "Hi in index page";
    }
}