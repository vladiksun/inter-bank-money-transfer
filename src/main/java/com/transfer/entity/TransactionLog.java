/*
 * This file is generated by jOOQ.
 */
package com.transfer.entity;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


public class TransactionLog implements Serializable {

    private static final long serialVersionUID = 211247371418598810L;

    private Long       txId;
    private String     accountNumber;
    private BigDecimal amount;
    private BigDecimal balance;
    private Timestamp  timeStamp;

    public TransactionLog() {}

    public TransactionLog(
        String     accountNumber,
        BigDecimal amount,
        BigDecimal balance,
        Timestamp  timeStamp
    ) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.balance = balance;
        this.timeStamp = timeStamp;
    }

    public Long getTxId() {
        return this.txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "TransactionLog{" +
                "txId=" + txId +
                ", accountNumber=" + accountNumber +
                ", amount=" + amount +
                ", balance=" + balance +
                ", timeStamp=" + timeStamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionLog)) return false;
        TransactionLog that = (TransactionLog) o;
        return Objects.equal(txId, that.txId) &&
                Objects.equal(accountNumber, that.accountNumber) &&
                Objects.equal(amount, that.amount) &&
                Objects.equal(balance, that.balance) &&
                Objects.equal(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(txId, accountNumber, amount, balance, timeStamp);
    }
}
