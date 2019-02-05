package com.transfer.service;

import com.transfer.entity.Account;
import com.transfer.entity.TransactionLog;
import com.transfer.entity.UserTransaction;
import com.transfer.exception.ApplicationException;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    /**
     * Get account transactions
     * @param startDate start from date
     * @param endDate start from date
     * @return <code>List<TransactionLog></code> transaction logs within dates
     * @throws ApplicationException
     */
    List<TransactionLog> getAccountTransactions(Date startDate, Date endDate, String accountNumber)
                                                                                    throws ApplicationException;

    /**
     * Withdraw funds from the account
     * @param amount amount of money to withdraw
     * @param description description
     * @return updated account details
     * @throws ApplicationException
     */
    Account withdraw(BigDecimal amount, @Nullable String description, long accountNumber) throws ApplicationException;

    /**
     * Deposit funds to the account
     * @param amount amount of money to withdraw
     * @param description description
     * @param accountNumber account number
     * @return updated account details
     * @throws ApplicationException
     */
    Account deposit(BigDecimal amount, String description, long accountNumber) throws ApplicationException;

    /**
     * Transfer funds from one account to another
     * @param userTransaction the POJO describing transaction details
     * @return the POJO describing transaction
     * @throws ApplicationException
     */
    TransactionLog transferFunds(UserTransaction userTransaction) throws ApplicationException;
}
