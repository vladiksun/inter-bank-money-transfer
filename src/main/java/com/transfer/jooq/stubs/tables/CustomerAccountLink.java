/*
 * This file is generated by jOOQ.
 */
package com.transfer.jooq.stubs.tables;


import com.transfer.jooq.stubs.Indexes;
import com.transfer.jooq.stubs.Keys;
import com.transfer.jooq.stubs.Public;
import com.transfer.jooq.stubs.tables.records.CustomerAccountLinkRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class CustomerAccountLink extends TableImpl<CustomerAccountLinkRecord> {

    private static final long serialVersionUID = 260251058;

    /**
     * The reference instance of <code>PUBLIC.CUSTOMER_ACCOUNT_LINK</code>
     */
    public static final CustomerAccountLink CUSTOMER_ACCOUNT_LINK = new CustomerAccountLink();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CustomerAccountLinkRecord> getRecordType() {
        return CustomerAccountLinkRecord.class;
    }

    /**
     * The column <code>PUBLIC.CUSTOMER_ACCOUNT_LINK.CUSTOMER_ID</code>.
     */
    public final TableField<CustomerAccountLinkRecord, Long> CUSTOMER_ID = createField("CUSTOMER_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.CUSTOMER_ACCOUNT_LINK.ACCOUNT_NUMBER</code>.
     */
    public final TableField<CustomerAccountLinkRecord, String> ACCOUNT_NUMBER = createField("ACCOUNT_NUMBER", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.CUSTOMER_ACCOUNT_LINK</code> table reference
     */
    public CustomerAccountLink() {
        this(DSL.name("CUSTOMER_ACCOUNT_LINK"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.CUSTOMER_ACCOUNT_LINK</code> table reference
     */
    public CustomerAccountLink(String alias) {
        this(DSL.name(alias), CUSTOMER_ACCOUNT_LINK);
    }

    /**
     * Create an aliased <code>PUBLIC.CUSTOMER_ACCOUNT_LINK</code> table reference
     */
    public CustomerAccountLink(Name alias) {
        this(alias, CUSTOMER_ACCOUNT_LINK);
    }

    private CustomerAccountLink(Name alias, Table<CustomerAccountLinkRecord> aliased) {
        this(alias, aliased, null);
    }

    private CustomerAccountLink(Name alias, Table<CustomerAccountLinkRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> CustomerAccountLink(Table<O> child, ForeignKey<O, CustomerAccountLinkRecord> key) {
        super(child, key, CUSTOMER_ACCOUNT_LINK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK_C2A_ACCOUNT_NUMBER_INDEX_E, Indexes.FK_C2A_CUSTOMER_ID_INDEX_E, Indexes.PRIMARY_KEY_E5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CustomerAccountLinkRecord> getPrimaryKey() {
        return Keys.PK_C2A;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CustomerAccountLinkRecord>> getKeys() {
        return Arrays.<UniqueKey<CustomerAccountLinkRecord>>asList(Keys.PK_C2A);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<CustomerAccountLinkRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CustomerAccountLinkRecord, ?>>asList(Keys.FK_C2A_CUSTOMER_ID, Keys.FK_C2A_ACCOUNT_NUMBER);
    }

    public Customer customer() {
        return new Customer(this, Keys.FK_C2A_CUSTOMER_ID);
    }

    public Account account() {
        return new Account(this, Keys.FK_C2A_ACCOUNT_NUMBER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerAccountLink as(String alias) {
        return new CustomerAccountLink(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerAccountLink as(Name alias) {
        return new CustomerAccountLink(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CustomerAccountLink rename(String name) {
        return new CustomerAccountLink(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CustomerAccountLink rename(Name name) {
        return new CustomerAccountLink(name, null);
    }
}
