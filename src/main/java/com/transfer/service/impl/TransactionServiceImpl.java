package com.transfer.service.impl;

import com.transfer.entity.TransactionDetails;
import com.transfer.exception.*;
import com.transfer.service.TransactionService;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Singleton
public class TransactionServiceImpl implements TransactionService {
    @Override
    public List<TransactionDetails> getAccountTransactions(Date startDate, Date endDate, String accountId) throws InvalidParameterException {
        return null;
    }

    @Override
    public TransactionDetails getTransactionDetails(long transactionID) throws TransactionNotFoundException, InvalidParameterException {
        return null;
    }

    @Override
    public void withdraw(BigDecimal amount, String description, long accountId) throws InvalidParameterException, AccountNotFoundException, IllegalAccountTypeException, InsufficientFundsException {

    }

    @Override
    public void deposit(BigDecimal amount, String description, long accountId) throws InvalidParameterException, AccountNotFoundException, IllegalAccountTypeException {

    }

    @Override
    public void makeCharge(BigDecimal amount, String description, long accountId) throws InvalidParameterException, AccountNotFoundException, IllegalAccountTypeException, InsufficientCreditException {

    }

    @Override
    public void makePayment(BigDecimal amount, String description, long accountId) throws InvalidParameterException, AccountNotFoundException, IllegalAccountTypeException {

    }

    @Override
    public void transferFunds(BigDecimal amount, String description, long fromAccountId, long toAccountId) throws InvalidParameterException, AccountNotFoundException, InsufficientFundsException, InsufficientCreditException {

    }
}
