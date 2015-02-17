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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SendGridServiceInfoCreatorTest {

  private SendGridServiceInfoCreator creator;
  
  @Before
  public void setUp() throws Exception {
    this.creator = new SendGridServiceInfoCreator();
  }

  @After
  public void tearDown() throws Exception {
    this.creator = null;
  }

  @Test
  public void testAccept() {
    Map<String, Object> data = new HashMap<String, Object>();
    assertFalse(creator.accept(data));
    data.put("label", "foo");
    assertFalse(creator.accept(data));
    data.put("label", "sendgrid");
    assertTrue(creator.accept(data));
  }

}

