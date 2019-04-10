/*
 * Copyright IBM Corp. 2019
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bluemix.connectors.core.creator;

import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.MongoSocketException;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.cloud.service.ServiceConnectorCreationException;
import org.springframework.cloud.service.document.MongoDbFactoryConfig;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import net.bluemix.connectors.core.info.DatabasesForMongoDBServiceInfo;
import net.bluemix.connectors.core.ssl.StringBasedSSLSocketFactory;
import net.bluemix.connectors.core.ssl.StringBasedTrustManager;

public class DatabasesForMongoDBCreator
        extends AbstractServiceConnectorCreator<MongoDbFactory, DatabasesForMongoDBServiceInfo> {

    private static final Logger LOG = Logger.getLogger(DatabasesForMongoDBCreator.class.getName());

    @Override
    public MongoDbFactory create(DatabasesForMongoDBServiceInfo serviceInfo, ServiceConnectorConfig config) {
        try {
            MongoClientOptions.Builder mongoOptionsToUse = getMongoOptions((MongoDbFactoryConfig) config);

            byte[] cert = serviceInfo.getCertData();
            if (cert != null) {
                try {
                    StringBasedTrustManager.getTrustManager().addCert(cert);
                } catch (Exception e) {
                    LOG.log(Level.SEVERE, "Unable to add certificate to trust manager", e);
                }
            }
            mongoOptionsToUse.socketFactory(new StringBasedSSLSocketFactory(""));

            SimpleMongoDbFactory mongoDbFactory = createMongoDbFactory(serviceInfo, mongoOptionsToUse);

            MongoDbFactory f = configure(mongoDbFactory, (MongoDbFactoryConfig) config);
            if (System.getenv("ICD_MONGO_PREGET") != null) {
                try {
                    MongoDatabase db = f.getDb();
                } catch (MongoSocketException e) {
                    LOG.log(Level.WARNING, "Initial Mongo Exception", e);
                }
            }
            return f;
        } catch (UnknownHostException e) {
            throw new ServiceConnectorCreationException(e);
        } catch (MongoException e) {
            throw new ServiceConnectorCreationException(e);
        } catch (GeneralSecurityException e) {
            throw new ServiceConnectorCreationException(e);
        }
    }

    private String buildUriString(DatabasesForMongoDBServiceInfo serviceInfo) {
        List<String> uris = serviceInfo.getUris();
        if (uris.size() == 1) {
            return uris.get(0);
        } else {
            String uri = "mongodb://";
            int schemelen = uri.length();
            boolean first = true;
            for (String s : uris) {
                int start = schemelen;
                if (!first) {
                    uri += ",";
                    start = s.indexOf("@", schemelen + 1) + 1;
                } else {
                    first = false;
                }
                uri += s.substring(start, s.indexOf("/", schemelen + 1));
            }
            uri += uris.get(0).substring(uris.get(0).indexOf("/", schemelen + 1));
            if (!uri.contains("replica_set=")) {
                if (uri.contains("?")) {
                    uri += "&replica_set=replset";
                } else {
                    uri += "?replica_set=replset";
                }
            }
            return uri;
        }
    }

    private SimpleMongoDbFactory createMongoDbFactory(DatabasesForMongoDBServiceInfo serviceInfo,
            MongoClientOptions.Builder mongoOptionsToUse) throws UnknownHostException {
        MongoClientURI mongoClientURI = new MongoClientURI(buildUriString(serviceInfo), mongoOptionsToUse);
        MongoClient mongo = new MongoClient(mongoClientURI);
        return new SimpleMongoDbFactory(mongo, mongoClientURI.getDatabase());
    }

    private MongoClientOptions.Builder getMongoOptions(MongoDbFactoryConfig config) {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        if (config != null) {
            if (config.getConnectionsPerHost() != null) {
                builder.connectionsPerHost(config.getConnectionsPerHost());
            }
            if (config.getMaxWaitTime() != null) {
                builder.maxWaitTime(config.getMaxWaitTime());
            }
            if (config.getWriteConcern() != null) {
                builder.writeConcern(new WriteConcern(config.getWriteConcern()));
            }
        }
        return builder;
    }

    public SimpleMongoDbFactory configure(SimpleMongoDbFactory mongoDbFactory, MongoDbFactoryConfig config) {
        if (config != null && config.getWriteConcern() != null) {
            WriteConcern writeConcern = WriteConcern.valueOf(config.getWriteConcern());
            if (writeConcern != null) {
                mongoDbFactory.setWriteConcern(writeConcern);
            }
        }
        return mongoDbFactory;
    }
}