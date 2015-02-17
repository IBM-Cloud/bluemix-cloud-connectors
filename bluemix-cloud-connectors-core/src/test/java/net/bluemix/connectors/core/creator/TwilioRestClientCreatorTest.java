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
package net.bluemix.connectors.core.creator;

import static org.junit.Assert.assertTrue;
import net.bluemix.connectors.core.info.TwilioServiceInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.twilio.sdk.TwilioRestClient;

public class TwilioRestClientCreatorTest {
  
  private TwilioRestClientCreator creator;

  @Before
  public void setUp() throws Exception {
    this.creator = new TwilioRestClientCreator();
  }

  @After
  public void tearDown() throws Exception {
    this.creator = null;
  }

  @Test
  public void testCreate() {
    TwilioServiceInfo info = new TwilioServiceInfo("twilio", "AC12345678ac12345678ac123456781234", "1234511c2209d62fab4766fa75435f1e");
    assertTrue(this.creator.create(info, null) instanceof TwilioRestClient);
  }

}

