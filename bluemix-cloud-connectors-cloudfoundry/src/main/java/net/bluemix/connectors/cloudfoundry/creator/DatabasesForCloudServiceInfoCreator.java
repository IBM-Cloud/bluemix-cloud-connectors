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

import java.util.List;
import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;
import org.springframework.cloud.service.ServiceInfo;

public abstract class DatabasesForCloudServiceInfoCreator<SI extends ServiceInfo>
        extends CloudFoundryServiceInfoCreator<SI> {

    String label;
    String connectionName;

    public DatabasesForCloudServiceInfoCreator(String label, String connectionName) {
        super(new Tags(label), (String[]) null);
        this.label = label;
        this.connectionName = connectionName;
    }

    public boolean accept(Map<String, Object> serviceData) {
        if (labelMatches(serviceData)) {
            Map<String, Object> creds = getCredentials(serviceData);
            if (creds == null) {
                return false;
            }
            if (getId(serviceData) == null) {
                return false;
            }
            if (getUriFromCredentials(creds) == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    protected boolean labelMatches(Map<String, Object> serviceData) {
        String label = (String) serviceData.get("label");
        return this.label.equals(label);
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> getCredentials(Map<String, Object> serviceData) {
        return (Map<String, Object>) serviceData.get("credentials");
    }

    protected String getId(Map<String, Object> serviceData) {
        return (String) serviceData.get("name");
    }

    protected List<String> getUrisFromCredentials(Map<String, Object> credentials) {
        Map<String, Object> connection = (Map<String, Object>) credentials.get("connection");
        if (connection != null) {
            Map<String, Object> details = (Map<String, Object>) connection.get(connectionName);
            if (details != null) {
                List<String> uris = (List<String>) details.get("composed");
                return uris;
            }
        }
        return null;
    }

    protected String getUriFromCredentials(Map<String, Object> credentials) {
        return getUrisFromCredentials(credentials).get(0);
    }

    protected String getHostFromCredentials(Map<String, Object> credentials) {
        Map<String, Object> connection = (Map<String, Object>) credentials.get("connection");
        if (connection != null) {
            Map<String, Object> details = (Map<String, Object>) connection.get(connectionName);
            if (details != null) {
                List<Map<String, Object>> hosts = (List<Map<String, Object>>) details.get("hosts");
                if (hosts != null) {
                    Object h = hosts.get(0).get("hostname");
                    if (h != null)
                        return h.toString();
                }
            }
        }
        return null;
    }

    protected String getPortFromCredentials(Map<String, Object> credentials) {
        Map<String, Object> connection = (Map<String, Object>) credentials.get("connection");
        if (connection != null) {
            Map<String, Object> details = (Map<String, Object>) connection.get(connectionName);
            if (details != null) {
                List<Map<String, Object>> hosts = (List<Map<String, Object>>) details.get("hosts");
                if (hosts != null) {
                    Object h = hosts.get(0).get("port");
                    if (h != null)
                        return h.toString();
                }
            }
        }
        return null;
    }

    protected String getSchemeFromCredentials(Map<String, Object> credentials) {
        Map<String,Object> connection = (Map<String, Object>) credentials.get("connection");
        if(connection!=null){
            Map<String,Object> details = (Map<String, Object>) connection.get(connectionName);
            if(details!=null){
               return (String)details.get("scheme");
            }
        }
        return null;
    }

    protected String getPasswordFromCredentials(Map<String, Object> credentials) {
        Map<String, Object> connection = (Map<String, Object>) credentials.get("connection");
        if (connection != null) {
            Map<String, Object> details = (Map<String, Object>) connection.get(connectionName);
            if (details != null) {
                Map<String, Object> auth = (Map<String, Object>) details.get("authentication");
                if (auth != null) {
                    Object h = auth.get("password");
                    if (h != null)
                        return h.toString();
                }
            }
        }
        return null;
    }

    protected String getRootCaFromCredentials(Map<String, Object> credentials) {
        Map<String, Object> connection = (Map<String, Object>) credentials.get("connection");
        if (connection != null) {
            Map<String, Object> details = (Map<String, Object>) connection.get(connectionName);
            if (details != null) {
                Map<String, Object> certinfo = (Map<String, Object>) details.get("certificate");
                return (String) certinfo.get("certificate_base64");
            }
        }
        return null;
    }

}
