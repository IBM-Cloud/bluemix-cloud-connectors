package org.terrence.testapp.config;

import org.ektorp.CouchDbInstance;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Config {

    @Configuration
    static class CloudConfiguration extends AbstractCloudConfig {

        @Bean
        public CouchDbInstance couchDbInstance() {
            CouchDbInstance instance = connectionFactory().service(CouchDbInstance.class);
            return instance;
        }
    }
}