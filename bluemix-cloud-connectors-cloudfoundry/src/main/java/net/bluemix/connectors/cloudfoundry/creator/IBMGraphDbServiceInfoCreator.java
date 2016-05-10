/*
 * Copyright IBM Corp. 2014
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

import net.bluemix.connectors.core.info.IBMGraphDbServiceInfo;

/**
 * Creates a new {@link IBMGraphDbServiceInfo}
 * @author ryanjbaxter
 *
 */
public class IBMGraphDbServiceInfoCreator extends CloudFoundryServiceInfoCreator<IBMGraphDbServiceInfo> {

  private static final String LABEL = "IBM Graph";

  /**
   * Constructor
   */
  public IBMGraphDbServiceInfoCreator() {
    super(new Tags(), "");
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
  public IBMGraphDbServiceInfo createServiceInfo(Map<String, Object> serviceData) {
    String id = null;
    String username = null;
    String password = null;
    String apiUrl = null;
    Object credObject = serviceData.get("credentials");
    Object idObj = serviceData.get("name");
    if(idObj instanceof String) { id = (String)idObj; }
    if(credObject instanceof Map<?, ?>) {
      Map<String, Object> credentials = (Map<String, Object>)credObject;
      Object usernameObj = credentials.get("username");
      Object passwordObj = credentials.get("password");
      Object apiUrlObj = credentials.get("apiURL");
      if(usernameObj instanceof String) { username = (String)usernameObj; }
      if(passwordObj instanceof String) { password = (String)passwordObj; }
      if(apiUrlObj instanceof String) { apiUrl = (String)apiUrlObj; }
    }
    return new IBMGraphDbServiceInfo(id, apiUrl, username, password);
  }

}
