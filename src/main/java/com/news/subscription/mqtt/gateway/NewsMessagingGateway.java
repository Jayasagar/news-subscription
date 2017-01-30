package com.news.subscription.mqtt.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

/**
 * Spring Integration provides the GatewayProxyFactoryBean that generates a proxy for any interface and internally invokes the gateway methods shown below.
 * Using dependency injection you can then expose the interface to your business methods.
 * @param <T>
 */
@MessagingGateway(name="newsGateway", defaultRequestChannel = "mqttExampleOutboundChannel")
public interface NewsMessagingGateway<T> {

    @Gateway(requestChannel = "mqttExampleOutboundChannel")
    void processHeartBeatMessage(Message<T> message);

    @Gateway(requestChannel = "mqttExampleOutboundChannel")
    void processHeartBeat(T news);

    @Gateway(requestChannel = "mqttExampleOutboundChannel")
    void processHeartBeatStringPayload(Message<String> message);
}
