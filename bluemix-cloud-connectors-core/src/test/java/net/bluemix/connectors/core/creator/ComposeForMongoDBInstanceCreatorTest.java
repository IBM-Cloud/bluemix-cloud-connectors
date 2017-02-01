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
package net.bluemix.connectors.core.creator;

import com.google.common.collect.ImmutableList;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.List;
import static org.junit.Assert.assertTrue;
import net.bluemix.connectors.core.info.ComposeForMongoDBServiceInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.service.ServiceConnectorConfig;

public class ComposeForMongoDBInstanceCreatorTest {

    private ComposeForMongoDBCreator creator;

    @Before
    public void setUp() throws Exception {
        creator = new ComposeForMongoDBCreator();
    }

    @After
    public void tearDown() throws Exception {
        creator = null;
    }

    @Test
    public void testCreate() {
        final String test1_hostname = "example.com";
        final String test1_username = "username";
        final String test1_password = "password";
        final String test1_database = "database";

        final List<ServerAddress> servers = ImmutableList.of(
                new ServerAddress(test1_hostname)
        );
        final List<MongoCredential> credentials = ImmutableList.of(
                MongoCredential.createCredential(test1_username, test1_database, test1_password.toCharArray())
        );
        final MongoClientOptions options = new MongoClientOptions.Builder().build();

        final ComposeForMongoDBServiceInfo serviceInfo
                = new ComposeForMongoDBServiceInfo("id", servers, credentials, options);

        assertTrue(creator.create(serviceInfo, new ServiceConnectorConfig() {
        }) instanceof MongoClient);
    }

}
