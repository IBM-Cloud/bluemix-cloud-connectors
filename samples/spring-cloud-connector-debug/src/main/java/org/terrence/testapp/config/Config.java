package org.terrence.testapp.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Configuration;

public class Config {

    @Configuration
    static class CloudConfiguration extends AbstractCloudConfig {

    }
}