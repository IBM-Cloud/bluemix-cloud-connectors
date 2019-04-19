package org.terrence.testapp.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.terrence.testapp.domain.Person;
import org.terrence.testapp.repositories.PersonRepository;

@RestController
public class TestRestController {

  @Autowired
  private PersonRepository repo;

  StringWriter sw = new StringWriter();
  PrintWriter pw = new PrintWriter(sw);

  Person test = new Person("TestPerson", 33); // test person object with name and age
  String id = UUID.randomUUID().toString(); // use a random id

  // test methods

  // create test object with random id and fixed name and age
  private void createTestObject() throws Exception {
    try {
      pw.println("Name of the test object is: '" + test.getName() + "'");
      pw.println("Age of the test objec is: '" + test.getAge() + "'");
      pw.println("Random repo id of the test object is: '" + id + "'");
      pw.println("Setting id for test object: '" + id + "'");
      test.setId(id);
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
      // if there is an existing Person with the id then delete it
      repo.findById(id).ifPresent(p -> repo.deleteById(p.getId()));
      pw.println("Saving test object with id: '" + id + "'");
      repo.save(test);
    } catch (Exception e) {
      System.out.println("Exception caught: creating new object");
      pw.println("Saving test object with id: '" + id + "'");
      repo.save(test);
      throw e;
    }
  }

  // validate the the test object is in the database with the correct id and
  // message
  private void validateTestObject() throws Exception {
    try {
      // retrieve check object from the repo with the same id as the test object
      Person check = repo.findById(id).get();

      // validate the id and message of the check object equals the test object's
      pw.println("Validating object in the repo.");
      if (((check.getId() == null && test.getId() == null)
          || (check.getId() != null && check.getId().equals(test.getId())))
          && ((check.getName() == null && test.getName() == null)
              || (check.getName() != null && check.getName().equals(test.getName())))) {
        pw.println("PASS: Retrieved object in the repo matced the saved object.");
        pw.println("Deleting object in the repo.");
        repo.deleteById(id);
      } else {
        pw.println("FAIL: Retrieved object in the repo did NOT match the saved object");
        pw.println("Deleting object in the repo.");
        repo.deleteById(id);
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