package com.transfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.CustomerDao;
import com.transfer.entity.Customer;
import com.transfer.exception.ApplicationException;
import com.transfer.exception.InvalidParameterException;
import com.transfer.service.CustomerService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@Singleton
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Inject
    public CustomerServiceImpl(final CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer createCustomer(Customer customer) throws InvalidParameterException {
        validateCustomerFields(customer);
        return customerDao.createCustomer(customer);
    }

    @Override
    public void removeCustomer(long customerId) throws ApplicationException {

    }

    @Override
    public Customer getCustomerById(long customerId) throws ApplicationException {
        return null;
    }

    @Override
    public List<Customer> getCustomersByAccount(long accountId) throws ApplicationException {
        return null;
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) throws ApplicationException {
        return null;
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
