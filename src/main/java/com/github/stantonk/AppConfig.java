package com.github.stantonk;

import com.netflix.governator.annotations.Configuration;
import com.netflix.governator.configuration.ConfigurationProvider;
import com.netflix.governator.configuration.PropertiesConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

//    @Configuration("${env}.some.thing.enabled")
    @Configuration("some.thing.enabled")
    public boolean someThingEnabled = false;

    //    @Configuration("${env}.some.thing.enabled")
    @Configuration("some.thing.timeout")
    public int someThingTimeout = 10;

//    public static final ConfigurationProvider CONFIG_PROVIDER = new SystemConfigurationProvider();
    public static final ConfigurationProvider CONFIG_PROVIDER = new PropertiesConfigurationProvider(loadProperties("app.properties"));

    private static Properties loadProperties(String filename) {
        logger.info("loading...");
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(filename));
        } catch (IOException e) {
            //TODO: log error...hrm
            logger.error(filename + " not found, using defaults for configuration");
        }
        return p;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "someThingEnabled=" + someThingEnabled +
                ", someThingTimeout=" + someThingTimeout +
                '}';
    }
}
