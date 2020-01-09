package com.se.sample.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * Created by JavaDeveloperZone on 19-07-2017.
 */
@RestController
@RequestMapping("/profile")
public class ProfileCheckController {

    private static final Logger logger = Logger.getLogger(ProfileCheckController.class.getName());

    @Autowired
    private Environment environment;

    @GetMapping("/checkProfile")
    public String checkProfile() {
        return "Spring Boot is running under "+environment.getActiveProfiles()[0] + " Profile";
    }
}