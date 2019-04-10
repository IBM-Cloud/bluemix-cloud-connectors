/*
 * Copyright IBM Corp. 2019
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

import java.util.Base64;
import java.util.List;
import java.util.Map;

import net.bluemix.connectors.core.info.DatabasesForRedisServiceInfo;

public class DatabasesForRedisServiceInfoCreator
        extends DatabasesForCloudServiceInfoCreator<DatabasesForRedisServiceInfo> {

    public DatabasesForRedisServiceInfoCreator() {
        super("databases-for-redis", "rediss");
    }

    @Override
    public DatabasesForRedisServiceInfo createServiceInfo(Map<String, Object> serviceData) {
        String id = getId(serviceData);

        Map<String, Object> credentials = getCredentials(serviceData);
        List<String> uri = getUrisFromCredentials(credentials);

        String cert64 = getRootCaFromCredentials(credentials);

        return new DatabasesForRedisServiceInfo(id, Base64.getDecoder().decode(cert64),
                getHostFromCredentials(credentials), getPortFromCredentials(credentials),
                getPasswordFromCredentials(credentials), getSchemeFromCredentials(credentials));
    }
}