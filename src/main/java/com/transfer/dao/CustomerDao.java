package com.transfer.dao;

import com.transfer.entity.Customer;

public interface CustomerDao {

    Customer createCustomer(Customer customer);

    Customer getCustomerById(long customerId);
}
