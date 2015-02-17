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
package net.bluemix.connectors.core.info;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.cloud.service.BaseServiceInfo;

/**
 * Represents the Cloudant VCAP_SERVICE info.
 * @author ryanjbaxter
 *
 */
public class CloudantServiceInfo extends BaseServiceInfo {
  
  /**
   * Cloudant scheme.
   * This is not a real scheme but is needed for local config to work.
   */
  public static final String CLOUDANT_SCHEME = "couchdb";
  
  private String username;
  private String password;
  private String host;
  private int port;
  private String url;

  /**
   * Constructor.
   * @param id The service id.
   * @param username The username for the service.
   * @param password The password for the service.
   * @param host The host name for the service.
   * @param port The port for the service.
   * @param url The URL to the service.  This should include the username and password.
   */
	public CloudantServiceInfo(String id, String username, String password, String host, int port, String url) {
		super(id);
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
		this.url = url;
	}
	
	public CloudantServiceInfo(String id, String url) throws URISyntaxException {
	  super(id);
	  URI uri = new URI(url);
	  this.url = url.replace(CloudantServiceInfo.CLOUDANT_SCHEME, "http");
	  this.host = uri.getHost();
	  this.port = uri.getPort();
	  String serviceInfoString = uri.getUserInfo();
	  if(serviceInfoString != null) {
	    String[] userInfo = serviceInfoString.split(":");
	    this.username = userInfo[0];
	    this.password = userInfo[1];
	  }
	}

	/**
	 * Gets the username.
	 * @return The username.
	 */
	@ServiceProperty
  public String getUsername() {
    return username;
  }

	/**
	 * Gets the password.
	 * @return The password.
	 */
	@ServiceProperty
  public String getPassword() {
    return password;
  }

	/**
	 * Gets the host.
	 * @return The host.
	 */
	@ServiceProperty
  public String getHost() {
    return host;
  }

	/**
	 * Gets the port.
	 * @return The port.
	 */
	@ServiceProperty
  public int getPort() {
    return port;
  }

	/**
	 * Gets the URL.
	 * @return The URL.
	 */
	@ServiceProperty
  public String getUrl() {
    return url;
	}

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((host == null) ? 0 : host.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + port;
    result = prime * result + ((url == null) ? 0 : url.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == null || !(obj instanceof CloudantServiceInfo)) {
      return false;
    }
    boolean result = false;
    CloudantServiceInfo test = (CloudantServiceInfo)obj;
    result = test.getHost() != null ? test.getHost().equals(this.getHost()) : test.getHost() == this.getHost();
    result &= test.getPassword() != null ? test.getPassword().equals(this.getPassword()) : test.getPassword() == this.getPassword();
    result &= test.getUsername() != null ? test.getUsername().equals(this.getUsername()) : test.getUsername() == this.getUsername();
    result &= test.getId() != null ? test.getId().equals(this.getId()) : test.getId() == this.getId();
    result &= test.getPort() == this.getPort();
    result &= test.getUrl() != null ? test.getUrl().equals(this.getUrl()) : test.getUrl() == this.getUrl();
    return result;
  }
	
  @Override
  public String toString() {
    return "CloudantServiceInfo [username=" + username + ", password=" + password + ", host="
            + host + ", port=" + port + ", url=" + url + "]";
  }
}
