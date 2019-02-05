/*
 * This file is generated by jOOQ.
 */
package com.transfer.entity;


import com.google.common.base.Objects;

import java.io.Serializable;


public class CustomerAccountLink implements Serializable {

    private static final long serialVersionUID = 528322462;

    private Long customerId;
    private String accountNumber;

    public CustomerAccountLink() {}

    public CustomerAccountLink(CustomerAccountLink value) {
        this.customerId = value.customerId;
        this.accountNumber = value.accountNumber;
    }

    public CustomerAccountLink(
        Long customerId,
        String accountNumber
    ) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomerAccountLink (");

        sb.append(customerId);
        sb.append(", ").append(accountNumber);

        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerAccountLink)) return false;
        CustomerAccountLink that = (CustomerAccountLink) o;
        return Objects.equal(customerId, that.customerId) &&
                Objects.equal(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customerId, accountNumber);
    }
}
