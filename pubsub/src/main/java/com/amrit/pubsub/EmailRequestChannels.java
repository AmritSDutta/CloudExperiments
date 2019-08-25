package com.amrit.pubsub;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EmailRequestChannels {

    @Input("buCreationEmailInput")
    SubscribableChannel buCreationEmailRead();

    @Qualifier("Output")
    @Output("buCreationEmailOutput")
    MessageChannel buCreationEmailWrite();
}
