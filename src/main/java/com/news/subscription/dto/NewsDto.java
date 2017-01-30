package com.news.subscription.dto;

import com.news.subscription.mqtt.model.NewsMessage;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class NewsDto {
    private String topic;
    private String title;

    public static NewsDto from(NewsMessage newsMessage) {
        NewsDto newsDto = new NewsDto();
        newsDto.setTopic(newsMessage.getTopic());
        newsDto.setTitle(newsMessage.getTitle());

        return newsDto;
    }
}
