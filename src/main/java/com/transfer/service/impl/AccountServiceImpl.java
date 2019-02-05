package com.transfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.AccountDao;
import com.transfer.entity.Account;
import com.transfer.entity.AccountType;
import com.transfer.entity.Customer;
import com.transfer.exception.*;
import com.transfer.service.AccountService;
import com.transfer.service.CustomerService;
import org.apache.commons.lang.StringUtils;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.List;

@Singleton
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;
    private final Configuration configuration;
    private final CustomerService customerService;

    @Inject
    public AccountServiceImpl(final AccountDao accountDao,
                              final CustomerService customerService,
                              final Configuration configuration) {
        this.accountDao = accountDao;
        this.customerService = customerService;
        this.configuration = configuration;
    }

    @Override
    public Account createAccount(Account accountToCreate, long customerId) throws ApplicationException {

        validateAccountParameters(accountToCreate);

        DSLContext ctx = DSL.using(configuration);

        return ctx.transactionResult(txContext -> {
            Customer customer = customerService.getCustomerById(customerId);

            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found");
            }

            return accountDao.createAccount(txContext, accountToCreate, customerId);
        });
    }

    private void validateAccountParameters(Account accountToCreate) throws ApplicationException {
        if (accountToCreate == null) {
            throw new InvalidParameterException("Account parameters are not set");
        }

        if (StringUtils.isBlank(accountToCreate.getCurrencyCode())) {
            throw new InvalidParameterException("Currency code is empty");
        }

        if (!Validator.isAccountNumberValid(accountToCreate.getAccountNumber())) {
            throw new InvalidParameterException("Account number is not valid");
        }

        if (!isAccountTypeValid(accountToCreate.getType())) {
            throw new IllegalAccountTypeException("Account type is not valid");
        }

    }

    private boolean isAccountTypeValid(String type) {
        return AccountType.fromId(type) != null;
    }

    @Override
    public Account softDeleteAccount(String accountNumber) throws ApplicationException {
        return accountDao.softDeleteAccount(accountNumber);
    }

    @Override
    public Customer addCustomerToAccount(long customerId, String accountNumber) throws ApplicationException {
        Customer customer = customerService.getCustomerById(customerId);
        Account account = getAccountDetails(accountNumber);

        Validator.validateCustomerExists(customer, customerId);

        accountDao.addCustomerToAccount(customer, account);

        return customer;
    }

    @Override
    public List<Account> getAccountsOfCustomer(long customerId) throws ApplicationException {
        Customer customer = customerService.getCustomerById(customerId);
        Validator.validateCustomerExists(customer, customerId);

        return accountDao.getAccountsOfCustomer(customer);
    }

    @Override
    public List<Customer> getCustomersOfAccount(String accountNumber) throws ApplicationException {
        throw new RuntimeException("Operation is not supported yet.");
    }

    @Override
    public Account getAccountDetails(String accountNumber) throws ApplicationException {
        Account account = accountDao.getAccountDetails(accountNumber);
        Validator.validateAccountExists(account, accountNumber);
        return account;
    }


    @Override
    public void setAccountBalance(Account account) {
        accountDao.setAccountBalance(account);
    }

}
