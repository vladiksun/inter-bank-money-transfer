package com.transfer.service.impl;

import com.transfer.exception.CustomerNotFoundException;
import com.transfer.exception.InvalidParameterException;
import com.transfer.jooq.stubs.tables.pojos.Customer;
import com.transfer.service.CustomerService;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer createCustomer(Customer customer) throws InvalidParameterException {
        return customer;
    }

    @Override
    public void removeCustomer(long customerId) throws CustomerNotFoundException, InvalidParameterException {

    }

    @Override
    public Customer getCustomerById(long customerId) throws CustomerNotFoundException, InvalidParameterException {
        return null;
    }

    @Override
    public List<Customer> getCustomersByAccount(long accountId) throws CustomerNotFoundException, InvalidParameterException {
        return null;
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) throws InvalidParameterException {
        return null;
    }
}
