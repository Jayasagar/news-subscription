package com.news.subscription.mqtt.handler;

import org.springframework.context.annotation.Scope;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Scope("prototype")
public class GenericMessageHandler<T> implements MessageHandler {

    private Consumer<T> messageConsumer;

    public void setMessageConsumer(Consumer<T> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        T messageObj = (T) message.getPayload();
        String topic = (String)message.getHeaders().get(MqttHeaders.TOPIC);

        messageConsumer.accept(messageObj);
    }

}
