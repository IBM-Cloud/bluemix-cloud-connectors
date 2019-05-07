package org.terrence.testapp.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.terrence.testapp.domain.Status;
import org.terrence.testapp.repositories.StatusRepository;

@RestController
public class TestRestController {

  @Autowired
  private StatusRepository repo;

  StringWriter sw = new StringWriter();
  PrintWriter pw = new PrintWriter(sw);

  // create test object with random id and fixed message
  String id = UUID.randomUUID().toString();
  Status test = new Status();
  String message = String.format("Message for the object: %s", id);

  // repo methods
  private Status create(Status status) {
    repo.add(status);
    return status;
  }

  private void delete(String id) {
    repo.remove(repo.get(id));
  }

  // test methods

  // create test object with random id and fixed message
  private void createTestObject() throws Exception {
    try {
      pw.println("Setting id for test object: '" + id + "'");
      test.setId(id);
      pw.println("Setting message for test object: '" + message + "'");
      test.setMsg(message);
    } catch (Exception e) {
      pw.println("FAIL: Unexpected error during create test object.");
      e.printStackTrace(pw);
      throw e;
    }
  }

  // verify there is nothing in the repo with the id and then create the test
  // object
  private void addTestObject() throws Exception {
    try {
      pw.println("Testing for existing id in the repo '" + id + "' and  deleting as required.");
      Status exist = repo.get(id); // this should throw org.ektorp.DocumentNotFoundException if nothing exists

      // if we get this far then something exists and we need to delte it and then add
      // the test object
      pw.println("Object already exists in the repo with id '" + id + "', deleting it.");
      delete(id);
      pw.println("Adding test object to the repo.");
      create(test);
    } catch (org.ektorp.DocumentNotFoundException e) {
      pw.println("Object with id '" + id + "' does not exist in the repo, adding test object to the repo.");
      create(test);
    } catch (Exception e) {
      pw.println("FAIL: Problem during add of object...");
      e.printStackTrace(pw);
      throw e;
    }
  }

  // validate the the test object is in the database with the correct id and
  // message
  private void validateTestObject() throws Exception {
    try {
      // retrieve check object from the repo with the same id as the test object
      Status check = repo.get(id);

      // validate the id and message of the check object equals the test object's
      pw.println("Validating object in the repo.");
      if (((check.getId() == null && test.getId() == null)
          || (check.getId() != null && check.getId().equals(test.getId())))
          && ((check.getMsg() == null && test.getMsg() == null)
              || (check.getMsg() != null && check.getMsg().equals(test.getMsg())))) {
        pw.println("PASS: Retrieved object in the repo matced the saved object.");
        pw.println("Deleting object in the repo.");
        delete(id);

      } else {
        pw.println("FAIL: Retrieved object in the repo did NOT match the saved object");
        pw.println("Deleting object in the repo.");
        delete(id);
      }
    } catch (Exception e) {
      pw.println("FAIL: Problem during retrieve of object...");
      e.printStackTrace(pw);
      throw e;
    }
  }

  // run the test
  @RequestMapping(value = "/test", produces = "text/plain")
  public String runTest() {
    try {
      pw.println("Beginning test...");
      createTestObject();
      addTestObject();
      validateTestObject();

    } catch (Exception e) {
      pw.println("Failure reported by previous method.");
    }
    pw.flush();
    return sw.toString();
  }
}