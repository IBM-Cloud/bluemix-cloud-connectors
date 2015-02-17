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

import org.springframework.cloud.service.UriBasedServiceInfo;

/**
 * Represents service information for Twilio.
 * @author ryanjbaxter
 *
 */
public class TwilioServiceInfo extends UriBasedServiceInfo {
  
  private String accountId;
  private String authToken;

  /**
   * Constructor.
   * @param id The ID of the service.
   * @param accountId The account ID for the Twilio account.
   * @param authToken The auth token for the Twilio account.
   */
  public TwilioServiceInfo(String id, String accountId, String authToken) {
    super(id, "http", "api.twilio.com", 443, accountId, authToken, "");
    this.accountId = accountId;
    this.authToken = authToken;
  }
  
  /**
   * Constructor.
   * @param id The ID of the service
   * @param url The URL to Twilio.
   */
  public TwilioServiceInfo(String id, String url) {
    super(id, url);
    this.accountId = this.getUserName();
    this.authToken = this.getPassword();
  }

  /**
   * Gets the account ID.
   * @return The account ID.
   */
  @ServiceProperty
  public String getAccountId() {
    return accountId;
  }

  /**
   * Gets the auth token.
   * @return The auth token.
   */
  @ServiceProperty
  public String getAuthToken() {
    return authToken;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
    result = prime * result + ((authToken == null) ? 0 : authToken.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == null || !(obj instanceof TwilioServiceInfo)){
      return false;
    } else {
      TwilioServiceInfo test = (TwilioServiceInfo)obj;
      boolean result = false;
      result = test.getAccountId() != null ? test.getAccountId().equals(this.getAccountId()) : 
        test.getAccountId() == this.getAccountId();
      result &= test.getAuthToken() != null ? test.getAuthToken().equals(this.getAuthToken()) : 
        test.getAuthToken() == this.getAuthToken();
      result &= test.getId() != null ? test.getId().equals(this.getId()) : 
        test.getId() == this.getId();
      return result;
    }
  }

  @Override
  public String toString() {
    return "TwilioServiceInfo [accountId=" + accountId + ", authToken=" + authToken + ", id=" + getId() + "]";
  }
}

