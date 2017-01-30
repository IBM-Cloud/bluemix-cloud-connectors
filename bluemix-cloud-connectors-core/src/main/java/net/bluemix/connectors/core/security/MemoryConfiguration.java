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
package net.bluemix.connectors.core.security;

import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;
import javax.security.auth.login.Configuration;

/**
 * Memory Based JAAS Configuration.
 *
 * @author Hans W. Uhlig <hans.uhlig@ibm.com>
 */
public class MemoryConfiguration extends Configuration {

    private static MemoryConfiguration INSTANCE;

    public static MemoryConfiguration get() {
        if (INSTANCE == null) {
            INSTANCE = new MemoryConfiguration();
        }
        return INSTANCE;
    }

    public static MemoryConfiguration use() {
        Configuration.setConfiguration(get());
        return get();
    }

    private final Map<String, AppConfigurationEntry[]> configurations;

    private MemoryConfiguration() {
        this.configurations = new HashMap<>();
    }

    public MemoryConfiguration addConfigurationEntry(final String name, final AppConfigurationEntry entry) {
        AppConfigurationEntry[] oldEntryArray;
        if ((oldEntryArray = configurations.get(name)) == null) {
            oldEntryArray = new AppConfigurationEntry[0];
        }
        AppConfigurationEntry[] newEntryArray
                = new AppConfigurationEntry[oldEntryArray.length + 1];
        System.arraycopy(oldEntryArray, 0, newEntryArray, 0, oldEntryArray.length);
        newEntryArray[oldEntryArray.length] = entry;
        return this;
    }

    public MemoryConfiguration addConfigurationEntry(
            final String name,
            final String loginModuleName,
            final LoginModuleControlFlag loginModuleControlFlag,
            final Map<String, ?> loginModuleConfiguration) {
        return addConfigurationEntry(name,
                new AppConfigurationEntry(
                        loginModuleName,
                        loginModuleControlFlag,
                        loginModuleConfiguration
                )
        );
    }

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(final String name) {
        return configurations.containsKey(name)
                ? configurations.get(name)
                : new AppConfigurationEntry[0];
    }
}
