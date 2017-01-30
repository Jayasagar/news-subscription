package com.news.subscription.mqtt.config;

import com.news.subscription.JSON;
import com.news.subscription.mqtt.BridgeMqttClientFactory;
import com.news.subscription.mqtt.convertor.JSONMqttMessageConvertor;
import com.news.subscription.mqtt.model.NewsMessage;
import com.news.subscription.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceActivatorConfig {

    @Autowired
    private BridgeMqttClientFactory mqttClientFactory;

    @Autowired
    private NewsService newsService;

    @Bean
    @ServiceActivator(inputChannel = "mqttExampleOutboundChannel")
    public MessageHandler mqttOutbound(){
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(UUID.randomUUID().toString(), mqttClientFactory.get());
        handler.setAsync(true);
        handler.setConverter(new JSONMqttMessageConvertor(NewsMessage.class));
        return handler;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttEventHandlerInputChannel")
    public MessageHandler handler() {

        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = (String)message.getHeaders().get(MqttHeaders.TOPIC);
                Object messagePayload = message.getPayload();

                NewsMessage event = JSON.MAPPER.toObject(messagePayload.toString().getBytes(), NewsMessage.class);
                // Two Actions:
                // Action 1: Save message to DB
                newsService.save(event);

                // Action 2: publish message to UI subscription end points/ Web socket client



                // Parse Topic and extract device id and event name
//                Map<Integer, String> topicTokens = TopicParse.getTopicTokens(topic);
//                if (topicTokens != null && !topicTokens.isEmpty()) {
//                    event.setDeviceId(topicTokens.get(1));
//                    event.setName(topicTokens.get(3));
//                }
            }
        };
    }
}
