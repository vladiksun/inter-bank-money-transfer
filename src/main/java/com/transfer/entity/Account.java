package com.transfer.entity;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {

    private static final long serialVersionUID = 102294315;

    private Long       accountId;
    private String     type;
    private String     description;
    private BigDecimal balance;
    private BigDecimal creditLine;

    public Account() {}

    public Account(Account value) {
        this.accountId = value.accountId;
        this.type = value.type;
        this.description = value.description;
        this.balance = value.balance;
        this.creditLine = value.creditLine;
    }

    public Account(
        Long       accountId,
        String     type,
        String     description,
        BigDecimal balance,
        BigDecimal creditLine
    ) {
        this.accountId = accountId;
        this.type = type;
        this.description = description;
        this.balance = balance;
        this.creditLine = creditLine;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Account (");

        sb.append(accountId);
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
        return Objects.equal(accountId, account.accountId) &&
                Objects.equal(type, account.type) &&
                Objects.equal(description, account.description) &&
                Objects.equal(balance, account.balance) &&
                Objects.equal(creditLine, account.creditLine);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountId, type, description, balance, creditLine);
    }
}
