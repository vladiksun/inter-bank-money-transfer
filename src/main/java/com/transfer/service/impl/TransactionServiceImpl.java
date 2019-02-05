package com.transfer.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.AccountDao;
import com.transfer.dao.TransactionLogDao;
import com.transfer.entity.Account;
import com.transfer.entity.TransactionLog;
import com.transfer.entity.UserTransaction;
import com.transfer.exception.ApplicationException;
import com.transfer.exception.InsufficientFundsException;
import com.transfer.exception.InvalidParameterException;
import com.transfer.service.AccountService;
import com.transfer.service.TransactionService;
import com.transfer.utils.AppProperties;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Singleton
public class TransactionServiceImpl implements TransactionService {

    private final TransactionLogDao transactionLogDao;
    private final Configuration configuration;
    private final AccountDao accountDao;
    private final AccountService accountService;
    private final AppProperties appProperties;

    @Inject
    public TransactionServiceImpl(final TransactionLogDao transactionLogDao,
                                  final Configuration configuration,
                                  final AccountDao accountDao,
                                  final AppProperties appProperties,
                                  final AccountService accountService) {
        this.transactionLogDao = transactionLogDao;
        this.configuration = configuration;
        this.accountDao = accountDao;
        this.appProperties = appProperties;
        this.accountService = accountService;
    }

    @Override
    public List<TransactionLog> getAccountTransactions(Date startDate, Date endDate, String accountNumber)
            throws ApplicationException {
        throw new RuntimeException("Operation is not supported yet.");
    }

    @Override
    public TransactionLog withdraw(BigDecimal amount, String description, String accountNumber) throws ApplicationException {
        if (!Validator.isAccountNumberValid(accountNumber)) {
            throw new InvalidParameterException("Account number is not valid");
        }

        DSLContext ctx = DSL.using(configuration);

        return ctx.transactionResult(outerTx -> {

            Account account = accountDao.lockAccount(outerTx, accountNumber);
            Validator.validateAccountExists(account, accountNumber);

            CurrencyUnit fromCurrencyUnit = CurrencyUnit.of(account.getCurrencyCode());
            RoundingMode appRoundingMode = appProperties.getAppRoundingMode();

            Money transactionMoney = Money.of(fromCurrencyUnit, amount
                    .setScale(fromCurrencyUnit.getDecimalPlaces(), appRoundingMode));

            if (transactionMoney.isNegativeOrZero()) {
                throw new InvalidParameterException("Specify the sum to withdraw");
            }

            Money accountMoney = Money.of(fromCurrencyUnit, account.getBalance()
                    .setScale(fromCurrencyUnit.getDecimalPlaces(), appRoundingMode));

            Money accountLeftOver = accountMoney.minus(transactionMoney);

            if (accountLeftOver.isNegative()) {
                throw new InsufficientFundsException("Not enough funds.");
            }

            account.setBalance(accountLeftOver.getAmount());
            accountDao.setAccountBalance(outerTx, account);

            TransactionLog transactionLog = new TransactionLog(account.getAccountNumber(),
                                                                transactionMoney.getAmount(),
                                                                accountMoney.getAmount(),
                                                                new Timestamp(System.currentTimeMillis()));

            transactionLog = transactionLogDao.saveTransactionLog(configuration, transactionLog);

            return transactionLog;
        });
    }

    @Override
    public TransactionLog deposit(BigDecimal amount, String description, String accountNumber) throws ApplicationException {
        if (!Validator.isAccountNumberValid(accountNumber)) {
            throw new InvalidParameterException("Account number is not valid");
        }

        DSLContext ctx = DSL.using(configuration);

        return ctx.transactionResult(outerTx -> {

            Account account = accountDao.lockAccount(outerTx, accountNumber);
            Validator.validateAccountExists(account, accountNumber);

            CurrencyUnit fromCurrencyUnit = CurrencyUnit.of(account.getCurrencyCode());
            RoundingMode appRoundingMode = appProperties.getAppRoundingMode();

            Money transactionMoney = Money.of(fromCurrencyUnit, amount
                    .setScale(fromCurrencyUnit.getDecimalPlaces(), appRoundingMode));

            if (transactionMoney.isNegativeOrZero()) {
                throw new InvalidParameterException("Specify the sum to deposit");
            }

            Money accountMoney = Money.of(fromCurrencyUnit, account.getBalance()
                    .setScale(fromCurrencyUnit.getDecimalPlaces(), appRoundingMode));

            Money depositMoney = Money.of(fromCurrencyUnit, amount
                    .setScale(fromCurrencyUnit.getDecimalPlaces(), appRoundingMode));

            account.setBalance(accountMoney.plus(depositMoney).getAmount());
            accountDao.setAccountBalance(outerTx, account);

            TransactionLog transactionLog = new TransactionLog(account.getAccountNumber(),
                    transactionMoney.getAmount(),
                    accountMoney.getAmount(),
                    new Timestamp(System.currentTimeMillis()));

            transactionLog = transactionLogDao.saveTransactionLog(configuration, transactionLog);

            return transactionLog;
        });
    }

    @Override
    public TransactionLog transferFunds(UserTransaction userTransaction) throws ApplicationException {
        validateTransactionParameters(userTransaction);

        DSLContext ctx = DSL.using(configuration);

        return ctx.transactionResult(configuration -> {

            Account fromAccount = accountDao.lockAccount(configuration, userTransaction.getFromAccountNumber());
            Account toAccount = accountDao.lockAccount(configuration, userTransaction.getToAccountNumber());

            Validator.validateAccountExists(fromAccount, userTransaction.getFromAccountNumber());
            Validator.validateAccountExists(toAccount, userTransaction.getToAccountNumber());

            CurrencyUnit fromCurrencyUnit = CurrencyUnit.of(fromAccount.getCurrencyCode());
            CurrencyUnit toCurrencyUnit = CurrencyUnit.of(toAccount.getCurrencyCode());

            if (!fromCurrencyUnit.equals(toCurrencyUnit)) {
                throw new InvalidParameterException("Fail to transfer fund, the source and destination account are of different currency");
            }

            RoundingMode appRoundingMode = appProperties.getAppRoundingMode();

            Money transactionMoney = Money.of(fromCurrencyUnit, userTransaction.getAmount()
                    .setScale(fromCurrencyUnit.getDecimalPlaces(), appRoundingMode));

            if (transactionMoney.isNegativeOrZero()) {
                throw new InvalidParameterException("Nothing to transfer");
            }

            Money fromAccountMoney = Money.of(fromCurrencyUnit, fromAccount.getBalance()
                    .setScale(fromCurrencyUnit.getDecimalPlaces(), appRoundingMode));
            Money toAccountMoney = Money.of(toCurrencyUnit, toAccount.getBalance()
                    .setScale(toCurrencyUnit.getDecimalPlaces(), appRoundingMode));

            Money fromAccountLeftOver = fromAccountMoney.minus(transactionMoney);

            if (fromAccountLeftOver.isNegative()) {
                throw new InsufficientFundsException("Not enough funds at source account");
            }

            fromAccount.setBalance(fromAccountLeftOver.getAmount());
            toAccount.setBalance(toAccountMoney.plus(transactionMoney).getAmount());

            accountDao.setAccountBalance(configuration, fromAccount);
            accountDao.setAccountBalance(configuration, toAccount);

            TransactionLog transactionLog = new TransactionLog(fromAccount.getAccountNumber(),
                                                                transactionMoney.getAmount(),
                                                                fromAccountMoney.getAmount(),
                                                                new Timestamp(System.currentTimeMillis()));

            transactionLog = transactionLogDao.saveTransactionLog(configuration, transactionLog);

            return transactionLog;
        });
    }

    private void validateTransactionParameters(UserTransaction userTransaction) throws InvalidParameterException {
        if (userTransaction == null) {
            throw new InvalidParameterException("Transaction details are not specified");
        }

        if (userTransaction.getAmount() == null) {
            throw new InvalidParameterException("Transfer amount is not specified");
        }

        if (BigDecimal.ZERO.equals(userTransaction.getAmount())) {
            throw new InvalidParameterException("Transfer amount cannot be 0.00");
        }

        if (userTransaction.getFromAccountNumber() == null) {
            throw new InvalidParameterException("Source account is not specified");
        }

        if (userTransaction.getToAccountNumber() == null) {
            throw new InvalidParameterException("Target account is not specified");
        }

        if (userTransaction.getToAccountNumber().equals(userTransaction.getFromAccountNumber())) {
            throw new InvalidParameterException("Source and target accounts are same");
        }
    }
}
