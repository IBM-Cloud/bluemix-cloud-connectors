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
 * Service information for the IBM Graph DB service.
 * @author ryanjbaxter
 *
 */
public class IBMGraphDbServiceInfo extends BaseServiceInfo {
  
  /**
   * Graph DB scheme.
   * This is not a real scheme but is needed for local config to work.
   */
  public static final String GRAPH_DB_SCHEME = "ibmgraphdb";
	
	private String apiUrl;
	private String username;
	private String password;

	/**
	 * Constructor.
	 * @param id The id of the service.
	 * @param apiUrl The API URL for the service.
	 * @param username The username to authenticate with the service.
	 * @param password The password for the user to authenticate with the service.
	 */
	public IBMGraphDbServiceInfo(String id, String apiUrl, String username, String password) {
		super(id);
		this.id = id;
		this.apiUrl = apiUrl;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Constructor.
	 * @param id The id of the service.
	 * @param url The URL to the graph DB instance.
	 * @throws URISyntaxException Thrown if the URL is malformed.
	 */
	public IBMGraphDbServiceInfo(String id, String url) throws URISyntaxException {
	  super(id);
	  URI uri = new URI(url);
    this.apiUrl = url.replaceFirst(IBMGraphDbServiceInfo.GRAPH_DB_SCHEME, "https");
    if(uri.getUserInfo() != null) {
      this.apiUrl = apiUrl.replaceFirst(uri.getUserInfo(), "");
      this.apiUrl = apiUrl.replaceFirst("@", "");
    }
    String serviceInfoString = uri.getUserInfo();
    if(serviceInfoString != null) {
      String[] userInfo = serviceInfoString.split(":");
      this.username = userInfo[0];
      this.password = userInfo[1];
    }
	}

	/**
	 * Gets the API URL.
	 * @return The API URL.
	 */
  public String getApiUrl() {
    return apiUrl;
  }

  /**
   * Sets the API URL.
   * @param apiUrl The API URL to be set.
   */
  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  /**
   * Gets the username to authenticate with the service.
   * @return The username to authenticate with the service.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username to authenticate with the service.
   * @param username The username to be set.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password for the username.
   * @return The password for the username.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password for the username.
   * @param password The password to be set.
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Override
  public String toString() {
    return "IBMGraphDBServiceInfo [apiUrl=" + apiUrl + ", username=" + username + ", password="
            + password + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((apiUrl == null) ? 0 : apiUrl.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
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
    IBMGraphDbServiceInfo other = (IBMGraphDbServiceInfo) obj;
    if (apiUrl == null) {
      if (other.apiUrl != null)
        return false;
    } else if (!apiUrl.equals(other.apiUrl))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
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
