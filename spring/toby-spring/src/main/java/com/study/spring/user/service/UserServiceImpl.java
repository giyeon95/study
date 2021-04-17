package com.study.spring.user.service;

import com.study.spring.user.domain.Level;
import com.study.spring.user.domain.User;
import com.study.spring.user.repository.UserRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserLevelUpgradePolicy userLevelUpgradePolicy;
    private final DataSource dataSource;

    public UserServiceImpl(UserRepository userRepository,
        UserLevelUpgradePolicy userLevelUpgradePolicy, DataSource dataSource) {
        this.userRepository = userRepository;
        this.userLevelUpgradePolicy = userLevelUpgradePolicy;
        this.dataSource = dataSource;
    }

    @Override
    public void upgradeLevels() throws SQLException {
        TransactionSynchronizationManager.initSynchronization();
        Connection c = DataSourceUtils.getConnection(dataSource);
        c.setAutoCommit(false);

        try {
            List<User> users = userRepository.getAll();
            users.forEach(this::upgradeLevel);

            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally {
            DataSourceUtils.releaseConnection(c, dataSource);
            TransactionSynchronizationManager.unbindResource(this.dataSource);
            TransactionSynchronizationManager.clearSynchronization();
        }
    }


    @Override
    public void upgradeLevel(User user) {
        if (userLevelUpgradePolicy.canUpgradeLevel(user)) {
            User upgradeUser = userLevelUpgradePolicy.upgradeLevel(user);
            userRepository.update(upgradeUser);
        }
    }


    @Override
    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userRepository.add(user);
    }

}
