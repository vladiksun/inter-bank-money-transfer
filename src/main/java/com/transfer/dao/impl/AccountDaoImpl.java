package com.transfer.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.AccountDao;
import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.AccountNotFoundException;
import com.transfer.exception.ApplicationException;
import com.transfer.jooq.stubs.tables.records.AccountRecord;
import com.transfer.jooq.stubs.tables.records.CustomerAccountLinkRecord;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Optional;

import static com.transfer.jooq.stubs.Tables.ACCOUNT;
import static com.transfer.jooq.stubs.Tables.CUSTOMER_ACCOUNT_LINK;


@Singleton
public class AccountDaoImpl implements AccountDao {

    private final Configuration configuration;

    private RecordMapper<Record, Account> accountMapper = record ->
            new Account(record.get(ACCOUNT.ACCOUNT_NUMBER),
                        record.get(ACCOUNT.TYPE),
                        record.get(ACCOUNT.DESCRIPTION),
                        record.get(ACCOUNT.BALANCE),
                        record.get(ACCOUNT.CURRENCY_CODE));

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
    public Account getAccountDetails(String accountNumber) throws ApplicationException {
        DSLContext ctx = DSL.using(configuration);

        return Optional.ofNullable(ctx.select()
                .from(ACCOUNT)
                .where(ACCOUNT.ACCOUNT_NUMBER.eq(accountNumber)
                        .and(ACCOUNT.SOFT_DELETED.isNull()))
                .fetchOne())
                .map(record -> accountMapper.map(record))
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account with number [ %s ] not found", accountNumber)));
    }

    @Override
    public Account getAccountDetails(Account account) throws ApplicationException {
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
    public Account softDeleteAccount(String accountNumber) throws ApplicationException {
        DSLContext ctx = DSL.using(configuration);

        return Optional.ofNullable(ctx.update(ACCOUNT)
                                    .set(ACCOUNT.SOFT_DELETED, "TRUE")
                                    .where(ACCOUNT.ACCOUNT_NUMBER.eq(accountNumber))
                                    .returning()
                                    .fetchOne()
                )
                .map(record -> accountMapper.map(record))
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account with number [ %s ] not found", accountNumber)));
    }

    @Override
    public Account lockAccount(Configuration txContext, String accountNumber) {
        return DSL.using(txContext)
                .transactionResult(nestedTx ->
                        Optional.ofNullable(
                                DSL.using(nestedTx)
                                        .select()
                                        .from(ACCOUNT)
                                        .where(ACCOUNT.ACCOUNT_NUMBER.eq(accountNumber)
                                                .and(ACCOUNT.SOFT_DELETED.isNull()))
                                        .forUpdate()
                                        .fetchOne()

                        )
                        .map(record -> accountMapper.map(record))
                        .orElseThrow(() -> new AccountNotFoundException(String.format("Account with number [ %s ] not found", accountNumber)))
                );

    }

    @Override
    public void addCustomerToAccount(Customer customer, Account account) {
        DSLContext ctx = DSL.using(configuration);

        Record record = ctx.select()
                .from(CUSTOMER_ACCOUNT_LINK)
                .where(CUSTOMER_ACCOUNT_LINK.CUSTOMER_ID.eq(customer.getCustomerId())
                        .and(CUSTOMER_ACCOUNT_LINK.ACCOUNT_NUMBER.eq(account.getAccountNumber())))
                .fetchOne();

        if (record == null) {
            CustomerAccountLinkRecord customerAccountLinkRecord = ctx.newRecord(CUSTOMER_ACCOUNT_LINK);
            customerAccountLinkRecord.setAccountNumber(account.getAccountNumber());
            customerAccountLinkRecord.setCustomerId(customer.getCustomerId());
            customerAccountLinkRecord.store();
        }
    }

    @Override
    public List<Account> getAccountsOfCustomer(Customer customer) {
        DSLContext ctx = DSL.using(configuration);

        return ctx.select(ACCOUNT.fields())
                .from(ACCOUNT)
                .join(CUSTOMER_ACCOUNT_LINK)
                .on(CUSTOMER_ACCOUNT_LINK.ACCOUNT_NUMBER.eq(ACCOUNT.ACCOUNT_NUMBER)
                        .and(CUSTOMER_ACCOUNT_LINK.CUSTOMER_ID.eq(customer.getCustomerId())))
                .fetch().map(accountMapper);
    }
}
