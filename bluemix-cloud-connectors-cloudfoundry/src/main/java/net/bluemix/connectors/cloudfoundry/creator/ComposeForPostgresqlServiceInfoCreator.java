/*
 * Copyright IBM Corp. 2017
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

import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;
import org.springframework.cloud.service.common.PostgresqlServiceInfo;

/**
 * Creates a new {@link PostgresqlServiceInfo}.
 *
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 *
 */
public class ComposeForPostgresqlServiceInfoCreator extends CloudFoundryServiceInfoCreator<PostgresqlServiceInfo> {

    /**
     * Constructor.
     */
    public ComposeForPostgresqlServiceInfoCreator() {
        super(new Tags(), PostgresqlServiceInfo.POSTGRES_JDBC_SCHEME);
    }

    @Override
    public boolean accept(Map<String, Object> serviceData) {
        boolean result = false;
        // Don't really like using the label as the determining factor but that is the only
        // unique attribute to identify the service with.
        Object obj = serviceData.get("label");
        if (obj instanceof String) {
            String label = (String) obj;
            result = "compose-for-postgresql".equals(label);
        }
        return result;
    }

    @Override
    public PostgresqlServiceInfo createServiceInfo(final Map<String, Object> serviceData) {
        String id = null;
        String uri = null;
        Object credObject = serviceData.get("credentials");
        Object idObj = serviceData.get("name");
        if (idObj instanceof String) {
            id = (String) idObj;
        }
        if (credObject instanceof Map<?, ?>) {
            Map<String, Object> credentials = (Map<String, Object>) credObject;
            Object uriObj = credentials.get("uri");
            if (uriObj instanceof String) {
                uri = (String) uriObj;
            }
        }
        return new PostgresqlServiceInfo(id, uri);
    }

}
