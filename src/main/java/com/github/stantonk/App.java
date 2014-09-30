package com.github.stantonk;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.lifecycle.LifecycleManager;
import com.sun.xml.internal.xsom.impl.WildcardImpl;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private final AppConfig config;
    private final OtherConfig otherConfig;

    @Inject
    public App(AppConfig config, OtherConfig otherConfig) {
        this.config = config;
        this.otherConfig = otherConfig;
    }

    public static void main( String[] args ) throws Exception {
        Injector injector = LifecycleInjector
                .builder()
                .withBootstrapModule(new BootstrapModule() {
                    @Override
                    public void configure(BootstrapBinder binder) {
                        binder.bindConfigurationProvider().toInstance(AppConfig.CONFIG_PROVIDER);
                    }
                })
                .withModules(new AppModule())
                .build()
                .createInjector();

        LifecycleManager manager = injector.getInstance(LifecycleManager.class);
        manager.start();
        App instance = injector.getInstance(App.class);
        System.out.println(instance.config);
        System.out.println(instance.otherConfig);

        manager.close();
    }

    public static class AppModule extends AbstractModule {

        @Override
        protected void configure() {

        }
    }
}
