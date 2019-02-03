package com.transfer.service.impl;

import com.transfer.entity.AccountDetails;
import com.transfer.exception.AccountNotFoundException;
import com.transfer.exception.CustomerNotFoundException;
import com.transfer.exception.IllegalAccountTypeException;
import com.transfer.exception.InvalidParameterException;
import com.transfer.service.AccountService;

import javax.inject.Singleton;
import java.rmi.RemoteException;
import java.util.List;

@Singleton
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountDetails createAccount(AccountDetails accountToCreate, long customerId) throws IllegalAccountTypeException, CustomerNotFoundException, InvalidParameterException {
        return null;
    }

    @Override
    public void removeAccount(long accountId) throws RemoteException, InvalidParameterException, AccountNotFoundException {

    }

    @Override
    public void addCustomerToAccount(long customerId, long accountId) throws InvalidParameterException, CustomerNotFoundException, AccountNotFoundException {

    }

    @Override
    public void removeCustomerFromAccount(long customerId, long accountId) throws InvalidParameterException, CustomerNotFoundException, AccountNotFoundException {

    }

    @Override
    public List<AccountDetails> getAccountsOfCustomer(long customerId) throws InvalidParameterException, CustomerNotFoundException {
        return null;
    }

    @Override
    public List<Long> getCustomerIds(long accountId) throws InvalidParameterException, AccountNotFoundException {
        return null;
    }

    @Override
    public AccountDetails getAccountDetails(long accountId) throws InvalidParameterException, AccountNotFoundException {
        return null;
    }
}
