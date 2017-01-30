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

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.List;
import java.util.Objects;

import org.springframework.cloud.service.BaseServiceInfo;

/**
 * Represents the Compose for MongoDB VCAP_SERVICE info.
 *
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 *
 */
public class ComposeForMongoDBServiceInfo extends BaseServiceInfo {

    /**
     * Scheme. This is not a real scheme but is needed for local config to work.
     */
    public static final String SCHEME = "mongodb";

    private final List<ServerAddress> addresses;
    private final List<MongoCredential> credentials;
    private final MongoClientOptions options;

    /**
     * Constructor.
     *
     * @param id The service id.
     * @param addresses The list of Server Addresses.
     * @param credentials The List of Mongo Credentials.
     * @param options MongoClient Options.
     */
    public ComposeForMongoDBServiceInfo(
            final String id,
            final List<ServerAddress> addresses,
            final List<MongoCredential> credentials,
            final MongoClientOptions options) {
        super(id);
        this.addresses = addresses;
        this.credentials = credentials;
        this.options = options;
    }

    @ServiceProperty
    public List<ServerAddress> getAddresses() {
        return addresses;
    }

    @ServiceProperty
    public List<MongoCredential> getCredentials() {
        return credentials;
    }

    @ServiceProperty
    public MongoClientOptions getOptions() {
        return options;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.addresses);
        hash = 53 * hash + Objects.hashCode(this.credentials);
        hash = 53 * hash + Objects.hashCode(this.options);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComposeForMongoDBServiceInfo other = (ComposeForMongoDBServiceInfo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.addresses, other.addresses)) {
            return false;
        }
        if (!Objects.equals(this.credentials, other.credentials)) {
            return false;
        }
        return Objects.equals(this.options, other.options);
    }

    @Override
    public String toString() {
        return "ComposeForMongoDBServiceInfo"
                + '{'
                + "id=" + id + ", "
                + "addresses=" + addresses + ", "
                + "credentials=" + credentials + ", "
                + "options=" + options
                + '}';
    }

}
