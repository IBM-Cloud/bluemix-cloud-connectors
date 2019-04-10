/*
 * Copyright IBM Corp. 2019
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
package net.bluemix.connectors.core.info;

import java.util.List;

import org.springframework.cloud.service.BaseServiceInfo;

public class DatabasesForMongoDBServiceInfo extends BaseServiceInfo {
    private byte[] certData;
    private List<String> uriString;

    public DatabasesForMongoDBServiceInfo(String id, byte[] certData, List<String> uriString) {
        super(id);
        this.certData = certData;
        this.uriString = uriString;
    }

    public byte[] getCertData() {
        return this.certData;
    }

    public List<String> getUris() {
        return this.uriString;
    }
}
