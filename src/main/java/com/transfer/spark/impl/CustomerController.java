package com.transfer.spark.impl;

import com.google.inject.Inject;
import com.transfer.entity.Customer;
import com.transfer.service.CustomerService;
import com.transfer.spark.SparkController;

import javax.inject.Singleton;

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
        post("/customers", (req, res) -> {
            final String body = req.body();
            final Customer customerToCreate = fromJsonString(Customer.class, body);
            return customerService.createCustomer(customerToCreate);
        }, json());
    }
}
