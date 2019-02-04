package com.transfer.dao;


import com.transfer.entity.Account;
import org.jooq.Configuration;

public interface AccountDao extends Dao {

    Account getAccountDetails(long accountId);

    Account getAccountDetails(Account account);

    void setAccountBalance(Account account);

    void setAccountBalance(Configuration configuration, Account account);
}
