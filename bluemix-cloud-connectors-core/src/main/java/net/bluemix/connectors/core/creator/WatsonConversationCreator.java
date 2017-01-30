/*
 * Copyright IBM Corp. 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bluemix.connectors.core.creator;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import net.bluemix.connectors.core.info.WatsonConversationServiceInfo;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

/**
 * Class which creates a
 * {@link com.ibm.watson.developer_cloud.conversation.v1.ConversationService}
 * object using the service credentials for the Watson Conversation service.
 *
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public class WatsonConversationCreator
        extends AbstractServiceConnectorCreator<ConversationService, WatsonConversationServiceInfo> {

    @Override
    public ConversationService create(
            final WatsonConversationServiceInfo serviceInfo,
            final ServiceConnectorConfig serviceConnectorConfig) {
        final ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_09_20);
        service.setEndPoint(serviceInfo.getUrl());
        service.setUsernameAndPassword(serviceInfo.getUsername(), serviceInfo.getPassword());
        return service;
    }

}
