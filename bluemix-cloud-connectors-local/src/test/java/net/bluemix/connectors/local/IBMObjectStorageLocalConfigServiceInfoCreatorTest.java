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
package net.bluemix.connectors.local;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.bluemix.connectors.core.info.IBMObjectStorageServiceInfo;

public class IBMObjectStorageLocalConfigServiceInfoCreatorTest {
  
  private IBMObjectStorageLocalConfigServiceInfoCreator creator;

  @Before
  public void setUp() throws Exception {
    creator = new IBMObjectStorageLocalConfigServiceInfoCreator();
  }

  @After
  public void tearDown() throws Exception {
    creator = null;
  }

  @Test
  public void testCreateServiceInfoStringString() {
    IBMObjectStorageServiceInfo info = creator.createServiceInfo("id", "swiftobjectstorage://userId:password@identity.open.softlayer.com/project/domainName");
    assertEquals("https://identity.open.softlayer.com", info.getAuthUrl());
    assertEquals("userId", info.getUserId());
    assertEquals("password", info.getPassword());
    assertEquals("project", info.getProject());
    assertEquals("domainName", info.getDomainName());
    assertEquals("id",info.getId());
    assertNull(info.getDomainId());
    assertNull(info.getProjectId());
    assertNull(info.getRegion());
    assertNull(info.getUsername());
    info = creator.createServiceInfo("id", "swiftobjectstorage://identity.open.softlayer.com");
    assertEquals("https://identity.open.softlayer.com", info.getAuthUrl());
    assertNull(info.getUserId());
    assertNull(info.getPassword());
    assertNull(info.getProject());
    assertNull(info.getDomainName());
    assertEquals("id",info.getId());
    assertNull(info.getDomainId());
    assertNull(info.getProjectId());
    assertNull(info.getRegion());
    assertNull(info.getUsername());
  }

}

