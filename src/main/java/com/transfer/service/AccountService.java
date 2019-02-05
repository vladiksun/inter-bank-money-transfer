package com.transfer.service;

import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.*;

import java.util.List;

public interface AccountService {

    /**
     * Creates account for customer
     * @param accountToCreate account to create.
     * @param customerId customer id
     * @return created account
     * @throws ApplicationException
     */
    Account createAccount(Account accountToCreate, long customerId) throws ApplicationException;

    /**
     * Soft deletes account
     * @param accountNumber account number
     * @return removed account
     * @throws ApplicationException
     */
    Account softDeleteAccount(String accountNumber) throws ApplicationException;

    /**
     * Add customer to account
     * @param customerId customer ID
     * @param accountNumber accountNumber
     * @return customer
     * @throws ApplicationException
     */
    Customer addCustomerToAccount(long customerId, String accountNumber) throws ApplicationException;

    /**
     * Get list of customers accounts
     * @param customerId customer ID
     * @return <code>List<Account></code> of accounts
     * @throws ApplicationException
     */
    List<Account> getAccountsOfCustomer(long customerId) throws ApplicationException;

    /**
     * Get list of customers for account
     * @param accountNumber
     * @return <code>List<Customer></code> of customers
     * @throws ApplicationException
     */
    List<Customer> getCustomerIdsForAccount(String accountNumber) throws ApplicationException;

    /**
     * Get account details
     * @param accountNumber
     * @return account
     * @throws ApplicationException
     */
    Account getAccountDetails(String accountNumber) throws ApplicationException;

    /**
     * Set account balance
     * @param account
     * @return account
     * @throws ApplicationException
     */
    void setAccountBalance(Account account);
}
