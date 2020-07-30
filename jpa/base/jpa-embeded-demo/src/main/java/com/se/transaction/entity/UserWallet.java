package com.se.transaction.entity;

import com.se.transaction.WalletSystem;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserWallet {
    @EmbeddedId
    private WalletID walletID;

    private WalletSystem walletSystem;
    private String reference;

    public UserWallet() {
    }

    public WalletID getWalletID() {
        return walletID;
    }

    public void setWalletID(WalletID walletID) {
        this.walletID = walletID;
    }

    public WalletSystem getWalletSystem() {
        return walletSystem;
    }

    public void setWalletSystem(WalletSystem walletSystem) {
        this.walletSystem = walletSystem;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
