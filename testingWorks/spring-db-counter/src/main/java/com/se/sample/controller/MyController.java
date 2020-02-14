package com.se.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final TaskExecutor taskExecutor;

    @Autowired
    public MyController(final TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @GetMapping("/test/{value}")
    public ResponseEntity<String> get(final @PathVariable("value") String value) {
        taskExecutor.execute(() -> {

            System.out.println(value);
            // do something with your String
            // this will be executed by some worker Thread
        });

        return ResponseEntity.ok(value);
    }
}