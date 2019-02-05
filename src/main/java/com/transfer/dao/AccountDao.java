package com.transfer.dao;


import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import org.jooq.Configuration;

import java.util.List;

public interface AccountDao {

    Account createAccount(Configuration txContext, Account account, long customerID);

    Account createAccount(Account account, long customerID);

    Account getAccountDetails(String accountNumber);

    Account getAccountDetails(Account account);

    void setAccountBalance(Account account);

    void setAccountBalance(Configuration txContext, Account account);

    Account softDeleteAccount(String accountNumber);

    Account lockAccount(Configuration txContext, String accountNumber);

    void addCustomerToAccount(Customer customer, Account account);

    List<Account> getAccountsOfCustomer(Customer customer);
}
