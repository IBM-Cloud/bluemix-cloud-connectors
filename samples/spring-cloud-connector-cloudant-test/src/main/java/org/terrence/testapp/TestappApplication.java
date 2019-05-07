package org.terrence.testapp;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.impl.StdCouchDbConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestappApplication.class, args);
    }

    @Bean
    public CouchDbConnector couchDbConnector(CouchDbInstance couchDbInstance) {
        CouchDbConnector connector = new StdCouchDbConnector("status", couchDbInstance);
        connector.createDatabaseIfNotExists();
        return connector;
    }

}
