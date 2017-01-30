package com.news.subscription;

import com.news.subscription.model.Topic;
import com.news.subscription.model.User;
import com.news.subscription.model.UserSubscription;
import com.news.subscription.repo.UserRepository;
import com.news.subscription.service.UserService;
import com.news.subscription.service.UserSubscriptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        // Test purpose: Create User and subscriptions
        setupSystem(ctx);
        // Inbound message subscription!
        MqttPahoMessageDrivenChannelAdapter inboundMessageHandler = (MqttPahoMessageDrivenChannelAdapter)
                ctx.getBean("inboundMessageHandler", MessageProducer.class);
    }

    private static void setupSystem(ConfigurableApplicationContext ctx) {
        UserService userService = ctx.getBean(UserService.class);
        UserSubscriptionService userSubscriptionService = ctx.getBean(UserSubscriptionService.class);

        User u = new User();
        u.setEmail("jay@gmail.com");
        u.setName("Jay");

        User user = userService.save(u);

        UserSubscription userSubscription = new UserSubscription();

        userSubscription.setTopic(Topic.TECH.getName());
        userSubscription.setUser(user);
        userSubscriptionService.save(userSubscription);

    }
}