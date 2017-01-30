package com.news.subscription.service;

import com.news.subscription.model.News;
import com.news.subscription.mqtt.model.NewsMessage;
import com.news.subscription.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public News save(NewsMessage newsMessage) {

        News news = News.from(newsMessage);

       return newsRepository.save(news);
    }
}
