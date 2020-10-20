package com.train.springboot;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ArgumentHolder {
    public ArgumentHolder(ApplicationArguments arguments) {
        System.out.println("foo: "+ arguments.containsOption("foo"));
        System.out.println("bar: "+ arguments.containsOption("bar"));
    }
}
