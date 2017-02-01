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
package net.bluemix.connectors.core.creator;

import org.openstack4j.api.client.IOSClientBuilder.V3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

import net.bluemix.connectors.core.info.IBMObjectStorageServiceInfo;

/**
 * Class which creates a {@link org.openstack4j.api.client.IOSClientBuilder.V3}
 * object using the service credentials for the IBM Object Storage service.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 *
 */
public class V3InstanceCreator extends AbstractServiceConnectorCreator<V3, IBMObjectStorageServiceInfo> {

    @Override
    public V3 create(IBMObjectStorageServiceInfo serviceInfo,
            ServiceConnectorConfig serviceConnectorConfig) {
        final Identifier domainIdent = Identifier.byName(serviceInfo.getDomainName());
        final Identifier projectIdent = Identifier.byName(serviceInfo.getProject());
        return OSFactory.builderV3().endpoint(serviceInfo.getAuthUrl())
                .credentials(serviceInfo.getUserId(), serviceInfo.getPassword())
                .scopeToProject(projectIdent, domainIdent);
    }

}
