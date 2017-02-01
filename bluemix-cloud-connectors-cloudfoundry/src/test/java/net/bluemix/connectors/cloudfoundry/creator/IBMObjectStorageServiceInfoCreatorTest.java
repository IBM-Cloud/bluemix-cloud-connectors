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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.bluemix.connectors.core.info.IBMGraphDbServiceInfo;
import net.bluemix.connectors.core.info.IBMObjectStorageServiceInfo;

public class IBMObjectStorageServiceInfoCreatorTest {

    private IBMObjectStorageServiceInfoCreator creator;

    @Before
    public void setUp() throws Exception {
        this.creator = new IBMObjectStorageServiceInfoCreator();
    }

    @After
    public void tearDown() throws Exception {
        this.creator = null;
    }

    @Test
    public void testAcceptMapOfStringObject() {
        Map<String, Object> emptyProps = new HashMap<String, Object>();
        Map<String, Object> stringProps = new HashMap<String, Object>();
        stringProps.put("label", "Object-Storage");
        Map<String, Object> intProps = new HashMap<String, Object>();
        intProps.put("label", 123);
        assertFalse(creator.accept(emptyProps));
        assertFalse(creator.accept(intProps));
        assertTrue(creator.accept(stringProps));
    }

    @Test
    public void testCreateServiceInfo() {
        Map<String, Object> empty = new HashMap<String, Object>();
        Map<String, Object> badTypes = new HashMap<String, Object>();
        badTypes.put("name", 1);
        Map<String, Object> badCredTypes = new HashMap<String, Object>();
        badCredTypes.put("username", 1);
        badCredTypes.put("password", 1);
        badCredTypes.put("auth_url", 1);
        badCredTypes.put("domainName", 1);
        badCredTypes.put("domainId", 1);
        badCredTypes.put("project", 1);
        badCredTypes.put("projectId", 1);
        badCredTypes.put("region", 1);
        badCredTypes.put("userId", 1);
        badTypes.put("credentials", badCredTypes);
        Map<String, Object> rawInfo = new HashMap<String, Object>();
        rawInfo.put("name", "id");
        Map<String, Object> rawInfoCreds = new HashMap<String, Object>();
        rawInfoCreds.put("username", "username");
        rawInfoCreds.put("password", "password");
        rawInfoCreds.put("auth_url", "authUrl");
        rawInfoCreds.put("domainName", "domainName");
        rawInfoCreds.put("domainId", "domainId");
        rawInfoCreds.put("project", "project");
        rawInfoCreds.put("projectId", "projectId");
        rawInfoCreds.put("region", "region");
        rawInfoCreds.put("userId", "userId");
        rawInfo.put("credentials", rawInfoCreds);
        IBMObjectStorageServiceInfo nullServiceInfo = new IBMObjectStorageServiceInfo(null, null, null, null, null, null, null, null, null, null);
        IBMObjectStorageServiceInfo serviceInfo = new IBMObjectStorageServiceInfo("id", "authUrl", "domainId", "domainName", "password", "project",
                "projectId", "region", "userId", "username");
        assertEquals(nullServiceInfo, creator.createServiceInfo(empty));
        assertEquals(nullServiceInfo, creator.createServiceInfo(badTypes));
        assertEquals(serviceInfo, creator.createServiceInfo(rawInfo));
    }

}
