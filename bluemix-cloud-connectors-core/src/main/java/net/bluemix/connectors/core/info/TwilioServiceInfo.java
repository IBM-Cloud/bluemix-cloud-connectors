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
import org.springframework.cloud.service.UriBasedServiceInfo;

/**
 * Represents service information for Twilio.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public final class TwilioServiceInfo extends UriBasedServiceInfo {

    /**
     * Scheme. This is not a real scheme but is needed for local config to work.
     */
    public static final String SCHEME = "twilio";

    private final String accountId;
    private final String authToken;

    /**
     * Constructor.
     *
     * @param id The ID of the service.
     * @param accountId The account ID for the Twilio account.
     * @param authToken The auth token for the Twilio account.
     */
    public TwilioServiceInfo(final String id, final String accountId, final String authToken) {
        super(id, "http", "api.twilio.com", 443, accountId, authToken, "");
        this.accountId = accountId;
        this.authToken = authToken;
    }

    /**
     * Constructor.
     *
     * @param id The ID of the service
     * @param url The URL to Twilio.
     */
    public TwilioServiceInfo(final String id, final String url) {
        super(id, url);
        this.accountId = this.getUserName();
        this.authToken = this.getPassword();
    }

    /**
     * Gets the account ID.
     *
     * @return The account ID.
     */
    @ServiceProperty
    public String getAccountId() {
        return accountId;
    }

    /**
     * Gets the auth token.
     *
     * @return The auth token.
     */
    @ServiceProperty
    public String getAuthToken() {
        return authToken;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.accountId);
        hash = 29 * hash + Objects.hashCode(this.authToken);
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
        final TwilioServiceInfo other = (TwilioServiceInfo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.accountId, other.accountId)) {
            return false;
        }
        if (!Objects.equals(this.authToken, other.authToken)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TwilioServiceInfo"
                + '{'
                + "id=" + id + ", "
                + "accountId=" + accountId + ", "
                + "authToken=" + authToken
                + '}';
    }

}
