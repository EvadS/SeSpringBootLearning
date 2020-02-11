package com.hellokoding.springboot.jpa.entity;

import com.hellokoding.springboot.jpa.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;




public interface TagRepository extends JpaRepository<Tag, Long> {
}