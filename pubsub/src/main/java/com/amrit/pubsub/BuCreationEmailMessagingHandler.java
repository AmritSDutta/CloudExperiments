package com.amrit.pubsub;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@EnableBinding({ EmailRequestChannels.class })
@Component

public class BuCreationEmailMessagingHandler {
    private static Logger LOG = LoggerFactory.getLogger(BuCreationEmailMessagingHandler.class);

     MessageChannel buCreationEmailWrite;

    @Autowired(required=true)
    public BuCreationEmailMessagingHandler( @Qualifier("Output") MessageChannel buCreationEmailWrite) {
        this.buCreationEmailWrite=buCreationEmailWrite;
    }

    @StreamListener(value = "buCreationEmailInput")
    public void handle(EmailRequest emailRequest) throws IOException {
        Instant start = Instant.now();
        LOG.debug("Received [" + emailRequest.toString() + "] event: ");
        throw new RuntimeException("Test");
    }

    public void raiseRequestForEmail(EmailRequest emailRequest) {
        buCreationEmailWrite.send(MessageBuilder.withPayload(emailRequest).build());
    }
}
