package com.transfer.spark.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.entity.UserTransaction;
import com.transfer.service.TransactionService;
import com.transfer.spark.SparkController;

import static com.transfer.spark.util.JsonUtils.*;
import static spark.Spark.*;


@Singleton
public class TransactionController implements SparkController {

    private final TransactionService trasactionService;

    @Inject
    public TransactionController(final TransactionService trasactionService) {
        this.trasactionService = trasactionService;
    }

    @Override
    public void init() {
        post("transaction/transfer", (req, res) -> {
            final String body = req.body();
            final UserTransaction userTransaction = fromJsonString(UserTransaction.class, body);
            return trasactionService.transferFunds(userTransaction);
        }, json());

        post("transaction/withdraw", (req, res) -> {
            final String body = req.body();
            final UserTransaction userTransaction = fromJsonString(UserTransaction.class, body);
            return trasactionService.withdraw(userTransaction.getAmount(), userTransaction.getFromAccountNumber());
        }, json());

        post("transaction/deposit", (req, res) -> {
            final String body = req.body();
            final UserTransaction userTransaction = fromJsonString(UserTransaction.class, body);
            return trasactionService.deposit(userTransaction.getAmount(), userTransaction.getToAccountNumber());
        }, json());
    }
}
