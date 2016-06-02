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

import org.springframework.cloud.service.BaseServiceInfo;

/**
 * Service info for IBM's Object Storage service.
 * @author ryanjbaxter
 *
 */
public class IBMObjectStorageServiceInfo extends BaseServiceInfo {
  
  /**
   * Scheme used in URL for local configuration.
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
  public IBMObjectStorageServiceInfo(String id, String authUrl, String domainId, String domainName,
          String password, String project, String projectId, String region, String userId,
          String username) {
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
  
  public IBMObjectStorageServiceInfo(String id, String url) throws URISyntaxException {
    super(id);
    String modUrl = url.replaceFirst(IBMObjectStorageServiceInfo.SCHEME, "https");
    URI uri = new URI(modUrl);
    this.authUrl = uri.getScheme() + "://" + uri.getHost();
    if(uri.getPort() > 0) {
      this.authUrl = authUrl + ":" + uri.getPort();
    }
    if(uri.getUserInfo() != null) {
      String[] credentials = uri.getUserInfo().split(":");
      this.userId = credentials[0];
      this.password = credentials[1];
    }
    if(uri.getPath() != null) {
      //the path includes the '/' after the host
      String[] path = uri.getPath().split("/");
      if(path.length == 3) {
        this.project = path[1];
        this.domainName = path[2];
      }
    }
    
  }

  /**
   * Gets the authentication URL.
   * @return The authentication URL.
   */
  public String getAuthUrl() {
    return authUrl;
  }
  
  /**
   * Sets the authentication URL.
   * @param authUrl The authentication URL to set.
   */
  public void setAuthUrl(String authUrl) {
    this.authUrl = authUrl;
  }
  
  /**
   * Gets the domain ID.
   * @return The domain ID.
   */
  public String getDomainId() {
    return domainId;
  }
  
  /**
   * Sets the domain ID.
   * @param domainId The domain ID to set.
   */
  public void setDomainId(String domainId) {
    this.domainId = domainId;
  }
  
  /**
   * Gets the domain name.
   * @return The domain name.
   */
  public String getDomainName() {
    return domainName;
  }
  
  /**
   * Sets the domain name.
   * @param domainName The domain name to set.
   */
  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }
  
  /**
   * Gets the password.
   * @return The password.
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * Sets the password.
   * @param password The password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * Gets the project.
   * @return The project.
   */
  public String getProject() {
    return project;
  }
  
  /**
   * Sets the project.
   * @param project The project to set.
   */
  public void setProject(String project) {
    this.project = project;
  }
  
  /**
   * Gets the project ID.
   * @return The project ID.
   */
  public String getProjectId() {
    return projectId;
  }
  
  /**
   * Sets the project ID.
   * @param projectId The project ID to set.
   */
  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }
  
  /**
   * Gets the region in which the object storage service is run.
   * @return The region is which the object storage service is run.
   */
  public String getRegion() {
    return region;
  }
  
  /**
   * Sets the region in which the object storage service is run.
   * @param region The region to set.
   */
  public void setRegion(String region) {
    this.region = region;
  }
  
  /**
   * Gets the user ID to authenticate with the object storage service.
   * @return The user ID to authentication with the object storage service.
   */
  public String getUserId() {
    return userId;
  }
  
  /**
   * Sets the user ID to authenticate with the object storage service.
   * @param userId The user ID to set.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  /**
   * Gets the username to authenticate with the object storage service.
   * @return The username to authenticate with the object storage service.
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * Sets the username to authenticate with the object storage service.
   * @param username The username to set.
   */
  public void setUsername(String username) {
    this.username = username;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((authUrl == null) ? 0 : authUrl.hashCode());
    result = prime * result + ((domainId == null) ? 0 : domainId.hashCode());
    result = prime * result + ((domainName == null) ? 0 : domainName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((project == null) ? 0 : project.hashCode());
    result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
    result = prime * result + ((region == null) ? 0 : region.hashCode());
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    IBMObjectStorageServiceInfo other = (IBMObjectStorageServiceInfo) obj;
    if (authUrl == null) {
      if (other.authUrl != null)
        return false;
    } else if (!authUrl.equals(other.authUrl))
      return false;
    if (domainId == null) {
      if (other.domainId != null)
        return false;
    } else if (!domainId.equals(other.domainId))
      return false;
    if (domainName == null) {
      if (other.domainName != null)
        return false;
    } else if (!domainName.equals(other.domainName))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (project == null) {
      if (other.project != null)
        return false;
    } else if (!project.equals(other.project))
      return false;
    if (projectId == null) {
      if (other.projectId != null)
        return false;
    } else if (!projectId.equals(other.projectId))
      return false;
    if (region == null) {
      if (other.region != null)
        return false;
    } else if (!region.equals(other.region))
      return false;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}

