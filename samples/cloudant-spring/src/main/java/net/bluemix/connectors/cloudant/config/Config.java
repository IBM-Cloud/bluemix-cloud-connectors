/*
 * Copyright IBM Corp. 2014
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
package net.bluemix.connectors.cloudant.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ektorp.CouchDbInstance;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class Config {
  
  @Configuration
  //@Profile("cloud")
  @ServiceScan
  static class CloudConfiguration extends AbstractCloudConfig {
    // If you don't want to rely on @ServiceScan finding bound services and creating
    // the right beans you can uncomment the methods below.
    
    @Bean
    public CouchDbInstance couchDbInstance() throws NamingException {
      CouchDbInstance instance = connectionFactory().service(CouchDbInstance.class);
      return instance;
//      return (CouchDbInstance) new InitialContext().lookup("java:comp/env/couchdb/status-db");
    }
    
  }
}