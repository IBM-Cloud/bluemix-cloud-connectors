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
package net.bluemix.connectors.cloudant;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App implements EmbeddedServletContainerCustomizer {

  public static void main(String[] args) throws Exception {
      SpringApplication.run(App.class, args);
  }

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    //Enabled UTF-8 as the default character encoding for static HTML resources.
    //If you would like to disable this comment out the 3 lines below or change
    //the encoding to whatever you would like.
    MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
    mappings.add("html", "text/html;charset=utf-8");
    container.setMimeMappings(mappings );
  }

  @Bean
  public Database statusDatabase(CloudantClient client) {
    return client.database("status", true);
  }

}