package com.news.subscription.mqtt.config;

import com.news.subscription.mqtt.BridgeMqttClientFactory;
import com.news.subscription.mqtt.convertor.JSONMqttMessageConvertor;
import com.news.subscription.mqtt.model.NewsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InboundProducerConfig {

    @Autowired
    private BridgeMqttClientFactory mqttPahoClientFactory;

    @Autowired
    private MessageChannel mqttEventHandlerInputChannel;

    @Bean
    public MessageProducer inboundMessageHandler() {
        // Default Topic is mandatory
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(UUID.randomUUID().toString(), mqttPahoClientFactory.get(), "/newsMessage/notify");
        adapter.setCompletionTimeout(5000);
       adapter.setConverter(new JSONMqttMessageConvertor(NewsMessage.class));
        adapter.setQos(1);
        adapter.setOutputChannel(mqttEventHandlerInputChannel);
        return adapter;
    }
}
