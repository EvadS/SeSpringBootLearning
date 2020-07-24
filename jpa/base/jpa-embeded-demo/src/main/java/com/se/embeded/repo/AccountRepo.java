package com.se.embeded.repo;

import com.se.embeded.entity.Account;
import com.se.embeded.entity.AccountId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, AccountId> {

}