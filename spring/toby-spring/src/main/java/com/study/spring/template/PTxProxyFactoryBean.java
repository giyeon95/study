package com.study.spring.template;

import com.study.spring.user.service.TransactionHandler;
import java.lang.reflect.Proxy;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
public class PTxProxyFactoryBean implements FactoryBean<Object> {

    private final Object target;
    private final PlatformTransactionManager transactionManager;
    private final String pattern;
    private final Class<?> serviceInterface;

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(
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
