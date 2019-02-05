package com.transfer.dao;


import com.transfer.entity.Account;
import org.jooq.Configuration;

public interface AccountDao {

    Account createAccount(Configuration txContext, Account account, long customerID);

    Account createAccount(Account account, long customerID);

    Account getAccountDetails(long accountNumber);

    Account getAccountDetails(Account account);

    void setAccountBalance(Account account);

    void setAccountBalance(Configuration txContext, Account account);

    Account softDeleteAccount(long accountNumber);

    Account lockAccount(Configuration txContext, long accountNumber);
}
