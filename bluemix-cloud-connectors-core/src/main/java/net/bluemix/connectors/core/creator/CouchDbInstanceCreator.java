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

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.bluemix.connectors.core.info.CloudantServiceInfo;

import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

/**
 * Handles creating a CouchDBInstance from the CloudantServiceInfo object.
 * @author ryanjbaxter
 *
 */
public class CouchDbInstanceCreator extends AbstractServiceConnectorCreator<CouchDbInstance, CloudantServiceInfo> {
  
  private static final Logger LOG = Logger.getLogger(CouchDbInstanceCreator.class.getName());

  @Override
  public CouchDbInstance create(CloudantServiceInfo serviceInfo,
          ServiceConnectorConfig serviceConnectorConfig) {
    HttpClient httpClient;
    try {
      httpClient = new StdHttpClient.Builder()
      .url(serviceInfo.getUrl())
      .build();
      return new StdCouchDbInstance(httpClient);
    } catch (MalformedURLException e) {
      LOG.logp(Level.WARNING, CouchDbInstanceCreator.class.getName(), "create", "Error parsing URL", e);
      return null;
    }
  }
}

