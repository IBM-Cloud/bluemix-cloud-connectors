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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import net.bluemix.connectors.core.info.CloudantServiceInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CloudantServiceInfoCreatorTest {
  
  private CloudantServiceInfoCreator creator;

  @Before
  public void setUp() throws Exception {
    creator = new CloudantServiceInfoCreator();
  }

  @After
  public void tearDown() throws Exception {
    creator = null;
  }

  @Test
  public void testAcceptMapOfStringObject() {
    Map<String, Object> emptyProps = new HashMap<String, Object>();
    Map<String, Object> stringProps = new HashMap<String, Object>();
    stringProps.put("label", "cloudantNoSQLDB");
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
    badCredTypes.put("host", 1);
    badCredTypes.put("url", 1);
    badCredTypes.put("port", "1");
    badTypes.put("credentials", badCredTypes);
    Map<String, Object> rawInfo = new HashMap<String, Object>();
    rawInfo.put("name", "id");
    Map<String, Object> rawInfoCreds = new HashMap<String, Object>();
    rawInfoCreds.put("username", "username");
    rawInfoCreds.put("password", "password");
    rawInfoCreds.put("host", "host");
    rawInfoCreds.put("url", "url");
    rawInfoCreds.put("port", 443);
    rawInfo.put("credentials", rawInfoCreds);
    CloudantServiceInfo nullServiceInfo = new CloudantServiceInfo(null, null, null, null, 0, null);
    CloudantServiceInfo serviceInfo = new CloudantServiceInfo("id", "username", "password", "host", 443, "url");
    assertEquals(nullServiceInfo, creator.createServiceInfo(empty));
    assertEquals(nullServiceInfo, creator.createServiceInfo(badTypes));
    assertEquals(serviceInfo, creator.createServiceInfo(rawInfo));
    
  }

}

