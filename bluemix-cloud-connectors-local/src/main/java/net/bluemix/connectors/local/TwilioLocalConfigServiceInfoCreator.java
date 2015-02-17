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
package net.bluemix.connectors.local;

import net.bluemix.connectors.core.info.TwilioServiceInfo;

import org.springframework.cloud.localconfig.LocalConfigServiceInfoCreator;

/**
 * Local configuration for creating Twilio service info when running locally.
 * @author ryanjbaxter
 *
 */
public class TwilioLocalConfigServiceInfoCreator extends LocalConfigServiceInfoCreator<TwilioServiceInfo> {

  public TwilioLocalConfigServiceInfoCreator() {
    //Fake scheme to identify the service, its only for local configuration anyways so not a big deal
    super("twilio");
  }

  @Override
  public TwilioServiceInfo createServiceInfo(String id, String uri) {
    return new TwilioServiceInfo(id, uri.replace("twilio", "https"));
  }

}

