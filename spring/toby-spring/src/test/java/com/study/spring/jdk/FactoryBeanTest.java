package com.study.spring.jdk;

import static org.assertj.core.api.Assertions.*;

import com.study.spring.email.Message;
import com.study.spring.user.repository.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryBeanTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void getMessageFromBeanTest() {

        Message message = ac.getBean("message", Message.class);

        System.out.println("message = " + message.getText());

        assertThat(message).isInstanceOf(Message.class);
        assertThat(message.getText()).isEqualTo("hello world!");
    }
    @Test
    void getFactoryBeanTest() {
        Object factoryBean = ac.getBean("&message");

        assertThat(factoryBean).isInstanceOf(FactoryBean.class);

    }

}
