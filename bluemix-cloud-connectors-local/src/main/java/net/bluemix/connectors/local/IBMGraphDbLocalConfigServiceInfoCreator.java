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

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.cloud.localconfig.LocalConfigServiceInfoCreator;

import net.bluemix.connectors.core.info.IBMGraphDbServiceInfo;

/**
 * Creates {@link IBMGraphDbServiceInfo} based on a local configuration.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 *
 */
public class IBMGraphDbLocalConfigServiceInfoCreator extends LocalConfigServiceInfoCreator<IBMGraphDbServiceInfo> {

    private static final Logger LOG = Logger.getLogger(IBMGraphDbLocalConfigServiceInfoCreator.class.getName());

    /**
     * Constructor.
     */
    public IBMGraphDbLocalConfigServiceInfoCreator() {
        super(IBMGraphDbServiceInfo.SCHEME);
    }

    @Override
    public IBMGraphDbServiceInfo createServiceInfo(String id, String uri) {
        try {
            return new IBMGraphDbServiceInfo(id, uri);
        } catch (URISyntaxException e) {
            LOG.logp(Level.WARNING, IBMGraphDbLocalConfigServiceInfoCreator.class.getName(),
                    "createServiceInfo", "Invalid URI: " + uri, e);
            return null;
        }
    }
}
