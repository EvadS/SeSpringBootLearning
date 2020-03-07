package com.example.filedemo.controller;


import com.example.filedemo.validator.temp.ProductIDExisting;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductAPI {
    @GetMapping("/{id}")
    public void  findById( @PathVariable @ProductIDExisting Long id) {
    int a =10;
    }
}
