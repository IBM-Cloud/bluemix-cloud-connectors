[![Build Status](https://travis-ci.org/IBM-Bluemix/bluemix-cloud-connectors.svg?branch=master)](https://travis-ci.org/IBM-Cloud/bluemix-cloud-connectors) [![Lifecyle](https://img.shields.io/osslifecycle/IBM-Cloud/bluemix-cloud-connectors.svg)]()

## About
This project is meant to simplify the way Java developers access services
when deploying apps to IBM Bluemix.  When leveraging a bound service in an app
deployed to Bluemix you need to do go through a number of steps regardless of the
service you bound.

1.  Access the value of the VCAP_SERVICES environment variable.
2.  Parse the returned String into some type of Java object you can work with.
3.  Extract the data for the service you are interested in.
4.  Extract the credentials for the service you are interested in.
5.  Use the services Java client libraries to interact with that service.

Since these steps are pretty much repeated multiple times across all Java apps it is a great
opportunity to create a library that takes care of writing this mundane code.  The
[Spring Cloud Connectors project](http://cloud.spring.io/spring-cloud-connectors/)
already does this for [some services](https://github.com/spring-cloud/spring-cloud-connectors/tree/master/spring-cloud-cloudfoundry-connector)
that are common across all Cloud Foundry deployments.  However there are many services in Bluemix
that are not part of this project.  This project builds upon the Spring Cloud Connectors
project and provides connectors for the services within Bluemix.

### Supported Services
In addition to the services supported by the Spring Cloud Connectors project the
Bluemix Cloud Connectors project supports the following services

* Cloudant - via the [Ektorp library](http://ektorp.org/)
* Twilio - via the [Twilio client library](https://www.twilio.com/docs/java/install)

## When To Use This Project
If you are using the Liberty Runtime in Bluemix you can take advantage of the
[auto-configuration](https://www.ng.bluemix.net/docs/#starters/liberty/index.html#automaticconfigurationofboundservices)
features which may do the same thing as this project so it doesn't make sense to use this
project in your app in that case.  However if you are not using the Liberty Runtime and you are using
another Java buildpack to run your application than this library might make sense.  Also if you want to
use the Liberty buildpack but want the application to manage the connection to the service than this might
also be a situation when you might want to use this library.  In addition when developing locally you can configure
connection to local or remote services via a properties file instead of configuring the server itself.

## Getting Started

### Getting The Dependencies

Add the following dependency to you Maven/Gradle project.

```
    <dependency>
      <groupId>net.bluemix</groupId>
      <artifactId>bluemix-cloud-connectors-cloudfoundry</artifactId>
      <version>0.0.1.RC6</version>
    </dependency>
    <dependency>
      <groupId>net.bluemix</groupId>
      <artifactId>bluemix-cloud-connectors-local</artifactId>
      <version>0.0.1.RC6</version>
    </dependency>
```

If you are building a Spring app you will also need to add the following Sping Cloud Connectors dependency.

```
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-spring-service-connector</artifactId>
      <version>1.2.2.RELEASE</version>
    </dependency>
```

Alternatively you can download the jars from [here](https://oss.sonatype.org/content/repositories/releases/net/bluemix/).

### Accessing The Service Credentials In A Non-Spring App
In a non-Spring app you can easily access the credentials of a service from a ServiceInfo class.
Here is how you would get a list of ServiceInfos for the services bound to your application.

```
CloudFactory cloudFactory = new CloudFactory();
Cloud cloud = cloudFactory.getCloud();
List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
```

You can also get a subset of ServiceInfo objects for a specific service type, for example
Cloudant.

```
List<ServiceInfo> databaseInfos = cloud.getServiceInfos(CouchDbInstance.class);
```

Alternatively you can also get a higher level Java object to work with for a specific service.

```
//The serviceId variable should be the name you gave to your service.
String serviceId = "cloudant-db";
CouchDbInstance couchDb = cloud.getServiceConnector(serviceId, CouchDbInstance.class, null /* default config */);
```

For more detailed information on how this works you should read the
[Spring Cloud Connectors documentation](https://github.com/spring-cloud/spring-cloud-connectors/tree/master/spring-cloud-core).

There is a sample JEE app using the Bluemix Cloud Connectors project in the samples/cloudant-liberty folder.

###  Accessing The Service Credentials In A Spring App
When you are using Spring you can easily create beans for services you have bound to your app in Bluemix.

```
public class Config {

  @Configuration
  static class CloudConfiguration extends AbstractCloudConfig {
    @Bean
    public CouchDbInstance couchDbInstance() {
      CouchDbInstance instance = connectionFactory().service(CouchDbInstance.class);
      return instance;
    }
  }
}
```

This bean can now be injected into other classes and used to access the service bound to your application.
For more detailed information see the
[Spring Cloud Connectors documentation](https://github.com/spring-cloud/spring-cloud-connectors/tree/master/spring-cloud-spring-service-connector).

There is a sample Spring app using the Bluemix Cloud Connectors project in the samples/cloudant-spring folder.

### When Running Locally
When building apps for Bluemix, you usually want to also run your application locally during
development.  Developers have come up with various ways of achieving this.  Some set a VCAP_SERVICES environment
variable on their development machine.  Others write code that tries to determine if the application is running
locally or in the cloud.  In Spring you can use something like Spring profiles to enable certain configuration beans
when running in the cloud and running locally.  

The Spring Cloud Connectors project has a simple way of allowing developers
to run their Bluemix apps in the cloud and locally without having to write any extra code.  You can read more about how this works
in the [Spring Cloud Connectors project](https://github.com/spring-cloud/spring-cloud-connectors/tree/3ec88aba9ed85f2b09d2cafb620ad1d4a28aaa9d/spring-cloud-localconfig-connector).

In short, create a file called `spring-cloud-bootstrap.properties` in the project and add it to the project classpath.
In that file create a property called `spring.cloud.propertiesFile`.  The value of the property should be a path to another
properties file which will contain the credentials to the service to use when running locally.  Here is a sample
`spring-cloud-bootstrap.properties` file.

    spring.cloud.propertiesFile: ${user.home}/.config/cloudant-connector-sample/spring-cloud.properties

The properties file containing the service credentials should contain 2 properties `spring.cloud.appId` and
`spring.cloud.{id}` where {id} is the service ID you are using for your service in the cloud.  The `spring.cloud.appId`
property should be a unique id for your app.  The `spring.cloud.{id}` should be a URL to your service including any credentials
needed to access the service. Here is a sample

    spring.cloud.appId: cloudant-sample
    spring.cloud.cloudant: couchdb://user:pass@localhost:5984

#### Cloudant/CouchDB

The Spring Cloud Connectors project assumes that the `spring.cloud.{id}` is a URL.  Unfortunately Cloudant/CouchDB operates over
HTTP so it is hard for the Bluemix Cloud Connectors project to know what connector to use.  For that reason you must use the `couchdb`
protocol (which is something we made up), for example `couchdb://user:pass@localhost:5984`.

#### Twilio

The Spring Cloud Connectors project assumes that the `spring.cloud.{id}` is a URL.  Unfortunately Twilio operates over
HTTP so it is hard for the Bluemix Cloud Connectors project to know what connector to use.  For that reason you must use the `twilio`
protocol (which is something we made up), for example `twilio://user:pass@localhost:5984`.

## Development

Please use the GitHub pull request model for developement.  In other words, fork this project and submit a pull request if you want to change anything.

### Builds

When your code is merged you should monitor the builds in [Travis CI](https://travis-ci.org/IBM-Bluemix/bluemix-cloud-connectors).

### Snapshot Builds

The Travis CI build will automatically deploy a snapshot build to the [Sonatype Snapshot Maven Resitory](https://oss.sonatype.org/content/repositories/snapshots).  To use the snapshots in your projects add the following repository to your Maven POM (use similar settings if you are using Gradgle)

```
  <repositories>
    <repository>
      <id>ossrh-snapshots</id>
      <name>OSSRH Snapshots</name>
      <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
  </repositories>
```

### Releases
Only authorized users can do releases.  To do a release follow the steps below.

```
$ mvn release:clean -P release
$ mvn release:prepare -P release
$ mvn release:perform -P release
```

This will create a tag in the GitHub repo for the release and also push all (signed) artifacts to the [Sonatype Releases Repo](https://oss.sonatype.org/content/repositories/releases/).  This repo will be synced with the Maven central repo once every ten minutes.  For more information see the [Sonatype documentation](http://central.sonatype.org/pages/ossrh-guide.html).

## License

This code is licensed under Apache v2.  See the LICENSE file in the root of
the repository.

## Dependencies

For a list of 3rd party dependencies that are used see the POM files of the individual projects.
