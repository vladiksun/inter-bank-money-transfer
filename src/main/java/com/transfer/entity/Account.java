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
    private BigDecimal creditLine;
    private String currencyCode;

    public Account() {}

    public Account(Account value) {
        this.accountNumber = value.accountNumber;
        this.type = value.type;
        this.description = value.description;
        this.balance = value.balance;
        this.creditLine = value.creditLine;
        this.currencyCode = value.currencyCode;
    }

    public Account(
        Long       accountId,
        String     type,
        String     description,
        BigDecimal balance,
        BigDecimal creditLine,
        String currencyCode
    ) {
        this.accountNumber = accountId;
        this.type = type;
        this.description = description;
        this.balance = balance;
        this.creditLine = creditLine;
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

    public BigDecimal getCreditLine() {
        return this.creditLine;
    }

    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Account (");

        sb.append(accountNumber);
        sb.append(", ").append(type);
        sb.append(", ").append(description);
        sb.append(", ").append(balance);
        sb.append(", ").append(creditLine);

        sb.append(")");
        return sb.toString();
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
                Objects.equal(creditLine, account.creditLine) &&
                Objects.equal(currencyCode, account.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber, type, description, balance, creditLine, currencyCode);
    }
}
