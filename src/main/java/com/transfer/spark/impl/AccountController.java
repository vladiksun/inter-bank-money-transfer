package com.transfer.spark.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.entity.AccountDetails;
import com.transfer.service.AccountService;
import com.transfer.spark.SparkController;

import static com.transfer.spark.util.JsonUtils.fromJsonString;
import static com.transfer.spark.util.JsonUtils.json;
import static spark.Spark.*;

@Singleton
public class AccountController implements SparkController {

    private final AccountService accountService;

    @Inject
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void init() {
        post("/account", (req, res) -> {
            final String body = req.body();
            AccountDetails accountDetails = fromJsonString(AccountDetails.class, body);
            return accountService.createAccount(accountDetails.getAccount(), accountDetails.getCustomerID());
        }, json());

        post("/account/addCustomer", (req, res) -> {
            final String body = req.body();
            AccountDetails accountDetails = fromJsonString(AccountDetails.class, body);
            return accountService.addCustomerToAccount(accountDetails.getCustomerID(),
                    accountDetails.getAccount().getAccountNumber());
        }, json());

        get("/account/customers/:customerID", (req, res) -> {
            long customerID = Long.parseLong(req.params(":customerID"));
            return accountService.getAccountsOfCustomer(customerID);
        }, json());

        get("/account/:accountNumber", (req, res) -> {
            String accountNumber = req.params(":accountNumber");
            return accountService.getAccountDetails(accountNumber);
        }, json());
    }

}
