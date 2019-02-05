package com.transfer;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.transfer.dao.CustomerDao;
import com.transfer.dao.impl.CustomerDaoImpl;
import com.transfer.datasource.DataSources;
import com.transfer.service.ServiceModule;
import com.transfer.spark.ControllerModule;
import com.transfer.utils.AppProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.google.common.io.Resources.asByteSource;
import static com.google.common.io.Resources.getResource;

public class MainModule extends AbstractModule {

    private static final String APPLICATION_PROPERTIES = "config.properties";

    @Override
    protected void configure() {
        Properties properties = loadApplicationProperties();

        Names.bindProperties(binder(), properties);
        bind(AppProperties.class).toInstance(new AppProperties(properties));

        install(new DataSources());
        install(new ServiceModule());
        install(new ControllerModule());
    }

    /* loads application properties */
    private Properties loadApplicationProperties() {
        try (InputStream is = asByteSource(getResource(APPLICATION_PROPERTIES)).openBufferedStream()) {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load application properties");
        }

    }
}
