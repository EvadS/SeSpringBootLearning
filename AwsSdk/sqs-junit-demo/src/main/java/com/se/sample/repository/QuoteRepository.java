package com.se.sample.repository;

import com.se.sample.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
    boolean existsByAwsMessageId(String awsMessageId);
}