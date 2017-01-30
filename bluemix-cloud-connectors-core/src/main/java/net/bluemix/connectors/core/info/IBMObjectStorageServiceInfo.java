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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import org.springframework.cloud.service.BaseServiceInfo;

/**
 * Service info for IBM's Object Storage service.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public class IBMObjectStorageServiceInfo extends BaseServiceInfo {

    /**
     * Scheme. This is not a real scheme but is needed for local config to work.
     */
    public static final String SCHEME = "swiftobjectstorage";

    private String authUrl;
    private String domainId;
    private String domainName;
    private String password;
    private String project;
    private String projectId;
    private String region;
    private String userId;
    private String username;

    /**
     * Constructor.
     *
     * @param id The ID of the service.
     * @param authUrl The authentication URL.
     * @param domainId The domain ID.
     * @param domainName The domain name.
     * @param password The password.
     * @param project The project.
     * @param projectId The project ID.
     * @param region The region the service is located in.
     * @param userId The user ID.
     * @param username The username.
     */
    public IBMObjectStorageServiceInfo(
            final String id, final String authUrl, final String domainId,
            final String domainName, final String password, final String project,
            final String projectId, final String region, final String userId,
            final String username) {
        super(id);
        this.authUrl = authUrl;
        this.domainId = domainId;
        this.domainName = domainName;
        this.password = password;
        this.project = project;
        this.projectId = projectId;
        this.region = region;
        this.userId = userId;
        this.username = username;
    }

    public IBMObjectStorageServiceInfo(final String id, final String url) throws URISyntaxException {
        super(id);
        final String modUrl = url.replaceFirst(IBMObjectStorageServiceInfo.SCHEME, "https");
        final URI uri = new URI(modUrl);
        this.authUrl = uri.getScheme() + "://" + uri.getHost();
        if (uri.getPort() > 0) {
            this.authUrl = authUrl + ":" + uri.getPort();
        }
        if (uri.getUserInfo() != null) {
            final String[] credentials = uri.getUserInfo().split(":");
            this.userId = credentials[0];
            this.password = credentials[1];
        }
        if (uri.getPath() != null) {
            //the path includes the '/' after the host
            final String[] path = uri.getPath().split("/");
            if (path.length == 3) {
                this.project = path[1];
                this.domainName = path[2];
            }
        }

    }

    /**
     * Gets the authentication URL.
     *
     * @return The authentication URL.
     */
    @ServiceProperty
    public String getAuthUrl() {
        return authUrl;
    }

    /**
     * Sets the authentication URL.
     *
     * @param authUrl The authentication URL to set.
     */
    public void setAuthUrl(final String authUrl) {
        this.authUrl = authUrl;
    }

    /**
     * Gets the domain ID.
     *
     * @return The domain ID.
     */
    @ServiceProperty
    public String getDomainId() {
        return domainId;
    }

    /**
     * Sets the domain ID.
     *
     * @param domainId The domain ID to set.
     */
    public void setDomainId(final String domainId) {
        this.domainId = domainId;
    }

    /**
     * Gets the domain name.
     *
     * @return The domain name.
     */
    @ServiceProperty
    public String getDomainName() {
        return domainName;
    }

    /**
     * Sets the domain name.
     *
     * @param domainName The domain name to set.
     */
    public void setDomainName(final String domainName) {
        this.domainName = domainName;
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
     * Sets the password.
     *
     * @param password The password to set.
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets the project.
     *
     * @return The project.
     */
    @ServiceProperty
    public String getProject() {
        return project;
    }

    /**
     * Sets the project.
     *
     * @param project The project to set.
     */
    public void setProject(final String project) {
        this.project = project;
    }

    /**
     * Gets the project ID.
     *
     * @return The project ID.
     */
    @ServiceProperty
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the project ID.
     *
     * @param projectId The project ID to set.
     */
    public void setProjectId(final String projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the region in which the object storage service is run.
     *
     * @return The region is which the object storage service is run.
     */
    @ServiceProperty
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region in which the object storage service is run.
     *
     * @param region The region to set.
     */
    public void setRegion(final String region) {
        this.region = region;
    }

    /**
     * Gets the user ID to authenticate with the object storage service.
     *
     * @return The user ID to authentication with the object storage service.
     */
    @ServiceProperty
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID to authenticate with the object storage service.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * Gets the username to authenticate with the object storage service.
     *
     * @return The username to authenticate with the object storage service.
     */
    @ServiceProperty
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username to authenticate with the object storage service.
     *
     * @param username The username to set.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.authUrl);
        hash = 59 * hash + Objects.hashCode(this.domainId);
        hash = 59 * hash + Objects.hashCode(this.domainName);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + Objects.hashCode(this.project);
        hash = 59 * hash + Objects.hashCode(this.projectId);
        hash = 59 * hash + Objects.hashCode(this.region);
        hash = 59 * hash + Objects.hashCode(this.userId);
        hash = 59 * hash + Objects.hashCode(this.username);
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
        final IBMObjectStorageServiceInfo other = (IBMObjectStorageServiceInfo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.authUrl, other.authUrl)) {
            return false;
        }
        if (!Objects.equals(this.domainId, other.domainId)) {
            return false;
        }
        if (!Objects.equals(this.domainName, other.domainName)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.project, other.project)) {
            return false;
        }
        if (!Objects.equals(this.projectId, other.projectId)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return Objects.equals(this.username, other.username);
    }

}
