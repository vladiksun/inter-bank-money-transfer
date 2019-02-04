package com.transfer.service;

import com.transfer.entity.Account;
import com.transfer.exception.*;

import java.util.List;

public interface AccountService {

    /**
     * Creates account for customer
     * @param accountToCreate account to create.
     * @param customerId customer id
     * @return created account
     * @throws IllegalAccountTypeException when account type is not valid
     * @throws CustomerNotFoundException when the customer with <code>customerId</code> does not exist.
     */
    Account createAccount(Account accountToCreate, long customerId) throws ApplicationException;


    // makes a new account and enters it into db,
    // customer for customerId must exist 1st
    //TODO: javadoc
    void removeAccount(long accountId) throws ApplicationException;

    //TODO: javadoc
    // customer-account relationship methods
    void addCustomerToAccount(long customerId, long accountId) throws ApplicationException;
    //TODO: javadoc
    // removes a customer from the account, but
    // the customer is not removed from the db
    void removeCustomerFromAccount(long customerId, long accountId) throws ApplicationException;
    //TODO: javadoc
    List<Account> getAccountsOfCustomer(long customerId) throws ApplicationException;
    //TODO: javadoc
    List<Long> getCustomerIds(long accountId) throws ApplicationException;
    //TODO: javadoc
    Account getAccountDetails(long accountId) throws ApplicationException;

    void setAccountBalance(Account account);
}
