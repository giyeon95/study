package com.study.spring.template;

import com.study.spring.user.service.TransactionHandler;
import java.lang.reflect.Proxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
public class PTxProxyFactoryBean<T> implements FactoryBean<T> {

    private final T target;
    private final PlatformTransactionManager transactionManager;
    private final String pattern;
    private final Class<?> serviceInterface;

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(
            getClass().getClassLoader(),
            new Class[]{serviceInterface},
            new TransactionHandler(target, transactionManager, pattern)
        );
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
