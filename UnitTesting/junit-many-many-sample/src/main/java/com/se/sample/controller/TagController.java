package com.se.sample.controller;

import com.se.sample.dto.PostDto;
import com.se.sample.entity.Post;
import com.se.sample.entity.Tag;
import com.se.sample.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/tag")
public class TagController {

    private TagRepository tagRepository;

    public TagController(@Autowired TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }


    @PostMapping("/tag")
    public Tag createPost(@Valid @RequestBody Tag model) {
        return tagRepository.save(model);
    }


}
