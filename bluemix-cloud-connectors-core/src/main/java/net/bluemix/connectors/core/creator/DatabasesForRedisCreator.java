/*
 * Copyright IBM Corp. 2019
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

import static org.springframework.cloud.service.Util.hasClass;

import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.PooledServiceConnectorConfig;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.cloud.service.ServiceConnectorCreationException;
import org.springframework.cloud.service.keyval.RedisConnectionFactoryConfig;
import org.springframework.cloud.service.keyval.RedisJedisClientConfigurer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisSslClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import net.bluemix.connectors.core.info.DatabasesForRedisServiceInfo;
import net.bluemix.connectors.core.ssl.StringBasedSSLSocketFactory;
import net.bluemix.connectors.core.ssl.StringBasedTrustManager;

public class DatabasesForRedisCreator
        extends AbstractServiceConnectorCreator<RedisConnectionFactory, DatabasesForRedisServiceInfo> {

    private static final Logger LOG = Logger.getLogger(DatabasesForRedisCreator.class.getName());
    private static final String JEDIS_CLASS_NAME = "redis.clients.jedis.Jedis";

    @Override
    public RedisConnectionFactory create(DatabasesForRedisServiceInfo serviceInfo,
            ServiceConnectorConfig serviceConnectorConfig) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        configuration.setHostName(serviceInfo.getHost());
        configuration.setPort(Integer.valueOf(serviceInfo.getPort()));
        configuration.setPassword(RedisPassword.of(serviceInfo.getPassword()));

        if (hasClass(JEDIS_CLASS_NAME)) {
            JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();

            RedisJedisClientConfigurer clientConfigurer = new RedisJedisClientConfigurer();
            if (serviceConnectorConfig instanceof RedisConnectionFactoryConfig) {
                clientConfigurer.configure(builder, (RedisConnectionFactoryConfig) serviceConnectorConfig);
            } else {
                clientConfigurer.configure(builder, (PooledServiceConnectorConfig) serviceConnectorConfig);
            }

            if (connectionIsSecure(serviceInfo)) {
                try {
                    JedisSslClientConfigurationBuilder jedissl = builder.useSsl();
                    byte[] cert = serviceInfo.getCertData();
                    if (cert != null) {
                        try {
                            StringBasedTrustManager.getTrustManager().addCert(cert);
                        } catch (Exception e) {
                            LOG.log(Level.SEVERE, "Unable to add certificate to trust manager", e);
                        }
                    }
                    jedissl.sslSocketFactory(new StringBasedSSLSocketFactory(""));
                } catch (GeneralSecurityException e) {
                    throw new ServiceConnectorCreationException(e);
                }
            }

            JedisConnectionFactory connectionFactory = new JedisConnectionFactory(configuration, builder.build());
            connectionFactory.afterPropertiesSet();
            return connectionFactory;
        } else {
            throw new ServiceConnectorCreationException(String.format(
                    "Failed to create cloud Redis "
                            + "connection factory for %s service. No client implementation classes "
                            + " of Jedis clients implementation (%s) not found",
                    serviceInfo.getId(), JEDIS_CLASS_NAME));
        }
    }

    private boolean connectionIsSecure(DatabasesForRedisServiceInfo serviceInfo) {
        return "rediss".equals(serviceInfo.getScheme());
    }
}