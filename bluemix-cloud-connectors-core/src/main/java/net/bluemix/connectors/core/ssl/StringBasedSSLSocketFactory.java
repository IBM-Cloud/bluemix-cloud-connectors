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

package net.bluemix.connectors.core.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class StringBasedSSLSocketFactory extends SSLSocketFactory {
  private static final Logger LOG = Logger.getLogger(StringBasedSSLSocketFactory.class.getName());
  protected SSLSocketFactory factory;

  public Socket createSocket(InetAddress host, int port) throws IOException {
    return factory.createSocket(host, port);
  }

  public Socket createSocket(String host, int port) throws IOException {
    return factory.createSocket(host, port);
  }

  public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
    return factory.createSocket(host, port, localHost, localPort);
  }

  public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
      throws IOException {
    return factory.createSocket(address, port, localAddress, localPort);
  }

  public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
    return factory.createSocket(socket, host, port, autoClose);
  }

  public Socket createSocket() throws IOException {
    return factory.createSocket();
  }

  public String[] getDefaultCipherSuites() {
    return factory.getDefaultCipherSuites();
  }

  public String[] getSupportedCipherSuites() {
    return factory.getSupportedCipherSuites();
  }

  public StringBasedSSLSocketFactory(String arg) throws GeneralSecurityException {
    SSLContext ctx = SSLContext.getInstance("TLS");
    try {
      StringBasedTrustManager tm = StringBasedTrustManager.getTrustManager();
      ctx.init(null, new TrustManager[] { tm }, null);
    } catch (IOException e) {
      LOG.log(Level.SEVERE,"Failed to obtain trustmanager",e);
    }
    factory = ctx.getSocketFactory();
  }

}