package com.transfer.dao;

import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.ApplicationException;

import java.util.List;

public interface CustomerDao {

    Customer createCustomer(Customer customer);

    Customer getCustomerById(long customerId) throws ApplicationException;

    List<Customer> getCustomersOfAccount(Account account);
}
