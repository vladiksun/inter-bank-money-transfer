package com.transfer.service;

import com.google.common.base.Charsets;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.IOUtils;
import org.h2.tools.Server;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Contains startup and shutdown application logic
 */
public class LifecycleManager {

    private static final String INIT_DDL_SCRIPT = "/db-h2.sql";

    private final DataSource dataSource;
    private String dbPort;
    private String dbShutDownURL;

    @Inject
    public LifecycleManager(final DataSource dataSource,
                            @Named("db.port") String dbPort,
                            @Named("db.shutDownURL") String dbShutDownURL) {
        this.dataSource = dataSource;
        this.dbPort = dbPort;
        this.dbShutDownURL = dbShutDownURL;
    }

    public void onStart() {

        startDB();

        /* creates DB schema and fills DB with demo data */
        loadInitScript();

        /* Stops any services that must be stopped gracefully */
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));
    }

    private void startDB() {
        try {
            Server.createTcpServer("-tcpPort", dbPort, "-tcpAllowOthers").start();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot start H2 server in TCP mode");
        }
    }

    private void stopDB() {
        try {
            Server.shutdownTcpServer(dbShutDownURL, "", true, true);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot stop H2 server in TCP mode");
        }
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
        stopDB();
    }
}
