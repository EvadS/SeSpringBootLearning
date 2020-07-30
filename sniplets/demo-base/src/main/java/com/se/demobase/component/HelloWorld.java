package com.se.demobase.component;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HelloWorld {
    @PostConstruct
    public void sayHello() {
        System.out.println("Hello World Component, from Spring Boot 2!");
    }
}

