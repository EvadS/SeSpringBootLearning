package com.hellokoding.springboot.jpa.entity;

import com.hellokoding.springboot.jpa.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
