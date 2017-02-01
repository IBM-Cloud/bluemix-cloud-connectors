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

import java.util.List;
import java.util.Objects;

import org.springframework.cloud.service.BaseServiceInfo;

/**
 * Represents the MessageHub VCAP_SERVICE info.
 *
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public class MessageHubServiceInfo extends BaseServiceInfo {

    /**
     * Scheme. This is not a real scheme but is needed for local config to work.
     */
    public static final String SCHEME = "kafka";

    private final List<String> brokers;
    private final String username;
    private final String password;

    /**
     * Constructor.
     *
     * @param id The service id.
     * @param brokers List of Kafka Brokers
     * @param username Username
     * @param password Password
     */
    public MessageHubServiceInfo(
            final String id, final List<String> brokers,
            final String username, final String password) {
        super(id);
        this.brokers = brokers;
        this.username = username;
        this.password = password;
    }

    /**
     * Get Broker List.
     *
     * @return List of Brokers.
     */
    @ServiceProperty
    public List<String> getBrokers() {
        return brokers;
    }

    /**
     * Get Username.
     *
     * @return Service Username.
     */
    @ServiceProperty
    public String getUsername() {
        return username;
    }

    /**
     * Get Password.
     *
     * @return Service Password.
     */
    @ServiceProperty
    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.brokers);
        hash = 73 * hash + Objects.hashCode(this.username);
        hash = 73 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MessageHubServiceInfo other = (MessageHubServiceInfo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return Objects.equals(this.brokers, other.brokers);
    }

    @Override
    public String toString() {
        return "MessagheHubServiceInfo"
                + '{'
                + "id=" + id + ", "
                + "brokers=" + brokers + ", "
                + "username=" + username + ", "
                + "password=" + password
                + '}';
    }

}
