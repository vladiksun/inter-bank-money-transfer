/*
 * This file is generated by jOOQ.
 */
package com.transfer.entity;


import com.google.common.base.Objects;

import java.io.Serializable;


public class CustomerAccountLink implements Serializable {

    private static final long serialVersionUID = 528322462;

    private Long customerId;
    private Long accountId;

    public CustomerAccountLink() {}

    public CustomerAccountLink(CustomerAccountLink value) {
        this.customerId = value.customerId;
        this.accountId = value.accountId;
    }

    public CustomerAccountLink(
        Long customerId,
        Long accountId
    ) {
        this.customerId = customerId;
        this.accountId = accountId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomerAccountLink (");

        sb.append(customerId);
        sb.append(", ").append(accountId);

        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerAccountLink)) return false;
        CustomerAccountLink that = (CustomerAccountLink) o;
        return Objects.equal(customerId, that.customerId) &&
                Objects.equal(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customerId, accountId);
    }
}