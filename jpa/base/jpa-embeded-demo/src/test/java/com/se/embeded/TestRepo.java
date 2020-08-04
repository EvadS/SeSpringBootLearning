package com.se.embeded;

import com.se.transaction.entity.Account;
import com.se.transaction.entity.Wallet;
import com.se.transaction.entity.WalletID;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestRepo {

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public  void testtest(){
       Wallet wallet =  createWallet(1000l , "XML");
       Wallet wallet2 =  createWallet(2000l , "TTT");

        entityManager.persistAndFlush(wallet);
        entityManager.persistAndFlush(wallet2);

        Account account =new Account();
        account.setWallet(Arrays.asList(wallet,wallet2));

        entityManager.persistAndFlush(account);

        int a =10;
    }

    private Wallet createWallet(long userID, String currency) {
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
