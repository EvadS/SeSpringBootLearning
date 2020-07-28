package com.se.additional.repo;

import com.se.additional.entity.Book;
import com.se.enums.mapping.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TransactionRepo extends JpaRepository<com.se.additional.entity.Transaction, String> {

}