## Debug project for Spring Cloud Connectors on IBM Cloud
This project is a set of debug methods that check VCAP_SERVICES and ServiceInfo of the Spring Cloud Connector on IBM Cloud, as provided by https://github.com/IBM-Cloud/bluemix-cloud-connectors. This project provides a simple REST endpoint that will verify that a bound service is detected appropriately, and be used by the application.

## Setup
You'll need ibm cloud CLI from https://cloud.ibm.com/docs/cli/reference/ibmcloud?topic=cloud-cli-install-ibmcloud-cli#install_use and bx cf installed and configured to talk to the appropriate cf org/spac etc.

## Build
```
./mvnw clean package
```

## Deploy
```
bx cf push -b java_buildpack -p target/testapp-0.0.1-SNAPSHOT.jar YourAppname
```

## Verify
1. navigate to http://YourAppname.mybluemix.net/vcap or /infos
2. check log: 
```
bx cf logs YourAppname --recent
```
