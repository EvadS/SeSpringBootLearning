package com.se.transaction;


import com.se.transaction.entity.Account;
import com.se.transaction.entity.Wallet;
import com.se.transaction.entity.WalletID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner {

@Autowired
    AccountRepo accountRepo;

@Autowired
    WalletRepo walletRepo;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Wallet wallet =  createWallet(1000l , "XML");

        walletRepo.save(wallet);

        Wallet wallet2 =  createWallet(2000l , "TTT");
        walletRepo.save(wallet2);

//        accountRepo.save(wallet);
 //       entityManager.persistAndFlush(wallet2);

        Account account =new Account();
        account.setWallet(Arrays.asList(wallet,wallet2));

        accountRepo.save(account);

        int a =10;

    }

    private static Wallet createWallet(long userID, String currency) {
        WalletID walletID = new WalletID();
        walletID.setUserID(userID);
        walletID.setWalletCurrency(currency);

        Wallet wallet = new Wallet();
        wallet.setAmount(100);
        wallet.setReference("");
        wallet.setWalletID(walletID);

        return wallet;
    }

}
