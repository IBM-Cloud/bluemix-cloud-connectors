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

import org.springframework.cloud.localconfig.LocalConfigServiceInfoCreator;
import org.springframework.cloud.service.common.SmtpServiceInfo;

/**
 * Creates an SmtpServiceInfo object when the cloud application is running
 * locally. The URI must begin with smtp.
 *
 * @author Ryan J. Baxter <rbaxter@apache.org>
 *
 */
public class SendGridLocalConfigServiceInfoCreator extends LocalConfigServiceInfoCreator<SmtpServiceInfo> {

    /**
     * Constructor.
     */
    public SendGridLocalConfigServiceInfoCreator() {
        super(SmtpServiceInfo.SMTP_SCHEME);
    }

    @Override
    public SmtpServiceInfo createServiceInfo(String id, String uri) {
        return new SmtpServiceInfo(id, uri);
    }

}
