package com.se.transaction.entity;

import com.se.transaction.WalletSystem;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "wallet")
@Table(name = "wallet")

public class Wallet {
    @EmbeddedId
    private WalletID walletID;

    //@Type(type = "org.hibernate.type.TextType")
    @Column(length = 1024)
    private  String name;

    private double amount;

    private String reference;

    public Wallet() {
    }

    public WalletID getWalletID() {
        return walletID;
    }

    public void setWalletID(WalletID walletID) {
        this.walletID = walletID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
