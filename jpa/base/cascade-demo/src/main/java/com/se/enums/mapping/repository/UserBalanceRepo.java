package com.se.enums.mapping.repository;

import com.se.enums.mapping.model.UserBalance;
import com.se.enums.mapping.model.UserBalanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepo extends JpaRepository<UserBalance, UserBalanceId> {
}
