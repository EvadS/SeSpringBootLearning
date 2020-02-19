package com.se.sample.config;

import com.se.sample.enums.TaskType;
import com.se.sample.service.Counter;
import com.se.sample.service.Decrement;
import com.se.sample.service.Increment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AppConfig {
    private ExecutorService executorService;


    @PostConstruct
    public void init() {
        int cores = Runtime.getRuntime().availableProcessors();
        executorService =  Executors.newFixedThreadPool(cores);
    }

    @PreDestroy
    public void cleanup() {
        executorService.shutdown();
    }


    public void addTaskToQueue (Runnable task){
        executorService.submit(task);
    }
}


