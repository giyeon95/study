package com.study.spring.user.service;

import com.study.spring.email.EmailDTO;
import com.study.spring.email.EmailUtils;
import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import com.study.spring.user.repository.UserRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserLevelUpgradePolicy userLevelUpgradePolicy;
    private final PlatformTransactionManager transactionManager;
    private final EmailUtils emailUtils;

    public UserServiceImpl(UserRepository userRepository,
        UserLevelUpgradePolicy userLevelUpgradePolicy,
        PlatformTransactionManager transactionManager, EmailUtils emailUtils) {
        this.userRepository = userRepository;
        this.userLevelUpgradePolicy = userLevelUpgradePolicy;
        this.transactionManager = transactionManager;
        this.emailUtils = emailUtils;
    }

    @Override
    public void upgradeLevels() throws SQLException {

        TransactionStatus status = transactionManager
            .getTransaction(new DefaultTransactionDefinition());

        try {
            List<User> users = userRepository.getAll();
            users.forEach(this::upgradeLevel);
            transactionManager.commit(status);

        } catch (RuntimeException e) {
            transactionManager.rollback(status);
            throw e;
        }

    }


    @Override
    public void upgradeLevel(User user) {
        if (userLevelUpgradePolicy.canUpgradeLevel(user)) {
            User upgradeUser = userLevelUpgradePolicy.upgradeLevel(user);
            userRepository.update(upgradeUser);
        }

        emailUtils.send(
            EmailDTO.builder()
                .receiver(user.getEmail())
                .subject("Upgrade 안내")
                .contents("사용자님의 등급이" + user.getLevel().name() + "로 업그레이드 되었습니다.")
                .build());
    }


    @Override
    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userRepository.add(user);
    }

}
