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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IBMObjectStorageServiceInfoTest {
  
  private IBMObjectStorageServiceInfo serviceInfo;
  private IBMObjectStorageServiceInfo urlServiceInfo;
  private IBMObjectStorageServiceInfo badServiceInfo;
  
  @Before
  public void setUp() throws Exception {
    serviceInfo = new IBMObjectStorageServiceInfo("id", "authUrl", "domainId", "domainName", "password", "project", 
            "projectId", "region", "userId", "username");
    urlServiceInfo = new IBMObjectStorageServiceInfo("id", "swiftobjectstorage://userId:password@identity.open.softlayer.com/project/domainName");
    badServiceInfo = new IBMObjectStorageServiceInfo("id", "swiftobjectstorage://identity.open.softlayer.com");
  }

  @After
  public void tearDown() throws Exception {
    this.serviceInfo = null;
    this.urlServiceInfo = null;
    this.badServiceInfo = null;
  }

  @Test
  public void testGetAuthUrl() {
    assertEquals("authUrl", serviceInfo.getAuthUrl());
    assertEquals("https://identity.open.softlayer.com", urlServiceInfo.getAuthUrl());
    assertEquals("https://identity.open.softlayer.com", badServiceInfo.getAuthUrl());
  }

  @Test
  public void testGetDomainId() {
    assertEquals("domainId", serviceInfo.getDomainId());
    assertNull(urlServiceInfo.getDomainId());
    assertNull(badServiceInfo.getDomainId());
  }

  @Test
  public void testGetDomainName() {
    assertEquals("domainName", serviceInfo.getDomainName());
    assertEquals("domainName", urlServiceInfo.getDomainName());
    assertNull(badServiceInfo.getDomainName());
  }

  @Test
  public void testGetProject() {
    assertEquals("project", serviceInfo.getProject());
    assertEquals("project", urlServiceInfo.getProject());
    assertNull(badServiceInfo.getProject());
  }

  @Test
  public void testGetProjectId() {
    assertEquals("projectId", serviceInfo.getProjectId());
    assertNull(urlServiceInfo.getProjectId());
    assertNull(badServiceInfo.getProjectId());
  }

  @Test
  public void testGetRegion() {
    assertEquals("region", serviceInfo.getRegion());
    assertNull(urlServiceInfo.getRegion());
    assertNull(badServiceInfo.getRegion());
  }

  @Test
  public void testGetUserId() {
    assertEquals("userId", serviceInfo.getUserId());
    assertEquals("userId", urlServiceInfo.getUserId());
    assertNull(badServiceInfo.getUserId());
  }

  @Test
  public void testGetUsername() {
    assertEquals("username", serviceInfo.getUsername());
    assertNull(urlServiceInfo.getUsername());
    assertNull(badServiceInfo.getUsername());
  }
  
  @Test
  public void testGetPassword() {
    assertEquals("password", serviceInfo.getPassword());
    assertEquals("password", urlServiceInfo.getPassword());
    assertNull(badServiceInfo.getPassword());
  }

  @Test
  public void testEqualsObject() {
    assertTrue(new IBMObjectStorageServiceInfo("id", "authUrl", "domainId", "domainName", "password", "project", 
            "projectId", "region", "userId", "username").equals(serviceInfo));
    assertTrue(new IBMObjectStorageServiceInfo("id", "https://identity.open.softlayer.com", null, "domainName", "password", "project", 
            null, null, "userId", null).equals(urlServiceInfo));
    assertFalse(serviceInfo.equals(null));
    assertFalse(serviceInfo.equals("test"));
    assertFalse(serviceInfo.equals(new IBMObjectStorageServiceInfo("id1", "authUrl", "domainId", "domainName", "password", "project", 
            "projectId", "region", "userId", "username")));
  }

}

