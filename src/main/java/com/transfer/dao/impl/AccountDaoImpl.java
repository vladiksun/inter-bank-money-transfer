package com.transfer.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.AccountDao;
import com.transfer.entity.Account;
import com.transfer.jooq.stubs.tables.records.AccountRecord;
import org.jooq.*;
import org.jooq.impl.DSL;

import static com.transfer.jooq.stubs.Tables.ACCOUNT;


@Singleton
public class AccountDaoImpl implements AccountDao {

    private final Configuration configuration;

    private RecordMapper<Record, Account> accountMapper = record -> {
        AccountRecord accountRecord = (AccountRecord) record;

        return new Account(accountRecord.getAccountNumber(),
                            accountRecord.getType(),
                            accountRecord.getDescription(),
                            accountRecord.getBalance(),
                            accountRecord.getCreditLine(),
                            accountRecord.getCurrencyCode());
    };

    @Inject
    public AccountDaoImpl(final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Account getAccountDetails(long accountId) {
        DSLContext ctx = DSL.using(configuration);

        return ctx.select()
                .from(ACCOUNT)
                .where(ACCOUNT.ACCOUNT_NUMBER.eq(accountId))
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
    public void setAccountBalance(Configuration outer, Account account) {
        DSL.using(outer)
            .transaction(nested ->
                    DSL.using(nested)
                        .update(ACCOUNT)
                        .set(ACCOUNT.BALANCE, account.getBalance())
                        .where(ACCOUNT.ACCOUNT_NUMBER.eq(account.getAccountNumber()))
                        .execute());
    }
}
