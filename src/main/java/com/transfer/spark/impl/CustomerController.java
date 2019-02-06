package com.transfer.spark.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.entity.Customer;
import com.transfer.service.CustomerService;
import com.transfer.spark.SparkController;
import static com.transfer.spark.util.JsonUtils.json;
import static com.transfer.spark.util.JsonUtils.fromJsonString;
import static spark.Spark.*;

@Singleton
public class CustomerController implements SparkController {

    private final CustomerService customerService;

    @Inject
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void init() {
        post("/customer", (req, res) -> {
            final String body = req.body();
            final Customer customerToCreate = fromJsonString(Customer.class, body);
            return customerService.createCustomer(customerToCreate);
        }, json());

        get("/customer/:id", (req, res) -> {
            String customerID = req.params(":id");
            return customerService.getCustomerById(Long.valueOf(customerID));
        }, json());

        get("/customer/accounts/:accountNumber", (req, res) -> {
            String accountNumber = req.params(":accountNumber");
            return customerService.getCustomersOfAccount(accountNumber);
        }, json());

        get("/customer", (req, res) -> {
            String firstNameLike = req.queryParams("firstName");
            String lastNameLike = req.queryParams("lastName");
            return customerService.getCustomersByFirstAndLastName(firstNameLike, lastNameLike);
        }, json());
    }
}
