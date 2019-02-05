package com.transfer.entity;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserTransaction implements Serializable {

    private static final long serialVersionUID = 8585968271313157737L;

    private BigDecimal amount;

    private Long fromAccountNumber;

    private Long toAccountNumber;

    public UserTransaction(BigDecimal amount, Long fromAccountId, Long toAccountId) {
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

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
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
