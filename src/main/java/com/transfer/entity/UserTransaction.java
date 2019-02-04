package com.transfer.entity;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserTransaction implements Serializable {

    private static final long serialVersionUID = 8585968271313157737L;

    private BigDecimal amount;

    private Long fromAccountId;

    private Long toAccountId;

    public UserTransaction(BigDecimal amount, Long fromAccountId, Long toAccountId) {
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTransaction)) return false;
        UserTransaction that = (UserTransaction) o;
        return Objects.equal(amount, that.amount) &&
                Objects.equal(fromAccountId, that.fromAccountId) &&
                Objects.equal(toAccountId, that.toAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount, fromAccountId, toAccountId);
    }
}
