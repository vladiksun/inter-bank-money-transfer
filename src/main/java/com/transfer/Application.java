package com.transfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.transfer.service.LifecycleManager;
import com.transfer.spark.SparkController;
import org.h2.tools.Server;

public class Application {

    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        Injector injector = Guice.createInjector(new MainModule());
        injector.getInstance(LifecycleManager.class).onStart();
        activateSparkControllers(injector);
    }

    private void activateSparkControllers(Injector injector) {
        injector.getAllBindings().keySet().stream()
                .filter(key -> SparkController.class.isAssignableFrom(key.getTypeLiteral().getRawType()))
                .forEach(key -> {
                    SparkController sparkController = (SparkController) injector.getInstance(key);
                    sparkController.init();
                });
    }
}
