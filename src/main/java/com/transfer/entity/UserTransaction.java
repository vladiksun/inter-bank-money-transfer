package com.transfer.entity;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserTransaction implements Serializable {

    private static final long serialVersionUID = 8585968271313157737L;

    private BigDecimal amount;

    private String fromAccountNumber;

    private String toAccountNumber;

    public UserTransaction(BigDecimal amount, String fromAccountId, String toAccountId) {
        this.amount = amount;
        this.fromAccountNumber = fromAccountId;
        this.toAccountNumber = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTransaction)) return false;
        UserTransaction that = (UserTransaction) o;
        return Objects.equal(amount, that.amount) &&
                Objects.equal(fromAccountNumber, that.fromAccountNumber) &&
                Objects.equal(toAccountNumber, that.toAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount, fromAccountNumber, toAccountNumber);
    }
}
