package com.se.sample.repository;


import com.se.sample.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select u from #{#entityName} u where u.id= ?1")
    Post getById(Long postId);
}