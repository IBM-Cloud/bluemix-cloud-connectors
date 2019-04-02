/*
 * Copyright IBM Corp. 2019
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bluemix.connectors.cloudfoundry.creator;

import java.util.Base64;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.cloud.service.common.PostgresqlServiceInfo;

import net.bluemix.connectors.core.ssl.StringBasedSSLSocketFactory;
import net.bluemix.connectors.core.ssl.StringBasedTrustManager;

public class DatabasesForPostgresqlServiceInfoCreator
        extends DatabasesForCloudServiceInfoCreator<PostgresqlServiceInfo> {

    private static final Logger LOG = Logger.getLogger(DatabasesForPostgresqlServiceInfoCreator.class.getName());

    public DatabasesForPostgresqlServiceInfoCreator() {
        super("databases-for-postgresql", "postgres");
    }

    @Override
    public PostgresqlServiceInfo createServiceInfo(Map<String, Object> serviceData) {
        String id = getId(serviceData);

        Map<String, Object> credentials = getCredentials(serviceData);
        String uri = getUriFromCredentials(credentials);

        String cert64 = getRootCaFromCredentials(credentials);
        if (cert64 != null && uri.contains("sslmode")) {
            if (!uri.contains("sslfactory")) {
                try {
                    StringBasedTrustManager.getTrustManager().addCert(Base64.getDecoder().decode(cert64));
                    if (!uri.contains("?")) {
                        uri += "?sslfactory=" + StringBasedSSLSocketFactory.class.getCanonicalName();
                    } else {
                        uri += "&sslfactory=" + StringBasedSSLSocketFactory.class.getCanonicalName();
                    }
                } catch (Exception e) {
                    LOG.log(Level.SEVERE, "Unable to add certificate to trust manager", e);
                }
            } else {
                LOG.log(Level.WARNING,
                        "sslfactory already present in postgresql, not adding custom handler. Expect truststore issues.");
            }
        }

        return new PostgresqlServiceInfo(id, uri);
    }
}