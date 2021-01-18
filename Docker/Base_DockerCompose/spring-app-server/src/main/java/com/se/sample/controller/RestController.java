package com.se.sample.controller;

import com.se.sample.model.request.BookRequest;
import com.se.sample.service.RestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/book")

public class RestController {
    private final RestService restService;

    @Autowired
    public RestController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/{id}")
    public ResponseEntity onResRequest(@PathVariable("id") String id) {
        Long Id = Long.parseLong(id);
        return ResponseEntity.ok(restService.getBookStats(Id));
    }

    @PostMapping(value ="/")
    public ResponseEntity create(@RequestBody BookRequest book) {
        return ResponseEntity.ok(restService.createBook(book));
    }
}
