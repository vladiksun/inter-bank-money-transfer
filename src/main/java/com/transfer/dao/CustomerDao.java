package com.transfer.dao;

import com.transfer.entity.Customer;

public interface CustomerDao extends Dao {

    Customer createCustomer(Customer customer);
}
