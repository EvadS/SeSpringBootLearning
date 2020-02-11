package com.se.sample.repository;

import com.se.sample.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository // extends JpaRepository<PostTag, PostTagId>
{
    PostTag findPostTagByTag (Long id);
}
