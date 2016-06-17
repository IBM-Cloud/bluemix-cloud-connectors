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

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class StatusRepository {

  @Autowired
  private Database db;

  public void add(Status status) {
    Response response = db.post(status);
    status.setId(response.getId());
  }

  public Status get(String id) {
    return db.find(Status.class, id);
  }

  public void remove(String id, String revision) {
    db.remove(id, revision);
  }

  public void update(Status update) {
    db.update(update);
  }

  public List<Status> getAll() {
    try {
      return db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Status.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

