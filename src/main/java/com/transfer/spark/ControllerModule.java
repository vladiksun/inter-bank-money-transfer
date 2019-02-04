package com.transfer.spark;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.transfer.spark.impl.AccountController;
import com.transfer.spark.impl.CustomerController;
import com.transfer.spark.impl.ErrorHandler;
import com.transfer.spark.impl.TransactionController;


public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AccountController.class).in(Singleton.class);
        bind(CustomerController.class).in(Singleton.class);
        bind(TransactionController.class).in(Singleton.class);
        bind(ErrorHandler.class).in(Singleton.class);
    }
}
