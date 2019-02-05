package com.testsupport;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.transfer.MainModule;
import com.transfer.utils.LifecycleManager;
import org.junit.rules.ExternalResource;

/**
 * Container for integration tests.
 * <p>Usage of the common instance (time saving):</p>
 * <pre>
 *    {@literal @}ClassRule
 *     public static TestContainer cont = TestContainer.Common.INSTANCE;
 * </pre>
 */

public class TestContainer extends ExternalResource {

    private Injector injector;

    public static class Common extends TestContainer {

        public static final Common INSTANCE = new Common();

        private static volatile boolean initialized;

        private Common() {
        }

        @Override
        public void before() throws Throwable {
            if (!initialized) {
                super.before();
                initialized = true;
            }
            setupContext();
        }

        @Override
        public void after() {
            cleanupContext();
        }
    }

    public  <T> T getBean(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    protected void setupContext() {
        Injector injector = Guice.createInjector(new MainModule());
        this.injector = injector;
        injector.getInstance(LifecycleManager.class).onStart();
    }

    protected void cleanupContext() {
    }
}
