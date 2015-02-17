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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwilioServiceInfoTest {

  private TwilioServiceInfo nullInfo;
  private TwilioServiceInfo info;
  private TwilioServiceInfo urlInfo;
  
  @Before
  public void setUp() throws Exception {
    this.nullInfo = new TwilioServiceInfo("twilio", null, null);
    this.info = new TwilioServiceInfo("twilio", "abc", "123");
    this.urlInfo = new TwilioServiceInfo("twilio", "https://abc:123@api.twilio.com");
  }

  @After
  public void tearDown() throws Exception {
    this.nullInfo = null;
    this.info = null;
    this.urlInfo = null;
  }
  
  @Test
  public void testGetAccountId() {
    assertNull(nullInfo.getAccountId());
    assertEquals("abc", info.getAccountId());
    assertEquals("abc", info.getAccountId());
  }
  
  @Test
  public void testGetAccountToken() {
    assertNull(nullInfo.getAuthToken());
    assertEquals("123", info.getAuthToken());
    assertEquals("123", info.getAuthToken());
  }
  
  @Test
  public void testEquals() {
    assertNotEquals(nullInfo, info);
    assertNotEquals(nullInfo, null);
    assertEquals(info, info);
    assertEquals(new TwilioServiceInfo("twilio", "abc", "123"), info);
    assertEquals(urlInfo, info);
  }
  
  @Test
  public void testToString() {
    assertEquals("TwilioServiceInfo [accountId=abc, authToken=123, id=twilio]", info.toString());
  }
}

