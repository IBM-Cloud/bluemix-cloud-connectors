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

import net.bluemix.connectors.core.info.TwilioServiceInfo;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

/**
 * Creates new TwilioServieInfo when Twilio is bound as a service to the application.
 * @author ryanjbaxter
 *
 */
public class TwilioServiceInfoCreator extends CloudFoundryServiceInfoCreator<TwilioServiceInfo> {
  
  private static final String TWILIO_API_URI = "https://api.twilio.com";
  
  /**
   * Constructor.
   */
  public TwilioServiceInfoCreator() {
    super(new Tags(), "https");
  }

  @Override
  public boolean accept(Map<String, Object> serviceData) {
    Object credObject = serviceData.get("credentials");
    if(credObject instanceof Map<?, ?>) {
      Map<String, Object> credentials = (Map<String, Object>)credObject;
      Object urlObj = credentials.get("url");
      if(urlObj instanceof String) {
        return TWILIO_API_URI.equals((String)urlObj);
      }
    }
    return false;
  }

  @Override
  public TwilioServiceInfo createServiceInfo(Map<String, Object> serviceData) {
    String accountId = null;
    String authToken = null;
    String id = null;
    Object credObject = serviceData.get("credentials");
    Object idObj = serviceData.get("name");
    if(idObj instanceof String) { id = (String)idObj; }
    if(credObject instanceof Map<?, ?>) {
      Map<String, Object> credentials = (Map<String, Object>)credObject;
      Object accoutIdObj = credentials.get("accountSID");
      Object authTokenObj = credentials.get("authToken");
      if(accoutIdObj instanceof String) { accountId = (String)accoutIdObj; }
      if(authTokenObj instanceof String) { authToken = (String)authTokenObj; }
    }
    return new TwilioServiceInfo(id, accountId, authToken);
  }
}

