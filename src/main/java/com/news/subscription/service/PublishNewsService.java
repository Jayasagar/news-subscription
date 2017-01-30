package com.news.subscription.service;

import com.news.subscription.dto.NewsDto;
import com.news.subscription.mqtt.model.NewsMessage;
import org.springframework.messaging.handler.annotation.SendTo;

public class PublishNewsService {

    @SendTo("/topic/news")
    public NewsDto news(NewsMessage newsMessage) throws Exception {
        return NewsDto.from(newsMessage);
    }
}
