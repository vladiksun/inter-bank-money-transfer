package com.transfer.service;

import com.transfer.entity.TransactionDetails;
import com.transfer.entity.TransactionLog;
import com.transfer.entity.UserTransaction;
import com.transfer.exception.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    /**
     * Get account transactions
     * @param startDate start from date
     * @param endDate start from date
     * @return <code>List<TransactionDetails></code> transaction logs within dates
     * @throws ApplicationException
     */
    List<TransactionDetails> getAccountTransactions(Date startDate, Date endDate, String accountNumber)
                                                                                    throws ApplicationException;


    void withdraw(BigDecimal amount, String description, long accountId) throws ApplicationException;

    void deposit(BigDecimal amount, String description, long accountId) throws ApplicationException;

    void makeCharge(BigDecimal amount, String description, long accountId) throws ApplicationException;

    void makePayment(BigDecimal amount, String description, long accountId) throws ApplicationException;

    TransactionLog transferFunds(UserTransaction userTransaction) throws ApplicationException;
}
