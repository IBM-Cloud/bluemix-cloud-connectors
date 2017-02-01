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
package net.bluemix.connectors.core.info;

import java.util.Objects;

import org.springframework.cloud.service.BaseServiceInfo;

/**
 * Represents the Watson Personality Insights VCAP_SERVICE info.
 *
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 *
 */
public class WatsonPersonalityInsightsServiceInfo extends BaseServiceInfo {

    /**
     * Scheme. This is not a real scheme but is needed for local config to work.
     */
    public static final String SCHEME = "personality-insights";

    private final String username;
    private final String password;
    private final String url;

    /**
     * Constructor.
     *
     * @param id The service id.
     * @param username The username for the service.
     * @param password The password for the service.
     * @param url The URL to the service.
     */
    public WatsonPersonalityInsightsServiceInfo(
            final String id,
            final String username,
            final String password,
            final String url) {
        super(id);
        this.username = username;
        this.password = password;
        this.url = url;
    }

    /**
     * Gets the username.
     *
     * @return The username.
     */
    @ServiceProperty
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return The password.
     */
    @ServiceProperty
    public String getPassword() {
        return password;
    }

    /**
     * Gets the URL.
     *
     * @return The URL.
     */
    @ServiceProperty
    public String getUrl() {
        return url;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.username);
        hash = 17 * hash + Objects.hashCode(this.password);
        hash = 17 * hash + Objects.hashCode(this.url);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final WatsonPersonalityInsightsServiceInfo other = (WatsonPersonalityInsightsServiceInfo) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "WatsonPersonalityInsightsServiceInfo"
                + '{'
                + "id=" + id + ", "
                + "username=" + username + ", "
                + "password=" + password + ", "
                + "url=" + url
                + '}';
    }

}
