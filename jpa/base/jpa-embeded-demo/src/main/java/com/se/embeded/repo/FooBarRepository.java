package com.se.embeded.repo;

import com.se.embeded.model.FooBar;
import com.se.embeded.model.FooBarType;
import com.se.embeded.model.PrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooBarRepository  extends JpaRepository<FooBar, com.se.embeded.model.PrimaryKey> {

}