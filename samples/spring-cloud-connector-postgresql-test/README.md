## Test project for Spring Cloud Connectors with PostgreSQL on IBM Cloud
This project is a quick test to verify the behavior of the Spring Cloud Connector for PostgreSQL on IBM Cloud, as provided by https://github.com/IBM-Cloud/bluemix-cloud-connectors. This project provides a simple REST endpoint that will verify that a bound PostgreSQL service is detected appropriately, and be used by the application, by performing a quick read and write to the database.

## Setup
You'll need ibm cloud CLI from https://cloud.ibm.com/docs/cli/reference/ibmcloud?topic=cloud-cli-install-ibmcloud-cli#install_use and bx cf installed and configured to talk to the appropriate cf org/spac etc.

## Build
```
./mvnw package
```

## Deploy
```
bx cf push -b java_buildpack -p target/testapp-0.0.1-SNAPSHOT.jar YourAppname
```

## Verify
1. navigate to http://YourAppname.mybluemix.net/test
2. check log: 
```
bx cf logs YourAppname --recent
```
