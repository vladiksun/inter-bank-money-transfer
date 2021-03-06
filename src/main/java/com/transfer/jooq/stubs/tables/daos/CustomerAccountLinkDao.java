/*
 * This file is generated by jOOQ.
 */
package com.transfer.jooq.stubs.tables.daos;


import com.transfer.jooq.stubs.tables.CustomerAccountLink;
import com.transfer.jooq.stubs.tables.records.CustomerAccountLinkRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.Record2;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerAccountLinkDao extends DAOImpl<CustomerAccountLinkRecord, com.transfer.jooq.stubs.tables.pojos.CustomerAccountLink, Record2<Long, String>> {

    /**
     * Create a new CustomerAccountLinkDao without any configuration
     */
    public CustomerAccountLinkDao() {
        super(CustomerAccountLink.CUSTOMER_ACCOUNT_LINK, com.transfer.jooq.stubs.tables.pojos.CustomerAccountLink.class);
    }

    /**
     * Create a new CustomerAccountLinkDao with an attached configuration
     */
    public CustomerAccountLinkDao(Configuration configuration) {
        super(CustomerAccountLink.CUSTOMER_ACCOUNT_LINK, com.transfer.jooq.stubs.tables.pojos.CustomerAccountLink.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Record2<Long, String> getId(com.transfer.jooq.stubs.tables.pojos.CustomerAccountLink object) {
        return compositeKeyRecord(object.getCustomerId(), object.getAccountNumber());
    }

    /**
     * Fetch records that have <code>CUSTOMER_ID IN (values)</code>
     */
    public List<com.transfer.jooq.stubs.tables.pojos.CustomerAccountLink> fetchByCustomerId(Long... values) {
        return fetch(CustomerAccountLink.CUSTOMER_ACCOUNT_LINK.CUSTOMER_ID, values);
    }

    /**
     * Fetch records that have <code>ACCOUNT_NUMBER IN (values)</code>
     */
    public List<com.transfer.jooq.stubs.tables.pojos.CustomerAccountLink> fetchByAccountNumber(String... values) {
        return fetch(CustomerAccountLink.CUSTOMER_ACCOUNT_LINK.ACCOUNT_NUMBER, values);
    }
}
