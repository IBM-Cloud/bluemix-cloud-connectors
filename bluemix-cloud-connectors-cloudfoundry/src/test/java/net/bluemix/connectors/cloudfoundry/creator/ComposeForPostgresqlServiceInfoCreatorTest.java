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
package net.bluemix.connectors.cloudfoundry.creator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.service.common.PostgresqlServiceInfo;

public class ComposeForPostgresqlServiceInfoCreatorTest {

    private ComposeForPostgresqlServiceInfoCreator creator;

    @Before
    public void setUp() throws Exception {
        creator = new ComposeForPostgresqlServiceInfoCreator();
    }

    @After
    public void tearDown() throws Exception {
        creator = null;
    }

    @Test
    public void testAcceptMapOfStringObject() {
        final Map<String, Object> emptyProps = new HashMap<>();
        final Map<String, Object> stringProps = new HashMap<>();
        final Map<String, Object> intProps = new HashMap<>();
        stringProps.put("label", "compose-for-postgresql");
        intProps.put("label", 123);
        assertFalse(creator.accept(emptyProps));
        assertFalse(creator.accept(intProps));
        assertTrue(creator.accept(stringProps));
    }

    @Test
    public void testCreateServiceInfo() throws NullPointerException {
        final String test1_id = "id";
        final String test1_uri = "postgresql://username:password@hostname:1/database";

        final Map<String, Object> rawInfo = new HashMap<>();
        final Map<String, Object> rawInfoCreds = new HashMap<>();
        rawInfo.put("name", test1_id);
        rawInfo.put("credentials", rawInfoCreds);
        rawInfoCreds.put("uri", test1_uri);

        final PostgresqlServiceInfo serviceInfo = new PostgresqlServiceInfo(
                test1_id,
                test1_uri
        );

        //assertEquals(serviceInfo, creator.createServiceInfo(rawInfo));        
    }

}
