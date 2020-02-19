package com.se.sample.controller;

import com.se.sample.config.CounterExecutor;
import com.se.sample.helper.ThreadNameHeleper;

import com.se.sample.service.Counter;
import com.se.sample.service.Decrement;
import com.se.sample.service.Increment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

@RestController("/counter")
public class CounterController {

    private CounterExecutor executorService;
private ThreadNameHeleper threadNameHeleper;


    private ReentrantLock locker = new ReentrantLock();
    private Counter counter = new Counter();

    @Autowired
    public CounterController(@Autowired CounterExecutor executorService, @Autowired ThreadNameHeleper threadNameHeleper) {

        this.executorService = executorService;
        this.threadNameHeleper = threadNameHeleper;
    }



    @GetMapping("/add-increment")
    public ResponseEntity increaseProducer(){
        Runnable decrement = new Increment(counter, locker,threadNameHeleper);
        executorService.addTaskToQueue(decrement);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(String.format(" The response will be HTTP %s  success status.",HttpStatus.CREATED));
    }

    @GetMapping("/add-decrement")
    public ResponseEntity increaseDecrement(){
        Runnable decrement = new Decrement(counter, locker,threadNameHeleper);
        executorService.addTaskToQueue(decrement);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(String.format(" The response will be HTTP %s  success status.",HttpStatus.CREATED));
    }

    @GetMapping("/refresh-counter")
    public ResponseEntity  refreshCounter(@RequestParam("data") int itemid){
        counter.setCounter(itemid);

        counter.enableContinueProducing();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(String.format("Counter %s", counter.getCounterValue()));
    }
}