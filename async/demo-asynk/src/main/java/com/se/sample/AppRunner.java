package com.se.sample;

import com.se.sample.model.User;
import com.se.sample.service.GitHubLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final GitHubLookupService gitHubLookupService;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");
        CompletableFuture<User> page4 = gitHubLookupService.findUser("RameshMF");
        CompletableFuture<User> page5 = gitHubLookupService.findUser("skiea");

        // Wait until they are all done
        CompletableFuture.allOf(page1,page2,page3,page4,page5).join();

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("-- 1 >  " + page1.get());
        logger.info("-- 2 >  " + page2.get());
        logger.info("-- 3 >  " + page3.get());
        logger.info("-- 4 >  " + page4.get());
        logger.info("-- 4 >  " + page5.get());

    }

}