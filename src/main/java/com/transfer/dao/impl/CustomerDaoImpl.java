package com.transfer.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.CustomerDao;
import com.transfer.entity.Customer;
import com.transfer.jooq.stubs.tables.records.CustomerRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.impl.DSL;

import static com.transfer.jooq.stubs.Tables.CUSTOMER;

@Singleton
public class CustomerDaoImpl implements CustomerDao {

    private final Configuration configuration;

    private RecordMapper<Record, Customer> customerMapper = record -> {
        CustomerRecord customerRecord = (CustomerRecord) record;
        return new Customer(customerRecord.getCustomerId(), customerRecord.getFirstName(),
                            customerRecord.getLastName(), customerRecord.getEmail());
    };

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
        customerRecord.store();
        return customerRecord.map(customerMapper);
    }
}
