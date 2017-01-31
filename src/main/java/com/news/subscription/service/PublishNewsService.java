package com.news.subscription.service;

import com.news.subscription.dto.NewsDto;
import com.news.subscription.mqtt.model.NewsMessage;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class PublishNewsService {

    @SendTo("/topic/news")
    public NewsDto news(NewsMessage newsMessage){
        return NewsDto.from(newsMessage);
    }
}
