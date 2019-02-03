package com.transfer.service;

import com.google.common.base.Charsets;
import com.google.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Contains startup and shutdown application logic
 */
public class LifecycleManager {

    private static final String INIT_DDL_SCRIPT = "/db-h2.sql";

    private final DataSource dataSource;

    @Inject
    public LifecycleManager(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void onStart() {

        /* creates DB schema and fills DB with demo data */
        loadInitScript();

        /* Stops any services that must be stopped gracefully */
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));
    }

    private void loadInitScript() {
        DSLContext dsl = DSL.using(dataSource, SQLDialect.DEFAULT);
        try {
            String dml = IOUtils.resourceToString(INIT_DDL_SCRIPT, Charsets.UTF_8);
            dsl.execute(dml);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read DML statements from the file");
        }
    }

    private void onShutdown() {
        //do nothing
    }
}
