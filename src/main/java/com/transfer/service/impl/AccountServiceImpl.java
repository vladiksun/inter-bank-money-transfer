package com.transfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.AccountDao;
import com.transfer.entity.Account;
import com.transfer.exception.AccountNotFoundException;
import com.transfer.exception.CustomerNotFoundException;
import com.transfer.exception.IllegalAccountTypeException;
import com.transfer.exception.InvalidParameterException;
import com.transfer.service.AccountService;

import java.rmi.RemoteException;
import java.util.List;

@Singleton
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    @Inject
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account createAccount(Account accountToCreate, long customerId) throws IllegalAccountTypeException, CustomerNotFoundException, InvalidParameterException {
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
    public List<Account> getAccountsOfCustomer(long customerId) throws InvalidParameterException, CustomerNotFoundException {
        return null;
    }

    @Override
    public List<Long> getCustomerIds(long accountId) throws InvalidParameterException, AccountNotFoundException {
        return null;
    }

    @Override
    public Account getAccountDetails(long accountId) throws AccountNotFoundException {
        Account account = accountDao.getAccountDetails(accountId);
        validateAccount(account, accountId);
        return account;
    }


    @Override
    public void setAccountBalance(Account account) {
        accountDao.setAccountBalance(account);
    }

    private void validateAccount(Account account, long accountID) throws AccountNotFoundException {
        if (account == null) {
            throw new AccountNotFoundException(String.format("Account with number = [ %d ] is not found", accountID));
        }
    }
}
