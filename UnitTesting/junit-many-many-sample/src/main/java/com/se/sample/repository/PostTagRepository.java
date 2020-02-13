package com.se.sample.repository;

import com.se.sample.entity.PostTag;
import com.se.sample.entity.PostTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
public interface PostTagRepository extends JpaRepository<PostTag, PostTagId>
{
    List<PostTag> findAllByPostId(Long id);

}
