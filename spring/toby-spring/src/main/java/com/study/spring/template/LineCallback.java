package com.study.spring.template;

@FunctionalInterface
public interface LineCallback {
    Integer doSomethingWithLine(String line, Integer value);

}
