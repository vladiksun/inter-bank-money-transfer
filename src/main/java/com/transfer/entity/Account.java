package com.transfer.entity;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {

    private static final long serialVersionUID = -5300324823123920796L;

    private Long accountNumber;
    private String     type;
    private String     description;
    private BigDecimal balance;
    private String currencyCode;

    public Account() {}

    public Account(Account value) {
        this.accountNumber = value.accountNumber;
        this.type = value.type;
        this.description = value.description;
        this.balance = value.balance;
        this.currencyCode = value.currencyCode;
    }

    public Account(
        Long       accountNumber,
        String     type,
        String     description,
        BigDecimal balance,
        String currencyCode
    ) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.description = description;
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    public Long getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equal(accountNumber, account.accountNumber) &&
                Objects.equal(type, account.type) &&
                Objects.equal(description, account.description) &&
                Objects.equal(balance, account.balance) &&
                Objects.equal(currencyCode, account.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber, type, description, balance, currencyCode);
    }
}
