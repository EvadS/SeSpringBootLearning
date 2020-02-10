package com.se.sample.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository  extends JpaRepository<PostTag, PostTagId>
{
    PostTag findPostTagByTag (Long id);
}
