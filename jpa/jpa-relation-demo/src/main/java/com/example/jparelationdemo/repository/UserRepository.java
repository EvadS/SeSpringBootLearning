package com.example.jparelationdemo.repository;

import com.example.jparelationdemo.entity.Task;
import com.example.jparelationdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}