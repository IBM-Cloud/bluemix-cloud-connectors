## Test project for Spring Cloud Connectors with Twilio on IBM Cloud
This project is a quick test to verify the behavior of the Spring Cloud Connector for Twilio on IBM Cloud, as provided by https://github.com/IBM-Cloud/bluemix-cloud-connectors. This project provides a simple REST endpoint that will verify that a bound Twilio service is detected appropriately, and be used by the application, by sending an SMS message

## Setup
1. You will need ibm cloud CLI from https://cloud.ibm.com/docs/cli/reference/ibmcloud?topic=cloud-cli-install-ibmcloud-cli#install_use and bx cf installed and configured to talk to the appropriate cf org/space etc.
2. You will need to create a Twilio account and phone number and have another phone number capable of accepting SMS.
3. You will need to edit TestRestController.java and use the Twilio phone number for "From" and the other phone number for "To"

## Build
```
./mvnw clean package
```

## Deploy
```
bx cf push -b java_buildpack -p target/testapp-0.0.1-SNAPSHOT.jar YourAppname
```

## Verify
1. navigate to http://YourAppname.mybluemix.net/test
2. SMS message should be sent/received
3. check log: 
```
bx cf logs YourAppname --recent
```
