package org.terrence.testapp.config;

import com.twilio.sdk.TwilioRestClient;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Config {

    @Configuration
    static class CloudConfiguration extends AbstractCloudConfig {

        @Bean
        public TwilioRestClient twilioRestClient() {
            TwilioRestClient instance = connectionFactory().service(TwilioRestClient.class);
            return instance;
        }
    }
}