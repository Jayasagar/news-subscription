package com.news.subscription.mqtt.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class NewsMessageHandler<T> implements MessageHandler {

    // Example
    //@Autowired
    //private CIoTDeviceService deviceService;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();



    }
}
