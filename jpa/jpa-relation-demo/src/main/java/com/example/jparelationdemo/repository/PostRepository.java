package com.example.jparelationdemo.repository;

import com.example.jparelationdemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Temporal;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}