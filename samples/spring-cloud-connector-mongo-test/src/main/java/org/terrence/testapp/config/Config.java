package org.terrence.testapp.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Config {

    @Configuration
    static class CloudConfiguration extends AbstractCloudConfig {

        @Bean
        public MongoDbFactory mongoDbFactory() {
            MongoDbFactory instance = connectionFactory().service(MongoDbFactory.class);
            return instance;
        }

        @Bean // this gets used under the covers somehow by the repo
        public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
            return new MongoTemplate(mongoDbFactory);
        }
    }
}