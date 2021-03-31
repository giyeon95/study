package com.study.spring.template;

@FunctionalInterface
public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);

}
