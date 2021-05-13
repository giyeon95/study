package com.study.spring.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;

@RequiredArgsConstructor
public class MessageFactoryBean implements FactoryBean<Message> {

    private final String text;

    @Override
    public Message getObject() throws Exception {
        return Message.newMessage(this.text);
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
