package com.transfer.service.impl;

import com.google.inject.Inject;
import com.transfer.exception.CustomerNotFoundException;
import com.transfer.exception.InvalidParameterException;
import com.transfer.entity.Customer;
import com.transfer.jooq.stubs.tables.records.CustomerRecord;
import com.transfer.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.util.List;

import static com.transfer.jooq.stubs.Tables.CUSTOMER;

@Singleton
public class CustomerServiceImpl implements CustomerService {

    private final DataSource dataSource;

    @Inject
    public CustomerServiceImpl(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Customer createCustomer(Customer customer) throws InvalidParameterException {
        validateCustomerFields(customer);

        DSLContext ctx = DSL.using(dataSource, SQLDialect.DEFAULT);
        CustomerRecord customerRecord = ctx.newRecord(CUSTOMER);
        customerRecord.setFirstName(customer.getFirstName());
        customerRecord.setLastName(customer.getLastName());
        customerRecord.setEmail(customer.getEmail());
        customerRecord.store();

        String s = customerRecord.formatJSON();

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

    private void validateCustomerFields(Customer customer) throws InvalidParameterException {
        if (StringUtils.isBlank(customer.getFirstName())) {
            throw new InvalidParameterException("First name must not be null");
        }

        if (StringUtils.isBlank(customer.getLastName())) {
            throw new InvalidParameterException("Last name must not be null");
        }
    }
}
