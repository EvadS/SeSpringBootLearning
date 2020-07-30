package com.se.transaction.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class WalletID   implements Serializable {

   private String walletCurrency;
 private String userID;

    public WalletID() {
    }

    public String getWalletCurrency() {
        return walletCurrency;
    }

    public void setWalletCurrency(String walletCurrency) {
        this.walletCurrency = walletCurrency;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
