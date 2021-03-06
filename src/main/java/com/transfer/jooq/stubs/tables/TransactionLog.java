/*
 * This file is generated by jOOQ.
 */
package com.transfer.jooq.stubs.tables;


import com.transfer.jooq.stubs.Indexes;
import com.transfer.jooq.stubs.Keys;
import com.transfer.jooq.stubs.Public;
import com.transfer.jooq.stubs.tables.records.TransactionLogRecord;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class TransactionLog extends TableImpl<TransactionLogRecord> {

    private static final long serialVersionUID = -1515481917;

    /**
     * The reference instance of <code>PUBLIC.TRANSACTION_LOG</code>
     */
    public static final TransactionLog TRANSACTION_LOG = new TransactionLog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransactionLogRecord> getRecordType() {
        return TransactionLogRecord.class;
    }

    /**
     * The column <code>PUBLIC.TRANSACTION_LOG.TX_ID</code>.
     */
    public final TableField<TransactionLogRecord, Long> TX_ID = createField("TX_ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.TRANSACTION_LOG.ACCOUNT_NUMBER</code>.
     */
    public final TableField<TransactionLogRecord, String> ACCOUNT_NUMBER = createField("ACCOUNT_NUMBER", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TRANSACTION_LOG.AMOUNT</code>.
     */
    public final TableField<TransactionLogRecord, BigDecimal> AMOUNT = createField("AMOUNT", org.jooq.impl.SQLDataType.DECIMAL(19, 4).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TRANSACTION_LOG.BALANCE</code>.
     */
    public final TableField<TransactionLogRecord, BigDecimal> BALANCE = createField("BALANCE", org.jooq.impl.SQLDataType.DECIMAL(19, 4).nullable(false), this, "");

    /**
     * The column <code>PUBLIC.TRANSACTION_LOG.TIME_STAMP</code>.
     */
    public final TableField<TransactionLogRecord, Timestamp> TIME_STAMP = createField("TIME_STAMP", org.jooq.impl.SQLDataType.TIMESTAMP.precision(6).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>PUBLIC.TRANSACTION_LOG</code> table reference
     */
    public TransactionLog() {
        this(DSL.name("TRANSACTION_LOG"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.TRANSACTION_LOG</code> table reference
     */
    public TransactionLog(String alias) {
        this(DSL.name(alias), TRANSACTION_LOG);
    }

    /**
     * Create an aliased <code>PUBLIC.TRANSACTION_LOG</code> table reference
     */
    public TransactionLog(Name alias) {
        this(alias, TRANSACTION_LOG);
    }

    private TransactionLog(Name alias, Table<TransactionLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private TransactionLog(Name alias, Table<TransactionLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> TransactionLog(Table<O> child, ForeignKey<O, TransactionLogRecord> key) {
        super(child, key, TRANSACTION_LOG);
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
        return Arrays.<Index>asList(Indexes.FK_TX2A_ACCOUNT_NUMBER_INDEX_8, Indexes.PRIMARY_KEY_8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TransactionLogRecord, Long> getIdentity() {
        return Keys.IDENTITY_TRANSACTION_LOG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TransactionLogRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_8;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TransactionLogRecord>> getKeys() {
        return Arrays.<UniqueKey<TransactionLogRecord>>asList(Keys.CONSTRAINT_8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TransactionLogRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TransactionLogRecord, ?>>asList(Keys.FK_TX2A_ACCOUNT_NUMBER);
    }

    public Account account() {
        return new Account(this, Keys.FK_TX2A_ACCOUNT_NUMBER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionLog as(String alias) {
        return new TransactionLog(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionLog as(Name alias) {
        return new TransactionLog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TransactionLog rename(String name) {
        return new TransactionLog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TransactionLog rename(Name name) {
        return new TransactionLog(name, null);
    }
}
