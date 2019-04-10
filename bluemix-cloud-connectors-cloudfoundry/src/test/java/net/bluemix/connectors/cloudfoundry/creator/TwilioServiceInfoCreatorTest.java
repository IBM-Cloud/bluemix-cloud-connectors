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

import net.bluemix.connectors.core.info.TwilioServiceInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwilioServiceInfoCreatorTest {

    private TwilioServiceInfoCreator creator;

    @Before
    public void setUp() throws Exception {
        this.creator = new TwilioServiceInfoCreator();
    }

    @After
    public void tearDown() throws Exception {
        this.creator = null;
    }

    @Test
    public void testAcceptMapOfStringObject() {
        Map<String, Object> serviceData = new HashMap<String, Object>();
        Map<String, String> credData = new HashMap<String, String>();
        credData.put("twilio_api_base_url", "http://api.twilio.com/");
        serviceData.put("credentials", credData);
        assertFalse(creator.accept(serviceData));
        credData.put("twilio_api_base_url", "https://api.twilio.com/");
        assertTrue(creator.accept(serviceData));
        credData.remove("twilio_api_base_url");
        assertFalse(creator.accept(serviceData));
    }

    @Test
    public void testCreateServiceInfo() {
        Map<String, Object> empty = new HashMap<String, Object>();
        Map<String, Object> badTypes = new HashMap<String, Object>();
        badTypes.put("name", 1);
        Map<String, Object> badCredTypes = new HashMap<String, Object>();
        badCredTypes.put("twilio_account_sid", 1);
        badCredTypes.put("twilio_auth_token", 1);
        badTypes.put("credentials", badCredTypes);
        Map<String, Object> rawInfo = new HashMap<String, Object>();
        rawInfo.put("name", "id");
        Map<String, Object> rawInfoCreds = new HashMap<String, Object>();
        rawInfoCreds.put("twilio_account_sid", "abc");
        rawInfoCreds.put("twilio_auth_token", "123");
        rawInfo.put("credentials", rawInfoCreds);
        TwilioServiceInfo nullServiceInfo = new TwilioServiceInfo(null, null, null);
        TwilioServiceInfo serviceInfo = new TwilioServiceInfo("id", "abc", "123");
        assertEquals(nullServiceInfo, creator.createServiceInfo(empty));
        assertEquals(nullServiceInfo, creator.createServiceInfo(badTypes));
        assertEquals(serviceInfo, creator.createServiceInfo(rawInfo));
    }
}
