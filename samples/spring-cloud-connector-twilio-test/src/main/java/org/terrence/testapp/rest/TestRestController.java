package org.terrence.testapp.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

  @Autowired
  private TwilioRestClient twilioRestClient;

  StringWriter sw = new StringWriter();
  PrintWriter pw = new PrintWriter(sw);

  @RequestMapping(value = "/test", produces = "text/plain")

  // test by sending an SMS message
  public String runTest() {
    try {
      pw.println("Beginning test...");
      pw.println("Retrieving acount info.");
      Account account = twilioRestClient.getAccount();
      pw.println("Account is: '" + account + "'");
      List<NameValuePair> params = new ArrayList<NameValuePair>();
      pw.println("Setting To, From, and Body for the message.");
      params.add(new BasicNameValuePair("To", "15555555555")); // phone number to receive the sms
      params.add(new BasicNameValuePair("From", "+15555555555")); // twilio account phone number
      params.add(new BasicNameValuePair("Body", "Twilio Test")); // message to send
      pw.println("Setting up MessageFactory.");
      MessageFactory messageFactory = twilioRestClient.getAccount().getMessageFactory();
      pw.println("Passing message to messageFactory");
      Message message = messageFactory.create(params);
      pw.println("Sending SMS message with Sid: '" + message.getSid() + "'");
      pw.println("PASS: Message sent");

    } catch (Exception e) {
      pw.println("FAIL: Unexpected error during test.");
      e.printStackTrace();
    }
    pw.flush();
    return sw.toString();
  }
}