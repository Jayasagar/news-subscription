package com.news.subscription.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.news.subscription.mqtt.model.NewsMessage;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @Entity
@EqualsAndHashCode(of = {"id"})
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String topic;
    private String title;
    private String city;
    private LocalDateTime time;

    public static News from(NewsMessage newsMessage) {
        News news = new News();
        news.setTopic(newsMessage.getTopic());
        news.setTitle(newsMessage.getTitle());
        news.setCity(newsMessage.getCity());
        return news;
    }
}
