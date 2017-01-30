package com.news.subscription.mqtt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelConfiguration {

    @Bean
    public MessageChannel mqttEventHandlerInputChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public MessageChannel mqttExampleOutboundChannel() {
        DirectChannel directChannel = new DirectChannel();
        //directChannel.setMessageConverter(new JSONMqttMessageConvertor());
        return directChannel;
    }

    @Bean
    public MessageChannel mqttFileTransferChannel() {
        return new DirectChannel();
    }
}
