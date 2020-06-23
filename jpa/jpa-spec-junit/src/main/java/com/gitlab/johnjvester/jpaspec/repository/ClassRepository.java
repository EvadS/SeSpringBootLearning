package com.gitlab.johnjvester.jpaspec.repository;

import com.gitlab.johnjvester.jpaspec.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long>, JpaSpecificationExecutor {
    List<Class> findAllByNameContainsIgnoreCase(String searchString);
}
