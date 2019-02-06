package com.transfer;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.transfer.utils.LifecycleManager;
import com.transfer.spark.SparkController;

import static spark.Spark.port;

public class Application {

    @Parameter(names={"--port", "-p"})
    int sparkPort;

    public static void main(String[] args) {
        Application application = new Application();

        JCommander.newBuilder()
                .addObject(application)
                .build()
                .parse(args);

        application.start();
    }

    private void start() {
        Injector injector = Guice.createInjector(new MainModule());
        injector.getInstance(LifecycleManager.class).onStart();
        activateSparkControllers(injector);
    }

    private void activateSparkControllers(Injector injector) {
        if (sparkPort != 0) {
            port(sparkPort);
        }

        injector.getAllBindings().keySet().stream()
                .filter(key -> SparkController.class.isAssignableFrom(key.getTypeLiteral().getRawType()))
                .forEach(key -> {
                    SparkController sparkController = (SparkController) injector.getInstance(key);
                    sparkController.init();
                });
    }
}
