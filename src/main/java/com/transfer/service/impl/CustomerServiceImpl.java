package com.transfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.CustomerDao;
import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.ApplicationException;
import com.transfer.exception.InvalidParameterException;
import com.transfer.service.AccountService;
import com.transfer.service.CustomerService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@Singleton
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final AccountService accountService;

    @Inject
    public CustomerServiceImpl(final CustomerDao customerDao,
                               final AccountService accountService) {
        this.customerDao = customerDao;
        this.accountService = accountService;
    }

    @Override
    public Customer createCustomer(Customer customer) throws InvalidParameterException {
        validateCustomerFields(customer);
        return customerDao.createCustomer(customer);
    }

    @Override
    public Customer softDeleteCustomer(long customerId) throws ApplicationException {
        throw new RuntimeException("Not supported operation yet.");
    }

    @Override
    public Customer getCustomerById(long customerId) throws ApplicationException {
        return customerDao.getCustomerById(customerId);
    }

    @Override
    public List<Customer> getCustomersOfAccount(String accountNumber) throws ApplicationException {
        Account account = accountService.getAccountDetails(accountNumber);
        return customerDao.getCustomersOfAccount(account);
    }

    @Override
    public Customer getCustomersByFirstAndLastName(String firstName, String lastName) throws ApplicationException {
        throw new RuntimeException("Not supported operation.");
    }

    private void validateCustomerFields(Customer customer) throws InvalidParameterException {
        if (customer == null) {
            throw new InvalidParameterException("Customer details are not specified");
        }

        if (StringUtils.isBlank(customer.getFirstName())) {
            throw new InvalidParameterException("First name must not be null");
        }

        if (StringUtils.isBlank(customer.getLastName())) {
            throw new InvalidParameterException("Last name must not be null");
        }
    }
}
