package com.news.subscription.mqtt;

import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// TODO: Get the URIs and other required properties from the System Env/File
// TODO: SSL Properties
// TODO: Connection timeout, session, async settings
@Component
public class BridgeMqttClientFactory {

    private DefaultMqttPahoClientFactory mqttPahoClientFactory;

    @PostConstruct
    public void initializeMqttClient() {
        mqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        mqttPahoClientFactory.setServerURIs("tcp://iot.eclipse.org:1883");

    }
    public MqttPahoClientFactory get() {
        return mqttPahoClientFactory;
    }
}
