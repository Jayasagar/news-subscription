package com.news.subscription.mqtt;

import com.news.subscription.model.Topic;
import com.news.subscription.mqtt.gateway.NewsMessagingGateway;
import com.news.subscription.mqtt.model.NewsMessage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;

import java.io.IOException;
import java.util.Random;

@SpringBootApplication
@IntegrationComponentScan
public class OutboundClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(OutboundClient.class)
                .web(false).run(args);

        NewsMessagingGateway<NewsMessage> gateway =  ctx.getBean(NewsMessagingGateway.class);

        // Infinite loop to produce/publish news
        for (;;) {
            int nextInt = new Random().nextInt();
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setTitle(String.format("Title %d: Berlin tech news %d", nextInt, nextInt));
            newsMessage.setTopic(Topic.TECH.getName());
            newsMessage.setCity("Berlin");

            // Publisher can set topic name as a Header attribute
            gateway.processHeartBeatMessage(MessageBuilder
                    .withPayload(newsMessage)
                    .setHeader(MqttHeaders.TOPIC, "/newsMessage/notify")
                    .build());
            System.out.print("Successfully published message:");
        }
    }
}
