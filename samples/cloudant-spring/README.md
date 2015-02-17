# Spring Cloudant Connector Sample

## About
This is a sample demonstrating how to use the Cloudant connector in a Spring Boot application.
The sample application stores status messages in a Cloudant DB.

## Running Locally
You can run this application locally by either pointing to Cloudant in the cloud or running CouchDB locally.

Create a file called `spring-cloud.properties` in the directory `${user.home}/.config/cloudant-connector-sample`.

In the `spring-cloud.properties` file add the following properties

```
spring.cloud.appId: cloudant-sample
spring.cloud.connectors-sample: couchdb://user:password50@localhost:5984
```

You can change the `spring.cloud.connectors-sample` property to point to your own Cloudant account or local Couch DB server.
Normally CouchDB/Cloudant URLs use the HTTP protocol, however the Bluemix Cloud Connectors project uses the protocal (in this case couchdb) to identify the connector to use for the service.  Essentially you
can take your CouchDB/Cloudant URL and replace the http protooal with couchdb and set that as the value
of `spring.cloud.connectors-sample`.

## Running With Maven
After you have created your `spring-cloud.properties` file run `$ mvn -P run`.
Once the server is started navigate to [http://localhost:8080](http://localhost:8080).

## Running In Eclipse
Right click on `App.java` and select Run As -> Java Application.
Once the server is started navigate to [http://localhost:8080](http://localhost:8080).


## Deploying To Bluemix
Run the following Maven profile
```
mvn -P deploy -Dusername=bluemixUsername -Dpassword=bluemixPassword -Dorg=bluemixOrg -Dspace=bluemixSpace
```

Replace the properties with your Bluemix username and password as well as your organization name and space.