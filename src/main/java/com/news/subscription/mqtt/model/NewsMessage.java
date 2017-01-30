package com.news.subscription.mqtt.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewsMessage {
    private String topic;
    private String title;
    private String city;
}
