package com.transfer.dao;


import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.ApplicationException;
import org.jooq.Configuration;

import java.util.List;

public interface AccountDao {

    Account createAccount(Configuration txContext, Account account, long customerID);

    Account createAccount(Account account, long customerID);

    Account getAccountDetails(String accountNumber) throws ApplicationException;

    Account getAccountDetails(Account account) throws ApplicationException;

    void setAccountBalance(Account account);

    void setAccountBalance(Configuration txContext, Account account);

    Account softDeleteAccount(String accountNumber) throws ApplicationException;

    Account lockAccount(Configuration txContext, String accountNumber);

    void addCustomerToAccount(Customer customer, Account account);

    List<Account> getAccountsOfCustomer(Customer customer);
}
