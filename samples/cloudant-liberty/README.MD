# Liberty Cloudant Connector Sample

## About
This is a sample demonstrating how to use the Cloudant connector in a Java EE application running on IBM's Websphere Liberty Profile.
The sample application stores status messages in a Cloudant DB.

## Prereqs
In order to run or deploy this sample application you need to agree to the Liberty Profile
license agreement.  You can do this by reading the [License](http://public.dhe.ibm.com/ibmdl/export/pub/software/websphere/wasdev/downloads/wlp/beta/lafiles/en.html) and creating an environment variable named `LIBERTY_LICENSE` with the D/N value in the license agreement.  You will need to complete this step before running the Maven commands to run the application locally or deploying the application to Bluemix.

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

After you have created your `spring-cloud.properties` file run `$ mvn -P run`.  This will download a Liberty Profile deploy the application and start the server.  Once the server is started navigate to [http://localhost:9080](http://localhost:9080).

## Running In Eclipse
You can also run the sample in Eclipse.  You will need to install the [Websphere Developer Tools for Eclipse](https://developer.ibm.com/wasdev/downloads/liberty-profile-using-eclipse/) first.  Once they are installed and you setup you Liberty server in Eclipse (see step 3 in the previous link) you will need to add the application to the server in Eclipse.  In addition you will need to add the following features to your server.xml.

```
    <feature>jaxrs-1.1</feature>
    <feature>localConnector-1.0</feature>
    <feature>cdi-1.0</feature>
    <feature>servlet-3.1</feature>
```  

## Deploying To Bluemix
Run the following Maven profile

```
$ mvn -P deploy -Dcf.username=myusername -Dcf.password=mypassword -Dorg=bluemixOrg -Dspace=bluemixSpace
```

Replace the properties with your Bluemix username and password as well as your organization name and space.