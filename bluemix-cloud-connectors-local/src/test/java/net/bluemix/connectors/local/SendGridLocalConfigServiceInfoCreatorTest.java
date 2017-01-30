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
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.service.common.SmtpServiceInfo;

public class SendGridLocalConfigServiceInfoCreatorTest {

    private SendGridLocalConfigServiceInfoCreator creator;

    @Before
    public void setUp() throws Exception {
        this.creator = new SendGridLocalConfigServiceInfoCreator();
    }

    @After
    public void tearDown() throws Exception {
        this.creator = null;
    }

    @Test
    public void testCreateServiceInfo() {
        SmtpServiceInfo info = creator.createServiceInfo("sendgrid", "smtp://user:password@mymail.com");
        assertEquals("mymail.com", info.getHost());
        assertEquals("sendgrid", info.getId());
        assertEquals("password", info.getPassword());
        assertEquals("user", info.getUserName());
        assertEquals(-1, info.getPort());
        assertNull(info.getPath());
        assertNull(info.getQuery());
        assertEquals("smtp", info.getScheme());
        assertEquals("smtp://user:password@mymail.com", info.getUri());
    }

}
