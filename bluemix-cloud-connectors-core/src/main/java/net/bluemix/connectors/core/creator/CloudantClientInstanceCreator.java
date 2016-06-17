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

import net.bluemix.connectors.core.info.CloudantServiceInfo;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles creating a CloudantClient from the CloudantServiceInfo object.
 * @author dantwining
 *
 */
public class CloudantClientInstanceCreator extends AbstractServiceConnectorCreator<CloudantClient, CloudantServiceInfo> {
  
  private static final Logger LOG = Logger.getLogger(CloudantClientInstanceCreator.class.getName());

  @Override
  public CloudantClient create(CloudantServiceInfo serviceInfo,
          ServiceConnectorConfig serviceConnectorConfig) {
    try {

      URL cloudantUrl = new URL(serviceInfo.getUrl());

      return ClientBuilder.url(cloudantUrl)
              .username(serviceInfo.getUsername())
              .password(serviceInfo.getPassword())
              .build();

    } catch (MalformedURLException e) {
      LOG.logp(Level.WARNING, CloudantClientInstanceCreator.class.getName(), "create", "Error parsing URL", e);
      return null;
    }
  }
}

