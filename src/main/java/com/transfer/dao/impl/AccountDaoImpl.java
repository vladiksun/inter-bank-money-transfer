package com.transfer.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.AccountDao;
import com.transfer.entity.Account;
import com.transfer.jooq.stubs.tables.records.AccountRecord;
import com.transfer.jooq.stubs.tables.records.CustomerAccountLinkRecord;
import org.jooq.*;
import org.jooq.impl.DSL;

import static com.transfer.jooq.stubs.Tables.ACCOUNT;
import static com.transfer.jooq.stubs.Tables.CUSTOMER_ACCOUNT_LINK;


@Singleton
public class AccountDaoImpl implements AccountDao {

    private final Configuration configuration;

    private RecordMapper<Record, Account> accountMapper = record -> {
        AccountRecord accountRecord = (AccountRecord) record;

        return new Account(accountRecord.getAccountNumber(),
                            accountRecord.getType(),
                            accountRecord.getDescription(),
                            accountRecord.getBalance(),
                            accountRecord.getCurrencyCode());
    };

    @Inject
    public AccountDaoImpl(final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Account createAccount(Configuration txContext, Account account, long customerID) {
        return DSL.using(txContext)
                .transactionResult(nestedTx -> {
                    DSLContext ctx = DSL.using(nestedTx);

                    AccountRecord accountRecord = ctx.newRecord(ACCOUNT);
                    accountRecord.setAccountNumber(account.getAccountNumber());
                    accountRecord.setBalance(account.getBalance());
                    accountRecord.setCurrencyCode(account.getCurrencyCode());
                    accountRecord.setDescription(account.getDescription());
                    accountRecord.setType(account.getType());
                    accountRecord.setSoftDeleted(null);
                    accountRecord.store();

                    CustomerAccountLinkRecord customerAccountLinkRecord = ctx.newRecord(CUSTOMER_ACCOUNT_LINK);
                    customerAccountLinkRecord.setAccountNumber(account.getAccountNumber());
                    customerAccountLinkRecord.setCustomerId(customerID);
                    customerAccountLinkRecord.store();

                    return accountRecord.map(accountMapper);
                });
    }

    @Override
    public Account createAccount(Account account, long customerID) {
        return createAccount(configuration, account, customerID);
    }

    @Override
    public Account getAccountDetails(String accountNumber) {
        DSLContext ctx = DSL.using(configuration);

        return ctx.select()
                .from(ACCOUNT)
                .where(ACCOUNT.ACCOUNT_NUMBER.eq(accountNumber)
                        .and(ACCOUNT.SOFT_DELETED.isNull()))
                .fetchOne()
                .map(accountMapper);
    }

    @Override
    public Account getAccountDetails(Account account) {
        return getAccountDetails(account.getAccountNumber());
    }

    @Override
    public void setAccountBalance(Account account) {
        setAccountBalance(configuration, account);
    }

    @Override
    public void setAccountBalance(Configuration txContext, Account account) {
        DSL.using(txContext)
            .transaction(nested ->
                    DSL.using(nested)
                        .update(ACCOUNT)
                        .set(ACCOUNT.BALANCE, account.getBalance())
                        .where(ACCOUNT.ACCOUNT_NUMBER.eq(account.getAccountNumber()))
                        .execute());
    }

    @Override
    public Account softDeleteAccount(String accountNumber) {
        DSLContext ctx = DSL.using(configuration);

        //TODO remove links

        return ctx.update(ACCOUNT)
                .set(ACCOUNT.SOFT_DELETED, "TRUE")
                .where(ACCOUNT.ACCOUNT_NUMBER.eq(accountNumber))
                .returning()
                .fetchOne()
                .map(accountMapper);
    }

    @Override
    public Account lockAccount(Configuration txContext, String accountNumber) {
        return DSL.using(txContext)
                .transactionResult(nestedTx ->
                        DSL.using(nestedTx)
                                    .select()
                                    .from(ACCOUNT)
                                    .where(ACCOUNT.ACCOUNT_NUMBER.eq(accountNumber)
                                            .and(ACCOUNT.SOFT_DELETED.isNull()))
                                    .forUpdate()
                                    .fetchOne()
                                    .map(accountMapper));
    }
}
