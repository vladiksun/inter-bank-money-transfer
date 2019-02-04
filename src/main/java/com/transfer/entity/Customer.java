package com.transfer.entity;


import com.google.common.base.Objects;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 888135670889399684L;

    private Long   customerId;
    private String firstName;
    private String lastName;
    private String email;

    public Customer() {}

    public Customer(Customer value) {
        this.customerId = value.customerId;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.email = value.email;
    }

    public Customer(
        Long   customerId,
        String firstName,
        String lastName,
        String email
    ) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Customer (");

        sb.append(customerId);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(email);

        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equal(customerId, customer.customerId) &&
                Objects.equal(firstName, customer.firstName) &&
                Objects.equal(lastName, customer.lastName) &&
                Objects.equal(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(customerId, firstName, lastName, email);
    }
}
