package com.news.subscription.model;

public enum Topic {
    SPORTS("sports"), POLITICS("politics"), TECH("tech");

    private String name;

    Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
