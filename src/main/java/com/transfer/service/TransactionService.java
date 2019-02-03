package com.transfer.service;

import com.transfer.entity.TransactionDetails;
import com.transfer.exception.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    // returns an ArrayList of TxDetails objects
    // that correspond to the txs for the specified
    // account
    List<TransactionDetails> getAccountTransactions(Date startDate, Date endDate, String accountId)
                                                                                    throws InvalidParameterException;

    TransactionDetails getTransactionDetails(long transactionID) throws TransactionNotFoundException, InvalidParameterException;

    void withdraw(BigDecimal amount, String description, long accountId) throws InvalidParameterException,
                                                                                AccountNotFoundException,
                                                                                IllegalAccountTypeException,
                                                                                InsufficientFundsException;

    void deposit(BigDecimal amount, String description, long accountId) throws InvalidParameterException,
                                                                                AccountNotFoundException,
                                                                                IllegalAccountTypeException;

    void makeCharge(BigDecimal amount, String description, long accountId) throws InvalidParameterException,
                                                                                    AccountNotFoundException,
                                                                                    IllegalAccountTypeException,
                                                                                    InsufficientCreditException;

    void makePayment(BigDecimal amount, String description, long accountId) throws InvalidParameterException,
                                                                                    AccountNotFoundException,
                                                                                    IllegalAccountTypeException;

    void transferFunds(BigDecimal amount, String description, long fromAccountId, long toAccountId)
                                                                                    throws InvalidParameterException,
                                                                                            AccountNotFoundException,
                                                                                            InsufficientFundsException,
                                                                                            InsufficientCreditException;
}
