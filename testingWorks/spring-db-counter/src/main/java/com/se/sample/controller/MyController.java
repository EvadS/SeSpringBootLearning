package com.se.sample.controller;

import com.se.sample.config.AppConfig;
import com.se.sample.enums.TaskType;
import com.se.sample.helper.ThreadNameHeleper;
import com.se.sample.service.Counter;
import com.se.sample.service.Decrement;
import com.se.sample.service.Increment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class MyController {

    private static final Logger LOG = LoggerFactory.getLogger(MyController.class);

    private AppConfig executorService;
private ThreadNameHeleper threadNameHeleper;


    ReentrantLock locker = new ReentrantLock();
    Counter counter = new Counter();

    @Autowired
    public MyController(@Autowired AppConfig executorService, @Autowired ThreadNameHeleper threadNameHeleper) {

        this.executorService = executorService;
        this.threadNameHeleper = threadNameHeleper;

    }

//    @GetMapping("/test/{value}")
//    public ResponseEntity<String> get(final @PathVariable("value") String value) {
//        taskExecutor.execute(() -> {
//
//            System.out.println(value);
//            // do something with your String
//            // this will be executed by some worker Thread
//        });
//
//        return ResponseEntity.ok(value);
//    }

    @GetMapping("/add-increment")
    public void increaseProducer(){
        Runnable decrement = new Increment(counter, locker,threadNameHeleper);
        executorService.addTaskToQueue(decrement);
    }

    @GetMapping("/add-decrement")
    public void increaseDecrement(){
        Runnable decrement = new Decrement(counter, locker,threadNameHeleper);
        executorService.addTaskToQueue(decrement);
    }

    @GetMapping("/refresh-counter")
    public int   getItem(@RequestParam("data") int itemid){

        counter.setCounter(itemid);

        counter.enableContinueProducing();

        return counter.getCounterValue();
    }
}