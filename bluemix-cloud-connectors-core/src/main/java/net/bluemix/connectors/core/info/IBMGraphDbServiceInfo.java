/*
 * Copyright IBM Corp. 2016
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import org.springframework.cloud.service.BaseServiceInfo;

/**
 * Service information for the IBM Graph DB service.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public class IBMGraphDbServiceInfo extends BaseServiceInfo {

    /**
     * Scheme. This is not a real scheme but is needed for local config to work.
     */
    public static final String SCHEME = "ibmgraphdb";

    private String apiUrl;
    private String username;
    private String password;

    /**
     * Constructor.
     *
     * @param id The id of the service.
     * @param apiUrl The API URL for the service.
     * @param username The username to authenticate with the service.
     * @param password The password for the user to authenticate with the
     * service.
     */
    public IBMGraphDbServiceInfo(
            final String id, final String apiUrl,
            final String username, final String password) {
        super(id);
        this.apiUrl = apiUrl;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor.
     *
     * @param id The id of the service.
     * @param url The URL to the graph DB instance.
     * @throws URISyntaxException Thrown if the URL is malformed.
     */
    public IBMGraphDbServiceInfo(final String id, final String url) throws URISyntaxException {
        super(id);
        final URI uri = new URI(url);
        this.apiUrl = url.replaceFirst(IBMGraphDbServiceInfo.SCHEME, "https");
        if (uri.getUserInfo() != null) {
            this.apiUrl = apiUrl.replaceFirst(uri.getUserInfo(), "");
            this.apiUrl = apiUrl.replaceFirst("@", "");
        }
        final String serviceInfoString = uri.getUserInfo();
        if (serviceInfoString != null) {
            String[] userInfo = serviceInfoString.split(":");
            this.username = userInfo[0];
            this.password = userInfo[1];
        }
    }

    /**
     * Gets the API URL.
     *
     * @return The API URL.
     */
    @ServiceProperty
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * Sets the API URL.
     *
     * @param apiUrl The API URL to be set.
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * Gets the username to authenticate with the service.
     *
     * @return The username to authenticate with the service.
     */
    @ServiceProperty
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username to authenticate with the service.
     *
     * @param username The username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password for the username.
     *
     * @return The password for the username.
     */
    @ServiceProperty
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the username.
     *
     * @param password The password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.apiUrl);
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.password);
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
        final IBMGraphDbServiceInfo other = (IBMGraphDbServiceInfo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.apiUrl, other.apiUrl)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IBMGraphDBServiceInfo"
                + '{'
                + "id=" + id + ", "
                + "apiUrl=" + apiUrl + ", "
                + "username=" + username + ", "
                + "password=" + password
                + '}';
    }

}
