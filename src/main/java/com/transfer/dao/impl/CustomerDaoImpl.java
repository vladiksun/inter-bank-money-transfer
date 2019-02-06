package com.transfer.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.CustomerDao;
import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.ApplicationException;
import com.transfer.exception.CustomerNotFoundException;
import com.transfer.jooq.stubs.tables.records.CustomerRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Optional;

import static com.transfer.jooq.stubs.Tables.CUSTOMER;
import static com.transfer.jooq.stubs.Tables.CUSTOMER_ACCOUNT_LINK;

@Singleton
public class CustomerDaoImpl implements CustomerDao {

    private final Configuration configuration;

    private RecordMapper<Record, Customer> customerMapper = record ->
            new Customer(record.get(CUSTOMER.CUSTOMER_ID),
                        record.get(CUSTOMER.FIRST_NAME),
                        record.get(CUSTOMER.LAST_NAME),
                        record.get(CUSTOMER.EMAIL));

    @Inject
    public CustomerDaoImpl(final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        DSLContext ctx = DSL.using(configuration);

        CustomerRecord customerRecord = ctx.newRecord(CUSTOMER);
        customerRecord.setFirstName(customer.getFirstName());
        customerRecord.setLastName(customer.getLastName());
        customerRecord.setEmail(customer.getEmail());
        customerRecord.setSoftDeleted(null);
        customerRecord.store();
        return customerRecord.map(customerMapper);
    }

    @Override
    public Customer getCustomerById(long customerId) throws ApplicationException {
        DSLContext ctx = DSL.using(configuration);

        return Optional.ofNullable(ctx.select()
                            .from(CUSTOMER)
                            .where(CUSTOMER.CUSTOMER_ID.eq(customerId))
                            .fetchOne())
                .map(record -> customerMapper.map(record))
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with ID = [ %d ] not found", customerId)));
    }

    @Override
    public List<Customer> getCustomersOfAccount(Account account) {
        DSLContext ctx = DSL.using(configuration);

        return ctx.select(CUSTOMER.fields())
                .from(CUSTOMER)
                .join(CUSTOMER_ACCOUNT_LINK)
                .on(CUSTOMER_ACCOUNT_LINK.CUSTOMER_ID.eq(CUSTOMER.CUSTOMER_ID)
                        .and(CUSTOMER_ACCOUNT_LINK.ACCOUNT_NUMBER.eq(account.getAccountNumber())))
                .where(CUSTOMER.SOFT_DELETED.isNull())
                .fetch().map(customerMapper);
    }

    @Override
    public List<Customer> getCustomersByFirstAndLastName(String firstName, String lastName) {
        DSLContext ctx = DSL.using(configuration);

        return ctx.select()
                .from(CUSTOMER)
                .where(CUSTOMER.FIRST_NAME.like(String.format("%%%s%%", firstName))
                        .and(CUSTOMER.LAST_NAME.like(String.format("%%%s%%", lastName))
                        .and(CUSTOMER.SOFT_DELETED.isNull()))
                ).fetch().map(customerMapper);
    }
}
