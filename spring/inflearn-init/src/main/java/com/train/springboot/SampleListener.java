package com.train.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SampleListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    AuthorProperties authorProperties;



    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("===================");
        System.out.println("Start Application ");
        System.out.println(authorProperties.getFullName());
        System.out.println(authorProperties.getAge());
        System.out.println(authorProperties.getSessionTimeout());

        System.out.println("===================");
    }
}
