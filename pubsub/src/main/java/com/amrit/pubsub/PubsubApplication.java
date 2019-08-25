package com.amrit.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class PubsubApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    BuCreationEmailMessagingHandler buCreationEmailMessagingHandler;

    public static void main(String[] args) {
        SpringApplication.run(PubsubApplication.class, args);
    }


    @PutMapping("/send")
    public ResponseEntity<String> postMessage(@RequestBody String message) {
        LOG.debug("hi");
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<String> getMessage() {
        LOG.debug("hi");

        EmailRequest emailRequest = new EmailRequest();
        List<String> toEmail = new ArrayList<>();
        toEmail.add("amrit.dambul@gmail.com");
        emailRequest.setEmailReceiver(toEmail);

        String emailCC = "amrit.dambul@gmail.com";

        List<String> ccEmail = new ArrayList<>();
        ccEmail.add(emailCC);
        emailRequest.setEmailCcReceiver(ccEmail);
        emailRequest.setSenderEmail("amrit.dambul@gmail.com");

        emailRequest.setSendGridTemplateId("123");

        Map<String, String> dynamicTemplateData = new LinkedHashMap<>();
        /*dynamicTemplateData.put(TVCEmailing.TVC_SENDGRID_TEMPLATE_UNAME_KEY, userInfoRequest.getUserName());
        dynamicTemplateData.put(TVCEmailing.TVC_SENDGRID_TEMPLATE_PASS_KEY, userInfoRequest.getPassword());
        dynamicTemplateData.put(TVCEmailing.TVC_SENDGRID_TEMPLATE_SENDER_EMAIL_KEY, buCreationFromMail);
        dynamicTemplateData.put(TVCEmailing.TVC_SENDGRID_TEMPLATE_BU_URL_KEY, buApiUrl);*/
        emailRequest.setDynamicTemplateData(dynamicTemplateData);
        buCreationEmailMessagingHandler.raiseRequestForEmail(emailRequest);
        return new ResponseEntity<String>("Up", HttpStatus.OK);
    }


}

/*
spring:
  cloud:
    stream:
      gcp:
        pubsub:
          bindings:
            buCreationEmail:
              consumer:
                auto-create-resouces: true
            events:
              producer:
                auto-create-resources: true

      bindings:
        buCreationEmail:
          destination: sandbox_buCreationEmail
          group: ConsumergroupTenantService
          content-type: application/json
          binder: pubsub
          consumer:
            concurrency: 10

* */