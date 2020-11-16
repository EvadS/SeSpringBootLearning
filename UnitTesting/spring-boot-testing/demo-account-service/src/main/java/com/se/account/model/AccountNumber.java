package com.se.account.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.Assert;

public class AccountNumber {

    private final String accountNumber;

    public AccountNumber(String accountNumber) {
        Assert.notNull(accountNumber, "Account Number must not be null");
        Assert.isTrue(accountNumber.length() == 9,
                "Account Number must be exactly 9 characters");
        this.accountNumber = accountNumber;
    }

    @JsonValue
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof com.se.user.service.model.AccountNumber))
            return false;

        com.se.user.service.model.AccountNumber that = (com.se.user.service.model.AccountNumber) o;

        return getAccountNumber() != null ? getAccountNumber().equals(
                that.getAccountNumber()) : that.getAccountNumber() == null;
    }

    @Override
    public int hashCode() {
        return getAccountNumber() != null ? getAccountNumber().hashCode() : 0;
    }
}
