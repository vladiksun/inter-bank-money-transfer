package com.transfer.utils;

import com.google.common.base.Charsets;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.IOUtils;
import org.h2.tools.Server;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Contains startup and shutdown application logic
 */
public class LifecycleManager {

    final private Logger logger = LoggerFactory.getLogger(LifecycleManager.class);

    private static final String INIT_DDL_SCRIPT = "/db-h2.sql";

    private final Configuration configuration;
    private String dbPort;
    private String dbShutDownURL;

    @Inject
    public LifecycleManager(final Configuration configuration,
                            @Named("db.port") String dbPort,
                            @Named("db.shutDownURL") String dbShutDownURL) {
        this.configuration = configuration;
        this.dbPort = dbPort;
        this.dbShutDownURL = dbShutDownURL;
    }

    public void onStart() {

        Server h2Server = startDB();

        /* creates DB schema and fills DB with demo data */
        loadInitScript();

        /* Stops any services that must be stopped gracefully */
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));
        Runtime.getRuntime().addShutdownHook(new Thread(h2Server::stop));
    }

    /* Starts H2 database server in TCP mode */
    private Server startDB() {
        Server h2Server;

        try {
            h2Server = Server.createTcpServer("-tcpPort", dbPort, "-tcpAllowOthers").start();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot start H2 server in TCP mode");
        }

        return h2Server;
    }

    private void loadInitScript() {
        DSLContext dsl = DSL.using(configuration);
        try {
            String dml = IOUtils.resourceToString(INIT_DDL_SCRIPT, Charsets.UTF_8);
            dsl.transaction(config ->
                    dsl.execute(dml));
        } catch (IOException e) {
            throw new RuntimeException("Cannot read DML statements from the file");
        }
    }

    private void onShutdown() {

    }
}
