package com.news.subscription.service;

import com.news.subscription.model.UserSubscription;
import com.news.subscription.repo.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionService {

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    public UserSubscription save(UserSubscription userSubscription) {
        return userSubscriptionRepository.save(userSubscription);
    }
}
