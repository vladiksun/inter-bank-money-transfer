package com.transfer.service;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.transfer.dao.AccountDao;
import com.transfer.dao.CustomerDao;
import com.transfer.dao.TransactionLogDao;
import com.transfer.dao.impl.AccountDaoImpl;
import com.transfer.dao.impl.CustomerDaoImpl;
import com.transfer.dao.impl.TransactionLogDaoImpl;
import com.transfer.service.impl.AccountServiceImpl;
import com.transfer.service.impl.CustomerServiceImpl;
import com.transfer.service.impl.TransactionServiceImpl;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomerDao.class).to(CustomerDaoImpl.class).in(Singleton.class);
        bind(AccountDao.class).to(AccountDaoImpl.class).in(Singleton.class);
        bind(TransactionLogDao.class).to(TransactionLogDaoImpl.class).in(Singleton.class);

        bind(CustomerService.class).to(CustomerServiceImpl.class).in(Singleton.class);
        bind(AccountService.class).to(AccountServiceImpl.class).in(Singleton.class);
        bind(TransactionService.class).to(TransactionServiceImpl.class).in(Singleton.class);
    }
}
