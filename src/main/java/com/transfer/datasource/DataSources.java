package com.transfer.datasource;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.jolbox.bonecp.BoneCPDataSource;
import org.jooq.Configuration;

import javax.sql.DataSource;

public class DataSources extends AbstractModule {

    @Override
    protected void configure() {}

    @Provides
    @Singleton
    DataSource provideDataSource(@Named("db.driver") String dbDriver,
                                 @Named("db.url") String dbUrl,
                                 @Named("db.username") String dbUserName,
                                 @Named("db.password") String dbPassword) {

        BoneCPDataSource dataSource;

        try {
            // We're using BoneCP here to configure a connection pool
            dataSource = new BoneCPDataSource();
            dataSource.setDriverClass(dbDriver);
            dataSource.setJdbcUrl(dbUrl);
            dataSource.setUsername(dbUserName);
            dataSource.setPassword(dbPassword);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }
}
