package com.news.subscription.repo;

import com.news.subscription.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {


}