package com.se.demoauthinmemory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {

    private final Logger logger = LoggerFactory.getLogger(SecureController.class);

    @GetMapping("/")
    public String test() {
        logger.info("test() in secure controller");
        return "Hi  on secure controller";
    }

}