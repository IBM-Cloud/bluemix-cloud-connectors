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

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.bluemix.connectors.core.info.IBMGraphDbServiceInfo;

public class IBMGraphDbLocalConfigServiceInfoCreatorTest {

    private IBMGraphDbLocalConfigServiceInfoCreator creator;

    @Before
    public void setUp() throws Exception {
        this.creator = new IBMGraphDbLocalConfigServiceInfoCreator();
    }

    @After
    public void tearDown() throws Exception {
        this.creator = null;
    }

    @Test
    public void testCreateServiceInfoStringString() throws URISyntaxException {
        IBMGraphDbServiceInfo info = creator.createServiceInfo("id", "ibmgraphdb://user:password@graphdb.com");
        assertEquals(new IBMGraphDbServiceInfo("id", "https://graphdb.com", "user", "password"), info);
        info = creator.createServiceInfo("id", "ibmgraphdb://ibmgraphdb.com");
        assertEquals(new IBMGraphDbServiceInfo("id", "https://ibmgraphdb.com", null, null), info);
    }

}
