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
package net.bluemix.connectors.core.info;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CloudantServiceInfoTest {
  
  private CloudantServiceInfo serviceInfo;

  @Before
  public void setUp() throws Exception {
    serviceInfo = new CloudantServiceInfo("testId", "username", "password", "username.cloudant.com", 443, 
            "https://username:password@username.cloudant.com");
  }

  @After
  public void tearDown() throws Exception {
    serviceInfo = null;
  }

  @Test
  public void testGetUsername() {
    assertEquals("username", serviceInfo.getUsername());
  }

  @Test
  public void testGetPassword() {
    assertEquals("password", serviceInfo.getPassword());
  }

  @Test
  public void testGetHost() {
    assertEquals("username.cloudant.com", serviceInfo.getHost());
  }

  @Test
  public void testGetPort() {
    assertEquals(443, serviceInfo.getPort());
  }

  @Test
  public void testGetUrl() {
    assertEquals("https://username:password@username.cloudant.com", serviceInfo.getUrl());
  }
  
  @Test
  public void testEquals() {
    assertFalse(serviceInfo.equals(null));
    assertFalse(serviceInfo.equals("test"));
    assertFalse(serviceInfo.equals(new CloudantServiceInfo(null, null, null, null, 0, null)));
    assertTrue(serviceInfo.equals(new CloudantServiceInfo("testId", "username", "password", "username.cloudant.com", 443, 
            "https://username:password@username.cloudant.com")));
  }

}

