package org.terrence.testapp.rest;

import java.util.List;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

  // return VCAP_SERVICES
  @GetMapping("/vcap")
  public String runDebug() {
    return System.getenv("VCAP_SERVICES");
  }

  private Cloud getCloud() {
    try {
      CloudFactory cloudFactory = new CloudFactory();
      return cloudFactory.getCloud();
    } catch (CloudException ce) {
      return null;
    }
  }

  // return Service Info
  @GetMapping("/infos")
  public String getInfos() {
    Cloud cloud = getCloud();
    List<ServiceInfo> infos = cloud.getServiceInfos();
    String result = "Info:\n";
    for (ServiceInfo info : infos) {
      result += info.getClass().toString() + "\n";
    }
    return result;
  }
}