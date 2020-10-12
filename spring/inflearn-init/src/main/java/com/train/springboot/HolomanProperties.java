package com.train.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "holoman")
public class HolomanProperties {

    private String name;
    private int term;


    public String getName() {
        return name;
    }

    public int getTerm() {
        return term;
    }
}
