/*
 * This file is generated by jOOQ.
 */
package com.transfer.jooq.stubs;


import com.transfer.jooq.stubs.tables.Account;
import com.transfer.jooq.stubs.tables.Customer;
import com.transfer.jooq.stubs.tables.CustomerAccountLink;
import com.transfer.jooq.stubs.tables.TransactionLog;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in PUBLIC
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>PUBLIC.ACCOUNT</code>.
     */
    public static final Account ACCOUNT = com.transfer.jooq.stubs.tables.Account.ACCOUNT;

    /**
     * The table <code>PUBLIC.CUSTOMER</code>.
     */
    public static final Customer CUSTOMER = com.transfer.jooq.stubs.tables.Customer.CUSTOMER;

    /**
     * The table <code>PUBLIC.CUSTOMER_ACCOUNT_LINK</code>.
     */
    public static final CustomerAccountLink CUSTOMER_ACCOUNT_LINK = com.transfer.jooq.stubs.tables.CustomerAccountLink.CUSTOMER_ACCOUNT_LINK;

    /**
     * The table <code>PUBLIC.TRANSACTION_LOG</code>.
     */
    public static final TransactionLog TRANSACTION_LOG = com.transfer.jooq.stubs.tables.TransactionLog.TRANSACTION_LOG;
}
