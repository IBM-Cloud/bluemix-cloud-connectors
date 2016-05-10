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
package net.bluemix.connectors.core.info;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IBMGraphDbServiceInfoTest {
  
  private IBMGraphDbServiceInfo serviceInfo;
  private IBMGraphDbServiceInfo urlServiceInfo;

  @Before
  public void setUp() throws Exception {
    this.serviceInfo = new IBMGraphDbServiceInfo("testId", "https://graphdb.com", "user", "password");
    this.urlServiceInfo = new IBMGraphDbServiceInfo("testId", "ibmgraphdb://user:password@graphdb.com");
  }

  @After
  public void tearDown() throws Exception {
    this.serviceInfo = null;
    this.urlServiceInfo = null;
  }

  @Test
  public void testGetApiUrl() {
    assertEquals("https://graphdb.com", serviceInfo.getApiUrl());
    assertEquals("https://graphdb.com", urlServiceInfo.getApiUrl());
  }

  @Test
  public void testGetUsername() {
    assertEquals("user", serviceInfo.getUsername());
    assertEquals("user", urlServiceInfo.getUsername());
  }

  @Test
  public void testGetPassword() {
    assertEquals("password", serviceInfo.getPassword());
    assertEquals("password", urlServiceInfo.getPassword());
  }

  @Test
  public void testEqualsObject() {
    assertFalse(serviceInfo.equals(null));
    assertFalse(serviceInfo.equals("test"));
    assertFalse(serviceInfo.equals(new IBMGraphDbServiceInfo("test", "https://graphdb.com", "user", "password")));
    assertTrue(serviceInfo.equals(new IBMGraphDbServiceInfo("testId", "https://graphdb.com", "user", "password")));
    assertTrue(serviceInfo.equals(serviceInfo));
    assertFalse(urlServiceInfo.equals(new IBMGraphDbServiceInfo("test", "https://graphdb.com", "user", "password")));
    assertTrue(urlServiceInfo.equals(new IBMGraphDbServiceInfo("testId", "https://graphdb.com", "user", "password")));
    assertTrue(urlServiceInfo.equals(serviceInfo));
  }

}

