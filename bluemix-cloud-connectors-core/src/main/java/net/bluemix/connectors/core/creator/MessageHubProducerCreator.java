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

import com.google.common.base.Joiner;
import java.util.HashMap;
import java.util.Map;
import static javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag.REQUIRED;
import net.bluemix.connectors.core.info.MessageHubServiceInfo;
import net.bluemix.connectors.core.security.MemoryConfiguration;
import net.bluemix.connectors.core.service.MessageHubFactoryConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

/**
 * Class which creates a {@link org.apache.kafka.clients.producer.KafkaProducer}
 * object using the service credentials for MessageHub.
 *
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public class MessageHubProducerCreator
        extends AbstractServiceConnectorCreator<KafkaProducer, MessageHubServiceInfo> {

    @Override
    public KafkaProducer create(
            final MessageHubServiceInfo serviceInfo,
            final ServiceConnectorConfig serviceConnectorConfig) {
        // Producer Configuration
        final Map<String, Object> producerConfiguration = new HashMap<>();
        producerConfiguration.put("bootstrap.servers", Joiner.on(',').join(serviceInfo.getBrokers()));
        producerConfiguration.put("security.protocol", "SASL_SSL");
        producerConfiguration.put("sasl.mechanism", "PLAIN");
        producerConfiguration.put("ssl.protocol", "TLSv1.2");
        producerConfiguration.put("ssl.enabled.protocols", "TLSv1.2");
        producerConfiguration.put("ssl.truststore.location", System.getProperty("java.home") + "/lib/security/cacerts");
        producerConfiguration.put("ssl.truststore.password", "changeit");
        producerConfiguration.put("ssl.truststore.type", "jks");

        if (serviceConnectorConfig != null && serviceConnectorConfig instanceof MessageHubFactoryConfig) {
            producerConfiguration.putAll((MessageHubFactoryConfig) serviceConnectorConfig);
        }

        // JAAS Login Module Configuration
        final Map<String, Object> loginModuleConfiguration = new HashMap<>();
        loginModuleConfiguration.put("serviceName", "kafka");
        loginModuleConfiguration.put("username", serviceInfo.getUsername());
        loginModuleConfiguration.put("password", serviceInfo.getPassword());
        MemoryConfiguration.use().addConfigurationEntry("KafkaClient",
                "kafka", REQUIRED, loginModuleConfiguration
        );

        return new KafkaProducer(producerConfiguration);
    }

}
