package com.transfer.service;

import com.transfer.entity.Account;
import com.transfer.exception.AccountNotFoundException;
import com.transfer.exception.CustomerNotFoundException;
import com.transfer.exception.IllegalAccountTypeException;
import com.transfer.exception.InvalidParameterException;
import org.jooq.Configuration;

import java.rmi.RemoteException;
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
    Account createAccount(Account accountToCreate, long customerId) throws IllegalAccountTypeException,
                                                                            CustomerNotFoundException,
                                                                            InvalidParameterException;


    // makes a new account and enters it into db,
    // customer for customerId must exist 1st
    //TODO: javadoc
    void removeAccount(long accountId) throws RemoteException, InvalidParameterException, AccountNotFoundException;

    //TODO: javadoc
    // customer-account relationship methods
    void addCustomerToAccount(long customerId, long accountId) throws InvalidParameterException,
                                                                             CustomerNotFoundException,
                                                                             AccountNotFoundException;
    //TODO: javadoc
    // removes a customer from the account, but
    // the customer is not removed from the db
    void removeCustomerFromAccount(long customerId, long accountId) throws InvalidParameterException,
                                                                                  CustomerNotFoundException,
                                                                                  AccountNotFoundException;
    //TODO: javadoc
    List<Account> getAccountsOfCustomer(long customerId) throws InvalidParameterException, CustomerNotFoundException;
    //TODO: javadoc
    List<Long> getCustomerIds(long accountId) throws InvalidParameterException, AccountNotFoundException;
    //TODO: javadoc
    Account getAccountDetails(long accountId) throws InvalidParameterException, AccountNotFoundException;

    void setAccountBalance(Account account);
}
