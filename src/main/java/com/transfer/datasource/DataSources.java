package com.transfer.datasource;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.jolbox.bonecp.BoneCPDataSource;
import org.jooq.Configuration;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;

public class DataSources extends AbstractModule {

    @Override
    protected void configure() {}

    @Provides
    @Singleton
    Configuration provideConfiguration(@Named("db.driver") String dbDriver,
                                       @Named("db.url") String dbUrl,
                                       @Named("db.username") String dbUserName,
                                       @Named("db.password") String dbPassword) {


        BoneCPDataSource dataSource;

        try {
            dataSource = new BoneCPDataSource();
            dataSource.setDriverClass(dbDriver);
            dataSource.setJdbcUrl(dbUrl);
            dataSource.setUsername(dbUserName);
            dataSource.setPassword(dbPassword);
            dataSource.setDefaultAutoCommit(true);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        Settings settings = new Settings()
                .withReturnAllOnUpdatableRecord(false); // Defaults to false
        Configuration configuration = new DefaultConfiguration()
                .set(dataSource)
                .set(settings);

        return configuration;
    }
}
