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
package net.bluemix.connectors.local;

import static org.junit.Assert.assertEquals;
import net.bluemix.connectors.core.info.CloudantServiceInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CouchDbLocalConfigServiceInfoCreatorTest {
  
  private CouchDbLocalConfigServiceInfoCreator creator;

  @Before
  public void setUp() throws Exception {
    creator = new CouchDbLocalConfigServiceInfoCreator();
  }

  @After
  public void tearDown() throws Exception {
    creator = null;
  }

  @Test
  public void testCreateServiceInfoStringString() {
    CloudantServiceInfo info = creator.createServiceInfo("cloudant", "couchdb://user:password@user.cloudant.com");
    assertEquals(new CloudantServiceInfo("cloudant", "user", "password", "user.cloudant.com", -1, "http://user:password@user.cloudant.com"), info);
    info = creator.createServiceInfo("cloudant", "couchdb://localhost:5984");
    assertEquals(new CloudantServiceInfo("cloudant", null, null, "localhost", 5984, "http://localhost:5984"), info);
    info = creator.createServiceInfo("cloudant", "couchdb://couchdb:1234");
    assertEquals(new CloudantServiceInfo("cloudant", null, null, "couchdb", 1234, "http://couchdb:1234"), info);
  }

}

