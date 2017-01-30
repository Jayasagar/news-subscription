package com.news.subscription.mqtt.convertor;

import com.news.subscription.JSON;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;

public class JSONMqttMessageConvertor extends DefaultPahoMessageConverter {

    private Class classType;

    public JSONMqttMessageConvertor(Class classType) {
        this.classType = classType;
    }

    @Override
    protected byte[] messageToMqttBytes(Message<?> message) {
        return JSON.MAPPER.toBytes(message.getPayload());
    }

    @Override
    protected Object mqttBytesToPayload(MqttMessage mqttMessage) throws Exception {
        return JSON.MAPPER.toObject(mqttMessage.getPayload(), classType);
    }
}
