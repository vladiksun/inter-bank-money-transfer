package com.transfer.service;

import com.google.inject.AbstractModule;
import com.transfer.service.impl.AccountServiceImpl;
import com.transfer.service.impl.CustomerServiceImpl;
import com.transfer.service.impl.TransactionServiceImpl;

import javax.inject.Singleton;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomerService.class).to(CustomerServiceImpl.class).in(Singleton.class);
        bind(AccountService.class).to(AccountServiceImpl.class).in(Singleton.class);
        bind(TransactionService.class).to(TransactionServiceImpl.class).in(Singleton.class);
    }
}
