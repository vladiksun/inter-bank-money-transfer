package com.transfer.entity;

import com.google.common.base.Objects;

import java.io.Serializable;

public class AccountDetails implements Serializable {

    private static final long serialVersionUID = -6373205543817673651L;

    private Account account;

    private Long customerID;

    public AccountDetails(Account account, Long customerID) {
        this.account = account;
        this.customerID = customerID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDetails that = (AccountDetails) o;
        return Objects.equal(account, that.account) &&
                Objects.equal(customerID, that.customerID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(account, customerID);
    }
}
