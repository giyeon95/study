package com.study.spring.user.service;

import com.study.spring.user.domain.User;
import java.sql.SQLException;
import lombok.Getter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceTx implements UserService {

    private final UserService userService;
    private final PlatformTransactionManager transactionManager;

    public UserServiceTx(UserService userService,
        PlatformTransactionManager transactionManager) {
        this.userService = userService;
        this.transactionManager = transactionManager;
    }

    @Override
    public void upgradeLevels() throws SQLException {
        TransactionStatus status = transactionManager
            .getTransaction(new DefaultTransactionDefinition());

        try {
            userService.upgradeLevels();
            transactionManager.commit(status);

        } catch (RuntimeException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }


    @Override
    public void upgradeLevel(User user) {
        userService.upgradeLevel(user);
    }

    @Override
    public void add(User user) {
        userService.add(user);
    }
}
