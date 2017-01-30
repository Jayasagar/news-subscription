package com.news.subscription.controller;

import com.news.subscription.dto.NewsDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.SendTo;

@RestController
public class PublishNewsController {
    @SendTo("/topic/news")
    public NewsDto news() throws Exception {
        // TODO : Move to Service class
        return new NewsDto();
    }
}
