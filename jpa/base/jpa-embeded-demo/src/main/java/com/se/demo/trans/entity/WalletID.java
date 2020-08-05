package com.se.demo.trans.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WalletID implements Serializable {

   private String walletCurrency;
   private long employeeKey;

    public WalletID() {
    }

    public String getWalletCurrency() {
        return walletCurrency;
    }

    public void setWalletCurrency(String walletCurrency) {
        this.walletCurrency = walletCurrency;
    }

    public long getEmployeeKey() {
        return employeeKey;
    }

    public void setEmployeeKey(long employeeKey) {
        this.employeeKey = employeeKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletID walletID = (WalletID) o;
        return employeeKey == walletID.employeeKey &&
                Objects.equals(walletCurrency, walletID.walletCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletCurrency, employeeKey);
    }
}
