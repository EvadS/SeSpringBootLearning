package com.se.transaction;

import com.se.transaction.entity.Wallet;
import com.se.transaction.entity.WalletID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet, WalletID> {
}
