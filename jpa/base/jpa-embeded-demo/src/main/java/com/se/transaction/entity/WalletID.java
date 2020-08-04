package com.se.transaction.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class WalletID implements Serializable {

   private String walletCurrency;
   private long userID;

    public WalletID() {
    }

    public String getWalletCurrency() {
        return walletCurrency;
    }

    public void setWalletCurrency(String walletCurrency) {
        this.walletCurrency = walletCurrency;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
