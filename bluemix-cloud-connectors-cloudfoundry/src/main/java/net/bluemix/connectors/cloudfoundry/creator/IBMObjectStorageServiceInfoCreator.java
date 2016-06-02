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
package net.bluemix.connectors.cloudfoundry.creator;

import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import net.bluemix.connectors.core.info.IBMObjectStorageServiceInfo;

/**
 * Handles creating service info for the Object Storage service while running in Cloud Foundry.
 * @author ryanjbaxter
 *
 */
public class IBMObjectStorageServiceInfoCreator extends CloudFoundryServiceInfoCreator<IBMObjectStorageServiceInfo> {
  
  private static final String LABEL = "Object-Storage";

  /**
   * Constructor.
   */
  public IBMObjectStorageServiceInfoCreator() {
    super(new Tags(), IBMObjectStorageServiceInfo.SCHEME);
  }
  
  @Override
  public boolean accept(Map<String, Object> serviceData) {
    boolean result = false;
    // Don't really like using the label as the determining factor but that is the only
    // unique attribute to identify the service with.
    Object obj = serviceData.get("label");
    if(obj instanceof String) {
      String label = (String)obj;
      result = LABEL.equals(label);
    }
    return result;
  }

  @Override
  public IBMObjectStorageServiceInfo createServiceInfo(Map<String, Object> serviceData) {
    String id = null;
    String authUrl = null;
    String domainName = null;
    String domainId = null;
    String password = null;
    String project = null;
    String projectId = null;
    String region = null;
    String userId = null;
    String username = null;
    Object credObject = serviceData.get("credentials");
    Object idObj = serviceData.get("name");
    if(idObj instanceof String) { id = (String)idObj; }
    if(credObject instanceof Map<?, ?>) {
      Map<String, Object> credentials = (Map<String, Object>)credObject;
      Object authUrlObj = credentials.get("auth_url");
      Object domainNameObj = credentials.get("domainName");
      Object domainIdObj = credentials.get("domainId");
      Object passwordObj = credentials.get("password");
      Object projectObj = credentials.get("project");
      Object projectIdObj = credentials.get("projectId");
      Object regionObj = credentials.get("region");
      Object userIdObj = credentials.get("userId");
      Object usernameObj = credentials.get("username");
      if(authUrlObj instanceof String) { authUrl = (String)authUrlObj; }
      if(domainNameObj instanceof String) { domainName = (String)domainNameObj; }
      if(domainIdObj instanceof String) { domainId = (String)domainIdObj; }
      if(passwordObj instanceof String) { password = (String)passwordObj; }
      if(projectObj instanceof String) { project = (String)projectObj; }
      if(projectIdObj instanceof String) { projectId = (String)projectIdObj; }
      if(regionObj instanceof String) { region = (String)regionObj; }
      if(userIdObj instanceof String) { userId = (String)userIdObj; }
      if(usernameObj instanceof String) { username = (String)usernameObj; }
      
    }
    return new IBMObjectStorageServiceInfo(id, authUrl, domainId, domainName, password, project, projectId, region, userId, username);
  }

}

