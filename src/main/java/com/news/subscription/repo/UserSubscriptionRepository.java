package com.news.subscription.repo;

import com.news.subscription.model.UserSubscription;
import org.springframework.data.repository.CrudRepository;

public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {

}