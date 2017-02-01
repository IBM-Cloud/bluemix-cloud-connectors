/*
 * Copyright IBM Corp. 2017
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

import net.bluemix.connectors.core.info.TwilioServiceInfo;

import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

import com.twilio.sdk.TwilioRestClient;

/**
 * Class which creates a {@link com.twilio.sdk.TwilioRestClient} object using
 * the service credentials for the Twilio Service service.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public class TwilioRestClientCreator
        extends AbstractServiceConnectorCreator<TwilioRestClient, TwilioServiceInfo> {

    @Override
    public TwilioRestClient create(TwilioServiceInfo serviceInfo,
            ServiceConnectorConfig serviceConnectorConfig) {
        return new TwilioRestClient(serviceInfo.getAccountId(), serviceInfo.getAuthToken());
    }

}
