/*
 * Copyright IBM Corp. 2015
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

import net.bluemix.connectors.core.info.CloudantServiceInfo;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

/**
 * Creates a new {@link CloudantServiceInfo}.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 *
 */
public class CloudantServiceInfoCreator extends CloudFoundryServiceInfoCreator<CloudantServiceInfo> {

    /**
     * Constructor.
     */
    public CloudantServiceInfoCreator() {
        super(new Tags(), CloudantServiceInfo.SCHEME);
    }

    @Override
    public boolean accept(final Map<String, Object> serviceData) {
        boolean result = false;
        // Don't really like using the label as the determining factor but that is the only
        // unique attribute to identify the service with.
        Object obj = serviceData.get("label");
        if (obj instanceof String) {
            String label = (String) obj;
            result = "cloudantNoSQLDB".equals(label);
        }
        return result;
    }

    @Override
    public CloudantServiceInfo createServiceInfo(final Map<String, Object> serviceData) {
        String id = null;
        String username = null;
        String password = null;
        String host = null;
        int port = 0;
        String url = null;
        Object credObject = serviceData.get("credentials");
        Object idObj = serviceData.get("name");
        if (idObj instanceof String) {
            id = (String) idObj;
        }
        if (credObject instanceof Map<?, ?>) {
            final Map<String, Object> credentials = (Map<String, Object>) credObject;
            final Object usernameObj = credentials.get("username");
            final Object passwordObj = credentials.get("password");
            final Object hostObj = credentials.get("host");
            final Object portObj = credentials.get("port");
            final Object urlObj = credentials.get("url");
            if (usernameObj instanceof String) {
                username = (String) usernameObj;
            }
            if (passwordObj instanceof String) {
                password = (String) passwordObj;
            }
            if (hostObj instanceof String) {
                host = (String) hostObj;
            }
            if (portObj instanceof Integer) {
                port = (Integer) portObj;
            }
            if (urlObj instanceof String) {
                url = (String) urlObj;
            }
        }
        return new CloudantServiceInfo(id, username, password, host, port, url);
    }

}
